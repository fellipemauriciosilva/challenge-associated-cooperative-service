package br.com.associatedcooperative.usecase.service;

import br.com.associatedcooperative.usecase.dto.MeetingRequestDto;
import br.com.associatedcooperative.usecase.dto.MeetingResponseDto;

public interface CreateMeetingUseCase {
    MeetingResponseDto createMeeting(MeetingRequestDto dto);
}
