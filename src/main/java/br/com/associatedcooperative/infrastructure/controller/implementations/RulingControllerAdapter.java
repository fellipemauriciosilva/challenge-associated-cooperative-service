package br.com.associatedcooperative.infrastructure.controller.implementations;

import br.com.associatedcooperative.domain.ruling.RulingStatus;
import br.com.associatedcooperative.infrastructure.controller.RulingControllerPort;
import br.com.associatedcooperative.infrastructure.controller.utils.PathUtils;
import br.com.associatedcooperative.usecase.dto.RulingRequestDto;
import br.com.associatedcooperative.usecase.dto.RulingResponseDto;
import br.com.associatedcooperative.usecase.dto.RulingResultResponse;
import br.com.associatedcooperative.usecase.service.implementations.FindRulingByIdUseCaseImpl;
import br.com.associatedcooperative.usecase.service.implementations.CreateRulingUseCaseImpl;
import br.com.associatedcooperative.usecase.service.implementations.RulingResultByIdUseCaseImpl;
import br.com.associatedcooperative.usecase.service.implementations.StartOrFinishRulingByIdUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class RulingControllerAdapter implements RulingControllerPort {

    private final CreateRulingUseCaseImpl createRulingUseCase;
    private final FindRulingByIdUseCaseImpl findRulingByIdUseCase;
    private final StartOrFinishRulingByIdUseCaseImpl startOrFinishRulingByIdUseCase;
    private final RulingResultByIdUseCaseImpl rulingResultByIdUseCase;

    @Override
    public ResponseEntity<RulingResponseDto> createRuling(RulingRequestDto dto, UriComponentsBuilder uriBuilder) {
        var responseDto = createRulingUseCase.createRuling(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path(PathUtils.V1.Ruling.RULING + PathUtils.Ruling.FIND)
                        .buildAndExpand(responseDto.id())
                        .toUri())
                .body(responseDto);
    }

    @Override
    public ResponseEntity<RulingResponseDto> findRulingById(Long id) {
        return ResponseEntity.ok(findRulingByIdUseCase.findRulingById(id));
    }

    @Override
    public ResponseEntity<RulingResponseDto> startOrFinishRuling(Long id, RulingStatus status) {
        return ResponseEntity.accepted().body(startOrFinishRulingByIdUseCase.startOrFinishRuling(id, status));
    }

    @Override
    public ResponseEntity<RulingResultResponse> rulingResult(Long id) {
        return ResponseEntity.ok(rulingResultByIdUseCase.getRulingResultById(id));
    }
}
