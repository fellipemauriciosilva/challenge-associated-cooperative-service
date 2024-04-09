package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.domain.meeting.Meeting;
import br.com.associatedcooperative.infrastructure.persistence.MeetingRepository;
import br.com.associatedcooperative.usecase.service.CreateMeetingUseCase;
import br.com.associatedcooperative.usecase.dto.MeetingRequestDto;
import br.com.associatedcooperative.usecase.dto.MeetingResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMeetingUseCaseImpl implements CreateMeetingUseCase {

    private final MeetingRepository repository;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public MeetingResponseDto createMeeting(MeetingRequestDto dto) {
        return new MeetingResponseDto(repository.save(new Meeting(dto.name())));
    }
}
