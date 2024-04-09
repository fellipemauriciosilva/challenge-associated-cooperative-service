package br.com.associatedcooperative.infrastructure.controller.v1.implementations;

import br.com.associatedcooperative.infrastructure.controller.v1.VoteControllerPort;
import br.com.associatedcooperative.usecase.dto.VoteRequestDto;
import br.com.associatedcooperative.usecase.service.implementations.CastVoteByCpfUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoteControllerAdapter implements VoteControllerPort {

    private final CastVoteByCpfUseCaseImpl castVoteByCpfUseCase;

    @Override
    public ResponseEntity<?> castVote(VoteRequestDto dto) {
        castVoteByCpfUseCase.castVote(dto);
        return ResponseEntity.ok().build();
    }
}
