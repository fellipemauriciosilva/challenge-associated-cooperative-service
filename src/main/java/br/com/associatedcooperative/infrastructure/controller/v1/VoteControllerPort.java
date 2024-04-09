package br.com.associatedcooperative.infrastructure.controller.v1;

import br.com.associatedcooperative.infrastructure.controller.utils.PathUtils;
import br.com.associatedcooperative.usecase.dto.VoteRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = PathUtils.V1.Vote.VOTE, produces = MediaType.APPLICATION_JSON_VALUE)
public interface VoteControllerPort {

    @PostMapping(PathUtils.Vote.CREATE)
    @Operation(
            summary = "Create - Casts a Vote",
            description = "<ul><li><p>The vote must be entered as YES or NO, along with the ID of the Ruling and the CPF of the Member</p></li>" +
                    "<p><strong>NOTE:</strong> Only Members with status ABLE_TO_VOTE can vote, and only Rulings with status STARTED accept votes </p></ul></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<?> castVote(@RequestBody @Valid VoteRequestDto dto);

}
