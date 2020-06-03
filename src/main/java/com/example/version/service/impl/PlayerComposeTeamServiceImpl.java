package com.example.version.service.impl;

import com.example.version.model.PlayerComposeTeam;
import com.example.version.model.User;
import com.example.version.repository.PlayerComposeTeamRepo;
import com.example.version.service.PlayerComposeTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerComposeTeamServiceImpl extends PlayerComposeTeamService {

    @Autowired
    private PlayerComposeTeamRepo repo;

    @Override
    public List<PlayerComposeTeam> listALL() {
        return repo.findAll();
    }

    @Override
    public void update(Long id, PlayerComposeTeam object) {
        repo.update(id, object);
    }

    @Override
    public User save(PlayerComposeTeam object) {
        repo.save(object);
        return null;
    }

    @Override
    public PlayerComposeTeam get(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<PlayerComposeTeam> listPlayerByTeam(Long idTeam) {
        return repo.playersForTeam(idTeam);
    }


}
