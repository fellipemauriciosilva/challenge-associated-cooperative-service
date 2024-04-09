package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.domain.ruling.RulingStatus;
import br.com.associatedcooperative.infrastructure.persistence.RulingRepository;
import br.com.associatedcooperative.usecase.service.StartOrFinishRulingByIdUseCase;
import br.com.associatedcooperative.usecase.dto.RulingResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartOrFinishRulingByIdUseCaseImpl implements StartOrFinishRulingByIdUseCase {

    private final RulingRepository repository;

    @Transactional
    @Override
    public RulingResponseDto startOrFinishRuling(Long id, RulingStatus status) {
        return new RulingResponseDto(
                repository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new)
                        .startOrCloseRuling(status));
    }
}
