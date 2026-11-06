package com.example.team;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberService(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    public List<TeamMember> getActiveMembers() {
        return teamMemberRepository.findByIsActiveTrue();
    }

    public List<TeamMember> getAllMembers() {
        return teamMemberRepository.findAll();
    }

    public Optional<TeamMember> getMemberById(Long id) {
        return teamMemberRepository.findById(id);
    }

    public TeamMember saveMember(TeamMember member) {
        return teamMemberRepository.save(member);
    }

    public void deleteMember(Long id) {
        teamMemberRepository.deleteById(id);
    }
}
