package br.com.associatedcooperative.domain.vote;

import br.com.associatedcooperative.domain.member.Member;
import br.com.associatedcooperative.domain.ruling.Ruling;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VoteValue voteValue;
    @ManyToMany(mappedBy = "votes")
    private List<Member> members = new ArrayList<>();
    @ManyToMany(mappedBy = "votes")
    private List<Ruling> rulings = new ArrayList<>();
}
