package com.example.version.service.impl;

import com.example.version.model.Coach;
import com.example.version.model.User;
import com.example.version.repository.CoachRepo;
import com.example.version.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CoachServiceImpl extends CoachService {

    @Autowired
    private CoachRepo repo;

    @Override
    public List<Coach> listALL() {
        return repo.findAll();
    }

    @Override
    public void update(Long id, Coach object) {
        repo.update(id, object);
    }

    @Override
    public User save(Coach object) {
        repo.save(object);
        return null;
    }


    @Override
    public Coach get(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
