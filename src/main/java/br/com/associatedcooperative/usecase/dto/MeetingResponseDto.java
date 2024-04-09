package br.com.associatedcooperative.usecase.dto;

import br.com.associatedcooperative.domain.meeting.Meeting;
import br.com.associatedcooperative.domain.meeting.MeetingStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MeetingResponseDto(Long id, String nome, MeetingStatus status, @JsonProperty("Ruling") List<RulingResponseDto> rulingResponseDtoList) {
    public MeetingResponseDto(Meeting meeting, List<RulingResponseDto> rulingResponseDtoList) {
        this(meeting.getId(), meeting.getName(), meeting.getStatus(), rulingResponseDtoList);
    }
    public MeetingResponseDto(Meeting meeting) {
        this(meeting.getId(), meeting.getName(), meeting.getStatus(), null);
    }
}
