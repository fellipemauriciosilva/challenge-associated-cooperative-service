package br.com.associatedcooperative.usecase.service;

import br.com.associatedcooperative.usecase.dto.RulingResultResponse;

public interface RulingResultByIdUseCase {
    RulingResultResponse getRulingResultById(Long id);
}
