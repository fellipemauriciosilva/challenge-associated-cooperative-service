package br.com.associatedcooperative.infrastructure.controller.v1;

import br.com.associatedcooperative.domain.member.MemberStatus;
import br.com.associatedcooperative.infrastructure.controller.utils.PathUtils;
import br.com.associatedcooperative.usecase.dto.MemberRequestDto;
import br.com.associatedcooperative.usecase.dto.MemberResponseDto;
import br.com.associatedcooperative.usecase.validation.Cpf;
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
@RequestMapping(value = PathUtils.V1.Member.MEMBER, produces = MediaType.APPLICATION_JSON_VALUE)
public interface MemberControllerPort {

    @PostMapping(PathUtils.Member.CREATE)
    @Operation(
            summary = "Create - Creates a Member",
            description = "<ul><li><p>A Member's name and a valid CPF must be entered</p></li>" +
                    "<p><strong>NOTE:</strong> Every Member is created with Status ABLE_TO_VOTE</p></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = MemberResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<MemberResponseDto> createMember(@RequestBody @Valid MemberRequestDto dto, UriComponentsBuilder uriBuilder);

    @GetMapping(PathUtils.Member.FIND)
    @Operation(
            summary = "Find by CPF - Queries a Member",
            description = "Queries a Member by CPF, enter numbers only, returns 404 if the Member does not exist"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MemberResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<MemberResponseDto> findMemberByCpf(@PathVariable(PathUtils.Placeholder.CPF) @Valid @Cpf String cpf);

    @PutMapping(PathUtils.Member.STATUS)
    @Operation(
            summary = "Update Status - Deactivates or Activates a Member",
            description = "<ul><li><p>Deactivates a Member by CPF, enter numbers only, returns 404 if the Member does not exist</p></li>" +
                    "<li><p> The Member's Status can be changed to UNABLE_TO_VOTE or ABLE_TO_VOTE</p></li></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MemberResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<MemberResponseDto> deactivateOrActivateMember(@PathVariable(PathUtils.Placeholder.CPF) String cpf, @RequestParam(value = PathUtils.Placeholder.STATUS, required = true) MemberStatus status);
}
