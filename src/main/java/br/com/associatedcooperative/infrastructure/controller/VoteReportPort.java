package br.com.associatedcooperative.infrastructure.controller;

import br.com.associatedcooperative.infrastructure.controller.utils.PathUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = PathUtils.V1.Ruling.RULING, produces = MediaType.APPLICATION_PDF_VALUE)
public interface VoteReportPort {

    @GetMapping(PathUtils.Ruling.REPORT)
    @Operation(
            summary = "Report - PDF of the Ruling's Result",
            description = "<ul><li><p>The ID of the Ruling must be entered, A PDF with the result of the Ruling and vote count will be issued<p></li>")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/pdf") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<InputStreamResource> rulingVoteCountReport(@PathVariable(PathUtils.Placeholder.ID) Long id);
}

