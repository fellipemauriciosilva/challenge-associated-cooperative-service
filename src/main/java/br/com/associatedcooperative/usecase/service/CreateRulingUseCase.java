package br.com.associatedcooperative.usecase.service;

import br.com.associatedcooperative.usecase.dto.RulingRequestDto;
import br.com.associatedcooperative.usecase.dto.RulingResponseDto;

public interface CreateRulingUseCase {

    RulingResponseDto createRuling(RulingRequestDto dto);
}
