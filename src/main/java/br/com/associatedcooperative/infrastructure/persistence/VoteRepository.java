package br.com.associatedcooperative.infrastructure.persistence;

import br.com.associatedcooperative.domain.member.Member;
import br.com.associatedcooperative.domain.ruling.Ruling;
import br.com.associatedcooperative.domain.vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByMembersAndRulings(List<Member> members, List<Ruling> rulings);
}
