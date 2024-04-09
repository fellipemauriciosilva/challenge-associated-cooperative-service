package br.com.associatedcooperative.usecase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RulingRequestDto(@NotEmpty @NotBlank String description, LocalDateTime endTime, @NotNull Long meetingId) {
}
