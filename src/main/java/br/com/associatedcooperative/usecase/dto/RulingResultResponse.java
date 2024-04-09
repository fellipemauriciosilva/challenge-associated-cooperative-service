package br.com.associatedcooperative.usecase.dto;

public record RulingResultResponse(Long rulingId,
                                   String rulingDescription,
                                   Long yesVote,
                                   Long noVote,
                                   String result,
                                   String meetingName) {

}
