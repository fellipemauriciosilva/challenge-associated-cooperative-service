package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.domain.ruling.Ruling;
import br.com.associatedcooperative.domain.vote.VoteValue;
import br.com.associatedcooperative.infrastructure.handler.exception.NoVotesInRulingException;
import br.com.associatedcooperative.infrastructure.persistence.RulingRepository;
import br.com.associatedcooperative.usecase.service.RulingResultByIdUseCase;
import br.com.associatedcooperative.usecase.dto.RulingResultResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RulingResultByIdUseCaseImpl implements RulingResultByIdUseCase {

    private static final String RULING_APPROVED = "Ruling approved by majority of YES votes";
    private static final String RULING_DENIED = "Ruling denied by majority of NO votes";
    private static final String RULING_NO_RESULT = "Ruling without results due to a tie";
    private final RulingRepository rulingRepository;

    @Override
    public RulingResultResponse getRulingResultById(Long id) {
        var ruling = rulingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        validateVoteExistence(ruling);
        var yesCount = voteCount(ruling, VoteValue.YES);
        var noCount = voteCount(ruling, VoteValue.NO);
        var resultDescription = resultDescription(yesCount, noCount);
        return new RulingResultResponse(ruling.getId(), ruling.getDescription(), yesCount, noCount, resultDescription, ruling.getMeeting().getName());
    }

    private Long voteCount(Ruling ruling, VoteValue value){
        return ruling.getVotes().stream().filter(vote -> vote.getVoteValue().equals(value)).count();
    }

    private String resultDescription(Long yesCount, Long noCount){
        if(yesCount > noCount) return RULING_APPROVED;
        if(noCount > yesCount) return RULING_DENIED;
        return RULING_NO_RESULT;
    }

    private void validateVoteExistence(Ruling ruling){
        if(ruling.getVotes().isEmpty()) throw new NoVotesInRulingException("There are no votes to count for this Ruling");
    }
}

