package br.com.associatedcooperative.usecase.service;


import br.com.associatedcooperative.domain.ruling.RulingStatus;
import br.com.associatedcooperative.usecase.dto.RulingResponseDto;

public interface StartOrFinishRulingByIdUseCase {
    RulingResponseDto startOrFinishRuling(Long id, RulingStatus status);
}
