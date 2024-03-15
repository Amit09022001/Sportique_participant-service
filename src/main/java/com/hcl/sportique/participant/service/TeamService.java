package com.hcl.sportique.participant.service;

import com.hcl.sportique.participant.dto.TeamCreationRequest;
import com.hcl.sportique.participant.entity.Team;
import org.springframework.stereotype.Service;

@Service
public interface TeamService {
    Team createTeam(TeamCreationRequest request)throws Exception;
}
