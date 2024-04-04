package com.hcl.sportique.participant.controller;

import com.hcl.sportique.participant.dto.IndividualMemberRequest;
import com.hcl.sportique.participant.dto.IndividualSportDto;
import com.hcl.sportique.participant.dto.TeamRequest;
import com.hcl.sportique.participant.entity.IndividualSportRegister;
import com.hcl.sportique.participant.service.IndividualSportRegisterService;
import com.hcl.sportique.participant.serviceImpl.IndividualSportRegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/individual")
@CrossOrigin(origins = "*")
public class IndividualSportRegisterController {
    @Autowired
    private IndividualSportRegisterService individualSportRegisterService;
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> createRegistration(@RequestBody IndividualSportRegister registration) {
        String email = registration.getEmail();
        String sport = registration.getSport();
        if (individualSportRegisterService.isUserAlreadyRegistered(email,sport)) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Already Registered !!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        // Save the registration to the database
        individualSportRegisterService.save(registration);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/alldata")
    public ResponseEntity<List<IndividualSportRegister>> getAllRegistrations() {
        List<IndividualSportRegister> registrations = individualSportRegisterService.getAllRegistrations();
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/organization/{organizationId}/individualUser")
    public ResponseEntity<?> getTeamsByOrganizationId(@PathVariable String organizationId){
        List<IndividualMemberRequest> teamList = individualSportRegisterService.getMemberByOrganization(organizationId);
        if(teamList.isEmpty()){
            return  new ResponseEntity<>("Team id is not available", HttpStatus.BAD_REQUEST);
        }else {

            return new ResponseEntity<>(teamList, HttpStatus.OK);
        }
    }

    @GetMapping("/getAllSport/{email}")
    public ResponseEntity<?> getSportNameByEmailId(@PathVariable String email){
        IndividualSportDto dto=individualSportRegisterService.getAllSportByEmailId(email);
        if(dto.getIndividualSport().isEmpty() || dto.getEmail().isEmpty()){
            return new ResponseEntity<>("Data Not found!!",HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(dto,HttpStatus.OK);
    }



}
