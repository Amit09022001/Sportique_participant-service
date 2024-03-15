package com.hcl.sportique.participant.controller;

import com.hcl.sportique.participant.dto.TeamCreationRequest;
import com.hcl.sportique.participant.entity.Team;
import com.hcl.sportique.participant.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;


    @PostMapping("/registered")
    public ResponseEntity<Object> createTeam(@RequestBody TeamCreationRequest request) throws Exception {
        try {
            Team team = teamService.createTeam(request);
            return new ResponseEntity<>(team, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }
}
