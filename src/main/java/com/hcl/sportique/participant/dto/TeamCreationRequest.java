package com.hcl.sportique.participant.dto;

import com.hcl.sportique.participant.entity.TeamMember;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class TeamCreationRequest {

    private String teamName;
    private String sports;
    private List<TeamMemberDto> teamMemberList;

}
