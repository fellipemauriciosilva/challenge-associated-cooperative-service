package br.com.associatedcooperative.usecase.dto;

import br.com.associatedcooperative.domain.ruling.Ruling;
import br.com.associatedcooperative.domain.ruling.RulingStatus;

import java.time.LocalDateTime;

public record RulingResponseDto(Long id, String description, RulingStatus status, LocalDateTime startTime, LocalDateTime endTime) {
    public RulingResponseDto(Ruling ruling) {
        this(ruling.getId(), ruling.getDescription(), ruling.getStatus(), ruling.getStartTime(), ruling.getEndTime());
    }
}
