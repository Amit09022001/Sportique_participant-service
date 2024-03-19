package com.hcl.sportique.participant.repository;

import com.hcl.sportique.participant.entity.IndividualSportRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividualSportRegisterRepository  extends JpaRepository<IndividualSportRegister,Integer> {
    boolean existsByEmailAndSport(String email, String sport);

    List<IndividualSportRegister> findByOrganizationId(String organizationId);
}
