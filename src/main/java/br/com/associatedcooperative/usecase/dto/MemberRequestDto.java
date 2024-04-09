package br.com.associatedcooperative.usecase.dto;

import br.com.associatedcooperative.usecase.validation.Cpf;
import jakarta.validation.constraints.NotEmpty;

public record MemberRequestDto(@NotEmpty String name, @Cpf String cpf) {
}
