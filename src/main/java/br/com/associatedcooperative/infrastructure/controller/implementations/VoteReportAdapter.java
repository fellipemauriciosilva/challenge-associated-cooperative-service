package br.com.associatedcooperative.infrastructure.controller.implementations;

import br.com.associatedcooperative.infrastructure.controller.VoteReportPort;
import br.com.associatedcooperative.usecase.service.implementations.GenerateVotesPdfReportByIdUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoteReportAdapter implements VoteReportPort {

    private final GenerateVotesPdfReportByIdUseCaseImpl generateVotesPdfReportByIdUseCase;

    @Override
    public ResponseEntity<InputStreamResource> rulingVoteCountReport(Long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=voto.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(generateVotesPdfReportByIdUseCase.generateVotesPdfReport(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
