package br.com.associatedcooperative.usecase.service;


import br.com.associatedcooperative.usecase.dto.MeetingResponseDto;

public interface FindMeetingByIdUseCase {
    MeetingResponseDto findMeetingById(Long id);
}
