package br.com.associatedcooperative.infrastructure.controller.v1.implementations;

import br.com.associatedcooperative.infrastructure.controller.v1.MeetingControllerPort;
import br.com.associatedcooperative.infrastructure.controller.utils.PathUtils;
import br.com.associatedcooperative.usecase.dto.MeetingRequestDto;
import br.com.associatedcooperative.usecase.dto.MeetingResponseDto;
import br.com.associatedcooperative.usecase.service.implementations.FindMeetingByIdUseCaseImpl;
import br.com.associatedcooperative.usecase.service.implementations.FindAllMeetingsUseCaseImpl;
import br.com.associatedcooperative.usecase.service.implementations.CreateMeetingUseCaseImpl;
import br.com.associatedcooperative.usecase.service.implementations.CloseMeetingUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeetingControllerAdapter implements MeetingControllerPort {

    private final FindAllMeetingsUseCaseImpl findAllMeetingsUseCase;
    private final CreateMeetingUseCaseImpl createMeetingUseCase;
    private final FindMeetingByIdUseCaseImpl findMeetingByIdUseCase;
    private final CloseMeetingUseCaseImpl closeMeetingUseCase;

    @Override
    public ResponseEntity<List<MeetingResponseDto>> findAll() {
        return ResponseEntity.ok(findAllMeetingsUseCase.findAllMeetings());
    }

    @Override
    public ResponseEntity<MeetingResponseDto> createMeeting(MeetingRequestDto dto, UriComponentsBuilder uriBuilder) {
        var meetingResponseDto = createMeetingUseCase.createMeeting(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path(PathUtils.V1.Meeting.MEETING + PathUtils.Meeting.FIND)
                        .buildAndExpand(meetingResponseDto.id())
                        .toUri())
                .body(meetingResponseDto);    }

    @Override
    public ResponseEntity closeMeeting(Long id) {
        closeMeetingUseCase.closeMeetingById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<MeetingResponseDto> findMeeting(Long id) {
        return ResponseEntity.ok(findMeetingByIdUseCase.findMeetingById(id));
    }
}
