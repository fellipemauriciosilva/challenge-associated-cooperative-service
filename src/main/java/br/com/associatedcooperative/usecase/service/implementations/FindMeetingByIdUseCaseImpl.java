package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.infrastructure.persistence.MeetingRepository;
import br.com.associatedcooperative.usecase.service.FindMeetingByIdUseCase;
import br.com.associatedcooperative.usecase.dto.MeetingResponseDto;
import br.com.associatedcooperative.usecase.dto.RulingResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindMeetingByIdUseCaseImpl implements FindMeetingByIdUseCase {

    private final MeetingRepository repository;
    @Override
    public MeetingResponseDto findMeetingById(Long id) {
        var meeting = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new MeetingResponseDto(meeting, meeting.getRulings().stream().map(RulingResponseDto::new).toList());
    }
}
