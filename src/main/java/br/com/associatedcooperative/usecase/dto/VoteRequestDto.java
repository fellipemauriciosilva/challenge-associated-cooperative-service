package br.com.associatedcooperative.usecase.dto;

import br.com.associatedcooperative.domain.vote.VoteValue;
import br.com.associatedcooperative.usecase.validation.Cpf;
import jakarta.validation.constraints.NotNull;

public record VoteRequestDto(
        @NotNull VoteValue voteValue,
        @Cpf String cpf,
        @NotNull Long rulingId) {
}
