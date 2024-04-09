package br.com.associatedcooperative.infrastructure.persistence;

import br.com.associatedcooperative.domain.ruling.Ruling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulingRepository extends JpaRepository<Ruling, Long> {

    @Query("""
    select p from Ruling p
    where p.endTime < CURRENT_TIMESTAMP
    and p.status = 'STARTED'
    """)
    List<Ruling> findByFimBeforeNowAndStatus_Started();
}
