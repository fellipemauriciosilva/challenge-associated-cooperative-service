package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.domain.member.Member;
import br.com.associatedcooperative.infrastructure.persistence.MemberRepository;
import br.com.associatedcooperative.usecase.service.CreateMemberUseCase;
import br.com.associatedcooperative.usecase.dto.MemberRequestDto;
import br.com.associatedcooperative.usecase.dto.MemberResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMemberUseCaseImpl implements CreateMemberUseCase {

    private final MemberRepository repository;

    @Transactional
    @Override
    public MemberResponseDto createMember(MemberRequestDto dto) {
        return new MemberResponseDto(repository.save(new Member(dto.name(), dto.cpf())));
    }
}
