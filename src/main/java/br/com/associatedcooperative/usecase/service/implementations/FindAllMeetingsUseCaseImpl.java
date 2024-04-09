package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.infrastructure.persistence.MeetingRepository;
import br.com.associatedcooperative.usecase.service.FindAllMeetingsUseCase;
import br.com.associatedcooperative.usecase.dto.MeetingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllMeetingsUseCaseImpl implements FindAllMeetingsUseCase {

    private final MeetingRepository repository;
    @Override
    public List<MeetingResponseDto> findAllMeetings() {
        return repository.findAll().stream().map(MeetingResponseDto::new).toList();
    }
}
