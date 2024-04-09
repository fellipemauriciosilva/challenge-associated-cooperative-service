package br.com.associatedcooperative.domain.member;


import br.com.associatedcooperative.domain.vote.Vote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_vote",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id"))
    private List<Vote> votes = new ArrayList<>();

    public Member(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
        this.status = MemberStatus.ABLE_TO_VOTE;
    }

    public Member inactivateOrActivate(MemberStatus status){
        this.setStatus(status);
        return this;
    }

    public boolean canVote(){
        return this.status.equals(MemberStatus.ABLE_TO_VOTE);
    }

    public void addVote(Vote vote){
        this.votes.add(vote);
    }
}
