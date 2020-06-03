package com.example.version.service.impl;

import com.example.version.model.Record;
import com.example.version.model.User;
import com.example.version.repository.RecordRepo;
import com.example.version.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecordServiceImpl extends RecordService {

    @Autowired
    private RecordRepo repo;

    @Override
    public List<Record> listALL() {
        return repo.findAll();
    }

    @Override
    public void update(Long id, Record object) {
        repo.update(id, object);
    }

    @Override
    public User save(Record object) {
        repo.save(object);
        return null;
    }

    @Override
    public Record get(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
