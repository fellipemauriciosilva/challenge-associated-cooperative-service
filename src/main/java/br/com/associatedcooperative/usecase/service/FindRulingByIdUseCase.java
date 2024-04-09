package br.com.associatedcooperative.usecase.service;

import br.com.associatedcooperative.usecase.dto.RulingResponseDto;

public interface FindRulingByIdUseCase {
    RulingResponseDto findRulingById(Long id);
}
