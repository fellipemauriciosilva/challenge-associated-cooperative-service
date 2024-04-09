package br.com.associatedcooperative.infrastructure.persistence;

import br.com.associatedcooperative.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByCpf(String cpf);
}
