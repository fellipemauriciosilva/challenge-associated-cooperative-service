package br.com.associatedcooperative.infrastructure.controller;

import br.com.associatedcooperative.domain.ruling.RulingStatus;
import br.com.associatedcooperative.infrastructure.controller.utils.PathUtils;
import br.com.associatedcooperative.usecase.dto.RulingRequestDto;
import br.com.associatedcooperative.usecase.dto.RulingResponseDto;
import br.com.associatedcooperative.usecase.dto.RulingResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = PathUtils.V1.Ruling.RULING, produces = MediaType.APPLICATION_JSON_VALUE)
public interface RulingControllerPort {

    @PostMapping(PathUtils.Ruling.CREATE)
    @Operation(
            summary = "Create - Creates a Ruling",
            description = "<ul><li><p>The description of the ruling, the end date, and the ID of the Meeting to which the Ruling belongs must be entered</p></li>" +
                    "<p><strong>NOTE:</strong> Every Ruling is created with Status STARTED. The end date must be in the format: yyyy-MM-ddTHH:mm (year-month-dayThour:minute)</p></ul></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = RulingResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<RulingResponseDto> createRuling(@RequestBody @Valid RulingRequestDto dto, UriComponentsBuilder uriBuilder);

    @GetMapping(PathUtils.Ruling.FIND)
    @Operation(
            summary = "Find by ID - Queries a Ruling",
            description = "Queries a Ruling by Id, returns 404 if the Ruling does not exist"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = RulingResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<RulingResponseDto> findRulingById(@PathVariable(PathUtils.Placeholder.ID) Long id);

    @PutMapping(PathUtils.Ruling.STATUS)
    @Operation(
            summary = "Update Status - Starts or Finishes a Ruling",
            description = "<ul><li><p>Starts or Finishes a Ruling by ID, returns 404 if the Ruling does not exist</p></li>" +
                    "<li><p> The Ruling's Status can be changed to STARTED or FINISHED</p></li></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = RulingResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<RulingResponseDto> startOrFinishRuling(@PathVariable(PathUtils.Placeholder.ID) Long id,
                                                          @RequestParam(value = PathUtils.Placeholder.STATUS, required = true) RulingStatus status);

    @GetMapping(PathUtils.Ruling.RESULT)
    @Operation(
            summary = "Result - Queries the Ruling's result",
            description = "Queries the voting result of a Ruling by Id, returns 404 if the Ruling does not exist"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = RulingResultResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<RulingResultResponse> rulingResult(@PathVariable(PathUtils.Placeholder.ID) Long id);
}
