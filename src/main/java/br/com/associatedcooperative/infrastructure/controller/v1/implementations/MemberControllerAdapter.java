package br.com.associatedcooperative.infrastructure.controller.v1.implementations;

import br.com.associatedcooperative.domain.member.MemberStatus;
import br.com.associatedcooperative.infrastructure.controller.v1.MemberControllerPort;
import br.com.associatedcooperative.infrastructure.controller.utils.PathUtils;
import br.com.associatedcooperative.usecase.dto.MemberRequestDto;
import br.com.associatedcooperative.usecase.dto.MemberResponseDto;
import br.com.associatedcooperative.usecase.service.implementations.FindMemberByCpfUseCaseImpl;
import br.com.associatedcooperative.usecase.service.implementations.CreateMemberUseCaseImpl;
import br.com.associatedcooperative.usecase.service.implementations.DeactivateOrActivateMemberByCpfUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class MemberControllerAdapter implements MemberControllerPort {

    private final CreateMemberUseCaseImpl createMemberUseCase;
    private final FindMemberByCpfUseCaseImpl findMemberByCpfUseCase;
    private final DeactivateOrActivateMemberByCpfUseCaseImpl deactivateOrActivateMemberByCpfUseCase;

    @Override
    public ResponseEntity<MemberResponseDto> createMember(MemberRequestDto dto, UriComponentsBuilder uriBuilder) {
        var responseDto = createMemberUseCase.createMember(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path(PathUtils.V1.Member.MEMBER + PathUtils.Member.FIND)
                        .buildAndExpand(responseDto.id())
                        .toUri())
                .body(responseDto);
    }

    @Override
    public ResponseEntity<MemberResponseDto> findMemberByCpf(String cpf) {
        return ResponseEntity.ok(findMemberByCpfUseCase.findMemberByCpf(cpf));
    }

    @Override
    public ResponseEntity<MemberResponseDto> deactivateOrActivateMember(String cpf, MemberStatus status) {
        return ResponseEntity
                .accepted()
                .body(deactivateOrActivateMemberByCpfUseCase.deactivateOrActivateMember(cpf, status));
    }
}
