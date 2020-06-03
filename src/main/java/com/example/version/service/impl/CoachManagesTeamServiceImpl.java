package com.example.version.service.impl;

import com.example.version.model.CoachManagesTeam;
import com.example.version.model.User;
import com.example.version.repository.CoachManagesTeamRepo;
import com.example.version.service.CoachManagesTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CoachManagesTeamServiceImpl extends CoachManagesTeamService {

    @Autowired
    private CoachManagesTeamRepo repo;

    @Override
    public List<CoachManagesTeam> listALL() {
        return repo.findAll();
    }

    @Override
    public void update(Long id, CoachManagesTeam object) {
        repo.update(id, object);
    }

    @Override
    public User save(CoachManagesTeam object) {
        repo.save(object);
        return null;
    }

    @Override
    public CoachManagesTeam get(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<CoachManagesTeam> listCoachByTeam(Long idTeam) {
        return repo.listCoachByTeam(idTeam);
    }
}
