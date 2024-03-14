package com.hcl.sportique.participant.serviceImpl;

import com.hcl.sportique.participant.entity.IndividualSportRegister;
import com.hcl.sportique.participant.repository.IndividualSportRegisterRepository;
import com.hcl.sportique.participant.service.IndividualSportRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualSportRegisterServiceImpl implements IndividualSportRegisterService {
    @Autowired
    private IndividualSportRegisterRepository registerRepository;
    public boolean isUserAlreadyRegistered(String email, String sport) {
        return registerRepository.existsByEmailAndSport(email, sport);
    }

    public List<IndividualSportRegister> getAllRegistrations() {
        return registerRepository.findAll();
    }
    public IndividualSportRegister save(IndividualSportRegister registration) {
        return registerRepository.save(registration);
    }
}
