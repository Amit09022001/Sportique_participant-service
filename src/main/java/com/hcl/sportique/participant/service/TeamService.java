package com.hcl.sportique.participant.service;

import com.hcl.sportique.participant.dto.SportDto;
import com.hcl.sportique.participant.dto.TeamCreationRequest;
import com.hcl.sportique.participant.dto.TeamRequest;
import com.hcl.sportique.participant.entity.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    Team createTeam(TeamCreationRequest request)throws Exception;
    List<TeamRequest> lsitAllTeam();

    List<TeamRequest> listOfTeamByOrganizationId(String organizationId);
    public SportDto getSportByCaptainEmail(String email);
}
