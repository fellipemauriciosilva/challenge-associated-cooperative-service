package br.com.associatedcooperative.usecase.service;


import br.com.associatedcooperative.usecase.dto.MemberRequestDto;
import br.com.associatedcooperative.usecase.dto.MemberResponseDto;

public interface CreateMemberUseCase {
    MemberResponseDto createMember(MemberRequestDto dto);
}
