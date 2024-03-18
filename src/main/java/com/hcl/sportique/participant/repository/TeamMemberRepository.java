package com.hcl.sportique.participant.repository;

import com.hcl.sportique.participant.entity.Team;
import com.hcl.sportique.participant.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember,Integer> {
//    @Query("SELECT tm.teams FROM TeamMember tm WHERE tm.email = :email AND tm.teams.sport = :sports")
//    List<Team> findTeamsBySportAndEmail(String sports, String email);

//    @Override
//    Optional<TeamMember> findById(Integer integer);

    Optional<TeamMember>findByEmailAndSports(String email, String sports);
}
