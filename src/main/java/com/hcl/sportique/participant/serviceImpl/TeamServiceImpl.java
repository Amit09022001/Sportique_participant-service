package com.hcl.sportique.participant.serviceImpl;

import com.hcl.sportique.participant.Exception.DuplicateValueException;
import com.hcl.sportique.participant.Exception.NullValueException;
import com.hcl.sportique.participant.dto.SportDto;
import com.hcl.sportique.participant.dto.TeamCreationRequest;
import com.hcl.sportique.participant.dto.TeamMemberDto;
import com.hcl.sportique.participant.dto.TeamRequest;
import com.hcl.sportique.participant.entity.Team;
import com.hcl.sportique.participant.entity.TeamMember;
import com.hcl.sportique.participant.repository.TeamMemberRepository;
import com.hcl.sportique.participant.repository.TeamRepository;
import com.hcl.sportique.participant.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;


    public List<Team> getAllTeams() {

        return teamRepository.findAll();
    }


//    private void validateEmail(Team team) throws Exception{
//
//
//
//        if (teamMembers != null) {
//            for (TeamMember member : teamMembers) {
//                String email = member.getEmail();
//
//                if(email == null) {
//                    throw new NullValueException("TeamMembers email cannot be null or empty");
//                }
//
//            }
//        }
//    }

    //Team Name unique for similar sport
    private void validateUniqueTeamNameForSport(Team team) throws Exception {
        String sport = team.getSport();
        String teamName = team.getTeamName();
        if (teamName == null || teamName.length() == 0) {
            throw new NullValueException("TeamName cannot be null or empty");
        }
        if (sport == null || sport.length() == 0) {
            throw new NullValueException("Sports name cannot be null or empty");
        }
        if (teamRepository.existsByTeamNameAndSport(teamName, sport)) {
            throw new DuplicateValueException("Team name must be unique for similar Sport");
        }


    }


    @Transactional
    public Team createTeam(TeamCreationRequest request) throws Exception {

        Team team = new Team();


        List<TeamMemberDto> players = request.getTeamMemberList();
        List<TeamMember> updatedPlayers = new ArrayList<>();
        if (players != null) {
//                log.info("Player details is checking ...");
            for (TeamMemberDto player : players) {


                Optional<TeamMember> existingPlayer = teamMemberRepository.findByEmailAndSports(player.getEmail(), request.getSports());
                if (existingPlayer.isPresent()) {
                    System.out.println("Duplicate sports");
                    throw new DuplicateValueException(String.format("Player with email %s is already registered with sports %s  !!.", player.getEmail(), request.getSports()));
                } else {
                    TeamMember player1 = TeamMember.builder()
                            .email(player.getEmail().toLowerCase())
                            .sports(request.getSports())
                            .name(player.getName().toLowerCase())
                            .gender(player.getGender().toLowerCase())


                            .build();

                    updatedPlayers.add(player1);
//                        teamMemberRepository.save(player1);

                }

            }
            team.setTeamName(request.getTeamName());
            team.setSport(request.getSports());
            team.setOrganizationId(request.getOrganizationId());
            team.setCaptainEmail(request.getCaptainEmail());
            team.setMembers(updatedPlayers);
            validateUniqueTeamNameForSport(team);

            return teamRepository.save(team);


        }else{
            return null;
        }


    }

    public List<TeamRequest> lsitAllTeam(){
        List<TeamRequest> teamCreationRequests = new ArrayList<>();
        List<Team> teams = teamRepository.findAll();
        teams.stream().forEach(listOfTeams ->{
            TeamRequest getTeamList = new TeamRequest();

            getTeamList.setTeamName(listOfTeams.getTeamName());
            getTeamList.setSports(listOfTeams.getSport());


           List<TeamMemberDto> teamMemberDtos = new ArrayList<>();
           List<TeamMember> teamMembers = listOfTeams.getMembers();
           teamMembers.stream().forEach(listMembers ->{
               TeamMemberDto membersOfTeam = new TeamMemberDto();
               membersOfTeam.setName(listMembers.getName());
               membersOfTeam.setEmail(listMembers.getEmail());
               membersOfTeam.setGender(listMembers.getGender());
               teamMemberDtos.add(membersOfTeam);
               System.out.println(teamMemberDtos.toString());
           });
           getTeamList.setTeamMemberList(teamMemberDtos);



           teamCreationRequests.add(getTeamList);
        });
        return teamCreationRequests;
    }

    public List<TeamRequest> listOfTeamByOrganizationId(String organizationId){

        List<Team> teamByOrganizationId = teamRepository.findByOrganizationId(organizationId);
        List<TeamRequest> teamCreationRequests = new ArrayList<>();
        teamByOrganizationId.stream().forEach(listOfTeams ->{
            TeamRequest getTeamList = new TeamRequest();

            getTeamList.setTeamName(listOfTeams.getTeamName());
            getTeamList.setSports(listOfTeams.getSport());


            List<TeamMemberDto> teamMemberDtos = new ArrayList<>();
            List<TeamMember> teamMembers = listOfTeams.getMembers();
            teamMembers.stream().forEach(listMembers ->{
                TeamMemberDto membersOfTeam = new TeamMemberDto();
                membersOfTeam.setName(listMembers.getName());
                membersOfTeam.setEmail(listMembers.getEmail());
                membersOfTeam.setGender(listMembers.getGender());
                teamMemberDtos.add(membersOfTeam);
                System.out.println(teamMemberDtos.toString());
            });
            getTeamList.setTeamMemberList(teamMemberDtos);



            teamCreationRequests.add(getTeamList);
        });


        return teamCreationRequests;


    }

    @Override
    public SportDto getSportByCaptainEmail(String email) {
        SportDto sportDto=new SportDto();
        List<String> sportDetails=teamRepository.findSportByCaptainEmail(email);
        sportDto.setCaptainEmail(email);
        sportDto.setCaptainSport(sportDetails);
        return sportDto;
    }
}