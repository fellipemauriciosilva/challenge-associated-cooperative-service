package br.com.associatedcooperative.usecase.dto;

import br.com.associatedcooperative.domain.member.Member;
import br.com.associatedcooperative.domain.member.MemberStatus;

public record MemberResponseDto(Long id, String name, String cpf, MemberStatus status) {
    public MemberResponseDto(Member member){
        this(member.getId(), member.getName(), member.getCpf(), member.getStatus());
    }
}
