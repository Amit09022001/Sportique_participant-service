package com.hcl.sportique.participant.service;

import com.hcl.sportique.participant.entity.IndividualSportRegister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IndividualSportRegisterService {
    boolean isUserAlreadyRegistered(String email, String sport);

    List<IndividualSportRegister> getAllRegistrations();

    IndividualSportRegister save(IndividualSportRegister registration);

}
