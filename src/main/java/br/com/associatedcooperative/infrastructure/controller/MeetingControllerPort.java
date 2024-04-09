package br.com.associatedcooperative.infrastructure.controller;

import br.com.associatedcooperative.infrastructure.controller.utils.PathUtils;
import br.com.associatedcooperative.usecase.dto.MeetingRequestDto;
import br.com.associatedcooperative.usecase.dto.MeetingResponseDto;
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

import java.util.List;

@RestController
@RequestMapping(value = PathUtils.V1.Meeting.MEETING, produces = MediaType.APPLICATION_JSON_VALUE)
public interface MeetingControllerPort {

    @GetMapping(PathUtils.Meeting.FIND_ALL)
    @Operation(
            summary = "Find All",
            description = "Queries all Meetings"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    ResponseEntity<List<MeetingResponseDto>> findAll();

    @PostMapping(PathUtils.Meeting.CREATE)
    @Operation(
            summary = "Create - Creates a Meeting",
            description = "<ul><li><p>Must input the name of the Meeting<p></li>" +
                    "<p><strong>NOTE:</strong> Every Meeting is created with status OPEN</p></ul></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = MeetingResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<MeetingResponseDto> createMeeting(@RequestBody @Valid MeetingRequestDto dto, UriComponentsBuilder uriBuilder);

    @PutMapping(PathUtils.Meeting.CLOSE)
    @Operation(
            summary = "Enclose - Closes a Meeting",
            description = "<ul><li><p>Must inform the Id of the Meeting, the Meeting's status will be changed to CLOSED. All Agendas associated with this Meeting will be concluded</p></li></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MeetingResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity closeMeeting(@PathVariable(PathUtils.Placeholder.ID) Long id);

    @GetMapping(PathUtils.Meeting.FIND)
    @Operation(
            summary = "Find by Id - Queries a Meeting",
            description = "Queries a Meeting by its Id, returning its Agendas or 404 if the Meeting does not exist"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MeetingResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<MeetingResponseDto> findMeeting(@PathVariable(PathUtils.Placeholder.ID) Long id);

}
