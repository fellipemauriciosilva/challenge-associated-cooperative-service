package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.domain.member.Member;
import br.com.associatedcooperative.domain.ruling.Ruling;
import br.com.associatedcooperative.domain.vote.Vote;
import br.com.associatedcooperative.domain.vote.VoteValue;
import br.com.associatedcooperative.infrastructure.persistence.MemberRepository;
import br.com.associatedcooperative.infrastructure.persistence.RulingRepository;
import br.com.associatedcooperative.infrastructure.persistence.VoteRepository;
import br.com.associatedcooperative.infrastructure.message.Producer;
import br.com.associatedcooperative.usecase.service.CastVoteByCpfUseCase;
import br.com.associatedcooperative.usecase.dto.VoteKafka;
import br.com.associatedcooperative.usecase.dto.VoteRequestDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiPredicate;


@Slf4j
@Service
@RequiredArgsConstructor
public class CastVoteByCpfUseCaseImpl implements CastVoteByCpfUseCase {

    private final Producer producer;
    private final MemberRepository memberRepository;
    private final RulingRepository rulingRepository;
    private final VoteRepository voteRepository;

    private final List<BiPredicate<Member, Ruling>> voteValidationRules = List.of(
            this::validateMemberEligibleAndRulingStarted,
            this::validateIfMemberHasAlreadyVoted
    );

    @Override
    public void castVote(VoteRequestDto dto) {
        producer.send(dto);
    }

    @Transactional
    @Override
    public void persistVote(VoteKafka voteKafka) {
        var member = memberRepository.findByCpf(voteKafka.getCpf()).orElseThrow(EntityNotFoundException::new);
        var ruling = rulingRepository.findById(voteKafka.getRuling()).orElseThrow(EntityNotFoundException::new);
        if(voteValidationRules.stream().anyMatch(validation -> !validation.test(member, ruling))){
            log.info("Member ineligible, Ruling concluded, or vote already cast");
            return;
        }
        var vote = Vote.builder()
                .voteValue(VoteValue.valueOf(voteKafka.getVoteValue()))
                .members(List.of(member))
                .rulings(List.of(ruling))
                .build();
        member.addVote(vote);
        ruling.addVote(vote);
    }

    private boolean validateMemberEligibleAndRulingStarted(Member member, Ruling ruling) {
        return member.canVote() && ruling.isStarted();
    }

    private boolean validateIfMemberHasAlreadyVoted(Member member, Ruling ruling) {
        return !voteRepository.existsByMembersAndRulings(List.of(member), List.of(ruling));
    }
}
