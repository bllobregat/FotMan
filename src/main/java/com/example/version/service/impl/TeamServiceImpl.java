package com.example.version.service.impl;

import com.example.version.model.Team;
import com.example.version.model.User;
import com.example.version.repository.TeamRepo;
import com.example.version.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamServiceImpl extends TeamService {

    @Autowired
    private TeamRepo repo;

    @Override
    public List<Team> listALL() {
        return repo.findAll();
    }

    @Override
    public void update(Long id, Team object) {
        repo.update(id, object);
    }

    @Override
    public User save(Team object) {
        repo.save(object);
        return null;
    }

    @Override
    public Team get(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
