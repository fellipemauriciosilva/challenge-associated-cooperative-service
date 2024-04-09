package br.com.associatedcooperative.usecase.dto;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VoteKafka {
    private String voteValue;
    private String cpf;
    private Long ruling;

    public String toJson(){
        return new Gson().toJson(this);
    }

    public static VoteProducerBuilder builder(){
        return new VoteProducerBuilder();
    }

    public static class VoteProducerBuilder {
        private VoteRequestDto voteRequestDto;
        private String json;

        public VoteProducerBuilder vote(VoteRequestDto voteRequestDto){
            this.voteRequestDto = voteRequestDto;
            return this;
        }

        public VoteProducerBuilder voteJson(String json){
            this.json = json;
            return this;
        }

        public VoteKafka build(){
            if(!Objects.isNull(this.voteRequestDto)) return new VoteKafka(this.voteRequestDto.voteValue().name(),
                    this.voteRequestDto.cpf(),
                    this.voteRequestDto.rulingId());
            else if (!this.json.isEmpty()) return new Gson().fromJson(this.json, VoteKafka.class);
            return new VoteKafka();
        }
    }
}
