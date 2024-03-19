package com.hcl.sportique.participant.serviceImpl;

import com.hcl.sportique.participant.dto.IndividualMemberRequest;
import com.hcl.sportique.participant.entity.IndividualSportRegister;
import com.hcl.sportique.participant.repository.IndividualSportRegisterRepository;
import com.hcl.sportique.participant.service.IndividualSportRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<IndividualMemberRequest> getMemberByOrganization(String organizationId){
        List<IndividualSportRegister> individualSportRegisters = registerRepository.findByOrganizationId(organizationId);
        List<IndividualMemberRequest> singleMember = new ArrayList<>();
        individualSportRegisters.stream().forEach(singleUser ->{
            IndividualMemberRequest singleUserlist = new IndividualMemberRequest();
            singleUserlist.setName(singleUser.getName());
            singleUserlist.setEmail(singleUser.getEmail());
            singleUserlist.setSport(singleUser.getSport());
            singleUserlist.setGender(singleUser.getGender());

            singleMember.add(singleUserlist);
        });

        return singleMember;
    }
}
