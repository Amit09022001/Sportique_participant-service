package com.hcl.sportique.participant.repository;

import com.hcl.sportique.participant.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember,Integer> {
}
