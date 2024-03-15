package com.hcl.sportique.participant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;
    private String teamName;


    private String sport;


//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "team_id",referencedColumnName="id")
//    private List<TeamMember> teamMembers;

    @OneToMany(mappedBy = "teams")
    private Set<TeamMember> members = new HashSet<>();
}
