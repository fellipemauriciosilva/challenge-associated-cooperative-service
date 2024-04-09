package br.com.associatedcooperative.infrastructure.persistence;

import br.com.associatedcooperative.domain.meeting.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
