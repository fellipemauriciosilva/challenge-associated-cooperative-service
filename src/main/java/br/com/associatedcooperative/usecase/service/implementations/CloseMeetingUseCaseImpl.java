package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.domain.ruling.Ruling;
import br.com.associatedcooperative.infrastructure.persistence.MeetingRepository;
import br.com.associatedcooperative.usecase.service.CloseMeetingUseCase;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CloseMeetingUseCaseImpl implements CloseMeetingUseCase {

    private final MeetingRepository repository;

    @Transactional
    @Override
    public void closeMeetingById(Long id) {
         repository.findById(id)
                .orElseThrow(EntityNotFoundException::new)
                 .closeMeeting()
                 .getRulings()
                 .forEach(Ruling::closeRuling);
    }
}
