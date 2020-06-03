package com.example.version.service.impl;

import com.example.version.model.Tactic;
import com.example.version.model.User;
import com.example.version.repository.TacticRepo;
import com.example.version.service.TacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TacticServiceImpl extends TacticService {

    @Autowired
    private TacticRepo repo;

    @Override
    public List<Tactic> listALL() {
        return repo.findAll();
    }

    @Override
    public void update(Long id, Tactic object) {
        repo.update(id, object);
    }

    @Override
    public User save(Tactic object) {
        repo.save(object);
        return null;
    }

    @Override
    public Tactic get(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
