package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.infrastructure.persistence.MemberRepository;
import br.com.associatedcooperative.usecase.service.FindMemberByCpfUseCase;
import br.com.associatedcooperative.usecase.dto.MemberResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindMemberByCpfUseCaseImpl implements FindMemberByCpfUseCase {

    private final MemberRepository repository;
    @Override
    public MemberResponseDto findMemberByCpf(String cpf) {
        return new MemberResponseDto(repository.findByCpf(cpf).orElseThrow(EntityNotFoundException::new));
    }
}
