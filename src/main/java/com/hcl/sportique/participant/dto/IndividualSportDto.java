package com.hcl.sportique.participant.dto;

import lombok.Data;

import java.util.List;

@Data
public class IndividualSportDto {
    private String email;
    private List<String> individualSport;
}
