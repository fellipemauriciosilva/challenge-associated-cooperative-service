package br.com.associatedcooperative.infrastructure.message;

import br.com.associatedcooperative.usecase.dto.VoteKafka;
import br.com.associatedcooperative.usecase.dto.VoteRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Producer {
    @Value("${app.kafka.producer.topics}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(VoteRequestDto voteDto) {
        try {
            this.kafkaTemplate.send(this.topic,
                    VoteKafka
                            .builder()
                            .vote(voteDto)
                            .build()
                            .toJson());

        } catch (Exception ex){
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }
}
