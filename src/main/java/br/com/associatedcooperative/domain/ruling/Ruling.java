package br.com.associatedcooperative.domain.ruling;

import br.com.associatedcooperative.domain.meeting.Meeting;
import br.com.associatedcooperative.domain.vote.Vote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ruling")
public class Ruling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    private RulingStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ruling_vote",
            joinColumns = @JoinColumn(name = "ruling_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id"))
    private List<Vote> votes = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="meeting_id", nullable=false)
    private Meeting meeting;

    public Ruling(String description, LocalDateTime endTime, Meeting meeting) {
        this.description = description;
        this.endTime = endTime;
        this.meeting = meeting;
        this.status = RulingStatus.STARTED;
        this.startTime = LocalDateTime.now();
    }

    public Ruling closeRuling(){
        this.setStatus(RulingStatus.FINISHED);
        return this;
    }

    public Ruling startOrCloseRuling(RulingStatus status){
        this.setStatus(status);
        return this;
    }

    public boolean isStarted(){
        return this.status.equals(RulingStatus.STARTED);
    }

    public void addVote(Vote vote){
        this.votes.add(vote);
    }
}
