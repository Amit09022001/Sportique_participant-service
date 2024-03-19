package com.hcl.sportique.participant.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class IndividualMemberRequest {

    private String name;
    private String email;
    private String sport;
    private String gender;
}
