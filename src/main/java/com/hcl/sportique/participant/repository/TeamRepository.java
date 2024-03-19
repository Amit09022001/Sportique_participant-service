package com.hcl.sportique.participant.repository;

import com.hcl.sportique.participant.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {

    public boolean existsByTeamNameAndSport(String teamName,String sport);
    public boolean existsBySport(String sport);
    public List<Team> findByOrganizationId(String organizationId);



}
