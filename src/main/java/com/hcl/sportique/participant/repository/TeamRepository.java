package com.hcl.sportique.participant.repository;

import com.hcl.sportique.participant.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {

    public boolean existsByTeamNameAndSport(String teamName,String sport);
    public boolean existsBySport(String sport);

}
