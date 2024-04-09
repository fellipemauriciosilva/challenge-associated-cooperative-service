package br.com.associatedcooperative.usecase.service;

import br.com.associatedcooperative.usecase.dto.VoteKafka;
import br.com.associatedcooperative.usecase.dto.VoteRequestDto;

public interface CastVoteByCpfUseCase {
    void castVote(VoteRequestDto dto);
    void persistVote(VoteKafka voteKafka);
}
