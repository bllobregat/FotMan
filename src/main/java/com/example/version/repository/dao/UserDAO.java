package com.example.version.repository.dao;

import com.example.version.model.User;
import com.example.version.repository.dao.base.BaseDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends BaseDAO<User, Long> {

    public User findByEmail(String email);

    public List<String> findEmailsUsers();
}
