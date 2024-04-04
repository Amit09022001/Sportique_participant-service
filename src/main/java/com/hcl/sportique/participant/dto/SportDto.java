package com.hcl.sportique.participant.dto;

import lombok.Data;

import java.util.List;

@Data

public class SportDto {
    private String captainEmail;
    private List<String> captainSport;
}
