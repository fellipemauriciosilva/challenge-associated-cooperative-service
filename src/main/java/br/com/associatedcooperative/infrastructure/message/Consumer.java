package br.com.associatedcooperative.infrastructure.message;

import br.com.associatedcooperative.usecase.dto.VoteKafka;
import br.com.associatedcooperative.usecase.service.implementations.CastVoteByCpfUseCaseImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class Consumer {
    private final CastVoteByCpfUseCaseImpl castVoteByCpfUseCase;

    @KafkaListener(topics = "${app.kafka.producer.topics}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){
        castVoteByCpfUseCase
                .persistVote(VoteKafka
                        .builder()
                        .voteJson(payload.value())
                        .build());

    }
}
