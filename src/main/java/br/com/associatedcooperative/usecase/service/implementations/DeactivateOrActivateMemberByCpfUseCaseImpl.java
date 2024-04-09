package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.domain.member.MemberStatus;
import br.com.associatedcooperative.infrastructure.persistence.MemberRepository;
import br.com.associatedcooperative.usecase.service.DeactivateOrActivateMemberByCpfUseCase;
import br.com.associatedcooperative.usecase.dto.MemberResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeactivateOrActivateMemberByCpfUseCaseImpl implements DeactivateOrActivateMemberByCpfUseCase {

    private final MemberRepository repository;

    @Transactional
    @Override
    public MemberResponseDto deactivateOrActivateMember(String cpf, MemberStatus status) {
        return new MemberResponseDto(
                repository
                        .findByCpf(cpf)
                        .orElseThrow(EntityNotFoundException::new)
                        .inactivateOrActivate(status));
    }
}
