package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.infrastructure.persistence.RulingRepository;
import br.com.associatedcooperative.usecase.service.FindRulingByIdUseCase;
import br.com.associatedcooperative.usecase.dto.RulingResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindRulingByIdUseCaseImpl implements FindRulingByIdUseCase {

    private final RulingRepository repository;
    @Override
    public RulingResponseDto findRulingById(Long id) {
        return new RulingResponseDto(repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }
}
