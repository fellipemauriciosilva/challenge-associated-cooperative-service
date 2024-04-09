package br.com.associatedcooperative.usecase.dto;

import jakarta.validation.constraints.NotEmpty;

public record MeetingRequestDto(@NotEmpty String name) {
}
