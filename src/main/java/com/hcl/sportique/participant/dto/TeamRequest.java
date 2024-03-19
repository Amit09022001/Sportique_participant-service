package com.hcl.sportique.participant.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class TeamRequest {
    private String teamName;
    private String sports;
    private List<TeamMemberDto> teamMemberList;
}
