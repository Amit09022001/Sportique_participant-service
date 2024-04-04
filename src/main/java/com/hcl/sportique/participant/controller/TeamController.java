package com.hcl.sportique.participant.controller;

import com.hcl.sportique.participant.dto.SportDto;
import com.hcl.sportique.participant.dto.TeamCreationRequest;
import com.hcl.sportique.participant.dto.TeamRequest;
import com.hcl.sportique.participant.entity.Team;
import com.hcl.sportique.participant.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/show/teams")
    public ResponseEntity<List<TeamRequest>> getAllTeams(){
        List<TeamRequest> teamCreationRequests = teamService.lsitAllTeam();
        return new ResponseEntity<>(teamCreationRequests, HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}/teamsById")
    public ResponseEntity<?> getTeamsByOrganizationId(@PathVariable String organizationId){
        List<TeamRequest> teamList = teamService.listOfTeamByOrganizationId(organizationId);
        if(teamList.isEmpty()){
            return  new ResponseEntity<>("Team id is not available", HttpStatus.BAD_REQUEST);
        }else {

            return new ResponseEntity<>(teamList, HttpStatus.OK);
        }
    }

    @GetMapping("/getAllSport/{captainEmail}")
    public ResponseEntity<?> getSportNameByCaptainEmail(@PathVariable String captainEmail) {
        SportDto dto = teamService.getSportByCaptainEmail(captainEmail);
        if (dto.getCaptainEmail().isEmpty() || dto.getCaptainSport().isEmpty()) {
            return new ResponseEntity<>("Data Not found !!", HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(dto, HttpStatus.OK);

    }
}
