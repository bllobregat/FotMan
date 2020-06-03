package com.example.version.service.impl;

import com.example.version.model.Training;
import com.example.version.model.User;
import com.example.version.repository.TrainingRepo;
import com.example.version.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrainingServiceImpl extends TrainingService {

    @Autowired
    private TrainingRepo repo;

    @Override
    public List<Training> listALL() {
        return repo.findAll();
    }

    @Override
    public void update(Long id, Training object) {
        repo.update(id, object);
    }

    @Override
    public User save(Training object) {
        repo.save(object);
        return null;
    }

    @Override
    public Training get(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
