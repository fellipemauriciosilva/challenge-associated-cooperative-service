package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.domain.ruling.Ruling;
import br.com.associatedcooperative.infrastructure.handler.exception.DateTimeValidationException;
import br.com.associatedcooperative.infrastructure.persistence.MeetingRepository;
import br.com.associatedcooperative.infrastructure.persistence.RulingRepository;
import br.com.associatedcooperative.infrastructure.handler.exception.MeetingValidationException;
import br.com.associatedcooperative.usecase.service.CreateRulingUseCase;
import br.com.associatedcooperative.usecase.dto.RulingRequestDto;
import br.com.associatedcooperative.usecase.dto.RulingResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateRulingUseCaseImpl implements CreateRulingUseCase {

    private final RulingRepository rulingRepository;
    private final MeetingRepository meetingRepository;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public RulingResponseDto createRuling(RulingRequestDto dto) {
        var meeting = meetingRepository.findById(dto.meetingId()).orElseThrow(EntityNotFoundException::new);
        if(!meeting.isOpen()) throw new MeetingValidationException("Cannot create Ruling for a closed Meeting");
        return new RulingResponseDto(
                rulingRepository.save(
                        new Ruling(dto.description(), getEndTime(dto.endTime()), meeting)));
    }

    private LocalDateTime getEndTime(LocalDateTime endTime){
        if(Objects.isNull(endTime)) return LocalDateTime.now().plusMinutes(1L);
        if (endTime.isBefore(LocalDateTime.now())) throw new DateTimeValidationException("The end time of the Ruling is before the start time");
        return endTime;
    }
}

