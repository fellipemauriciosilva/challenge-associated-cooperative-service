package br.com.associatedcooperative.usecase.service;

import br.com.associatedcooperative.domain.member.MemberStatus;
import br.com.associatedcooperative.usecase.dto.MemberResponseDto;

public interface DeactivateOrActivateMemberByCpfUseCase {
    MemberResponseDto deactivateOrActivateMember(String cpf, MemberStatus status);
}
