package br.com.associatedcooperative.domain.meeting;

import br.com.associatedcooperative.domain.ruling.Ruling;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private MeetingStatus status;
    @OneToMany(mappedBy="meeting", fetch = FetchType.EAGER)
    private List<Ruling> rulings = new ArrayList<>();

    public Meeting(String name) {
        this.name = name;
        this.status = MeetingStatus.OPEN;
    }

    public Meeting closeMeeting(){
        this.setStatus(MeetingStatus.CLOSED);
        return this;
    }

    public boolean isOpen(){
        return this.status.equals(MeetingStatus.OPEN);
    }

}
