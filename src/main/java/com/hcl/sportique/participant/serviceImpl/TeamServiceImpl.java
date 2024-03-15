package com.hcl.sportique.participant.serviceImpl;

import com.hcl.sportique.participant.Exception.DuplicateValueException;
import com.hcl.sportique.participant.Exception.NullValueException;
import com.hcl.sportique.participant.dto.TeamCreationRequest;
import com.hcl.sportique.participant.entity.Team;
import com.hcl.sportique.participant.entity.TeamMember;
import com.hcl.sportique.participant.repository.TeamMemberRepository;
import com.hcl.sportique.participant.repository.TeamRepository;
import com.hcl.sportique.participant.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;


    public List<Team> getAllTeams(){

        return teamRepository.findAll();
    }




    private void validateEmail(Team team) throws Exception{


        Set<TeamMember> teamMembers = team.getMembers();
        if (teamMembers != null) {
            for (TeamMember member : teamMembers) {
                String email = member.getEmail();

                if(email == null) {
                    throw new NullValueException("TeamMembers email cannot be null or empty");
                }

            }
        }
    }

    //Team Name unique for similar sport
    private void validateUniqueTeamNameForSport(Team team) throws Exception {
        String sport = team.getSport();
        String teamName = team.getTeamName();
        if(teamName==null || teamName.length()==0) {
            throw new NullValueException("TeamName cannot be null or empty");
        }
        if(sport==null || sport.length()==0) {
            throw new NullValueException("Sports name cannot be null or empty");
        }
        if(teamRepository.existsByTeamNameAndSport(teamName,sport)) {
            throw new DuplicateValueException("Team name must be unique for similar Sport");
        }


    }




    @Transactional
    public Team createTeam(TeamCreationRequest request) throws Exception {

        try {
            Team team = new Team();
            team.setTeamName(request.getTeamName());
            team.setSport(request.getSports());
            validateEmail(team);
            validateUniqueTeamNameForSport(team);
            team = teamRepository.save(team);

            for (TeamMember member : request.getTeamMemberList()) {
                // Check if the member's email exists in the same sport for another team

                List<Team> teamsWithSameSport = teamMemberRepository.findTeamsBySportAndEmail(request.getSports(), member.getEmail());
                if (!teamsWithSameSport.isEmpty()) {
                    throw new IllegalArgumentException("Error: Member with email " + member.getEmail() + " already registered for sport " + request.getSports() + " in another team.");
                }

                TeamMember mem= new TeamMember();
               mem.setName(member.getName());
               mem.setEmail(member.getEmail());
               mem.setGender(member.getGender());
               mem.setTeams(team);
               team.getMembers().add(mem);





                teamMemberRepository.save(member);
            }

            return team;
        } catch (IllegalArgumentException e) {
            // Log the error or handle it accordingly
            throw new IllegalArgumentException("Error creating team: " + e.getMessage());
        }
    }

}
