package br.com.associatedcooperative.usecase.service;

import br.com.associatedcooperative.usecase.dto.MemberResponseDto;

public interface FindMemberByCpfUseCase {
    MemberResponseDto findMemberByCpf(String cpf);
}
