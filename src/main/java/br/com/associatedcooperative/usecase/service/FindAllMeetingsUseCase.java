package br.com.associatedcooperative.usecase.service;

import br.com.associatedcooperative.usecase.dto.MeetingResponseDto;

import java.util.List;

public interface FindAllMeetingsUseCase {
    List<MeetingResponseDto> findAllMeetings();
}
