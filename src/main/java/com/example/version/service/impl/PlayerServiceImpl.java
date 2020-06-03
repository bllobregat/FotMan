package com.example.version.service.impl;

import com.example.version.model.Player;
import com.example.version.model.User;
import com.example.version.repository.PlayerRepo;
import com.example.version.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl extends PlayerService {

    @Autowired
    private PlayerRepo repo;

    @Override
    public List<Player> listALL() {
        return repo.findAll();
    }

    public List<Player> listByTeam() {
        return repo.findPlayerTeam();
    }


    @Override
    public void update(Long id, Player object) {
        repo.update(id, object);
    }

    @Override
    public User save(Player object) {
        repo.save(object);
        return null;
    }

    @Override
    public Player get(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
