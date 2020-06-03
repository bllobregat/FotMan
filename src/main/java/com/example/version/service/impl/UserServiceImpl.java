package com.example.version.service.impl;

import com.example.version.model.User;
import com.example.version.repository.UserRepo;
import com.example.version.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends UserService {

    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<User> listALL() {
        return repo.findAll();
    }

    @Override
    public void update(Long id, User object) {
        repo.update(id, object);
    }

    @Override
    public User save(User object) {
        repo.save(object);
        return object;
    }

    @Override
    public User get(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }


    @Override
    public String authenticate(String email, String password) {
        User user = repo.findByEmail(email);
        if (user == null) {
            return "";
        } else {
            if (user.getRole().equals("user") && encoder.matches(password, user.getPassword()))
                return "user";
            else if (user.getRole().equals("admin") && encoder.matches(password, user.getPassword()))
                return "admin";
            else {
                return "";
            }
        }
    }


    @Override
    public List<String> findUserEmails() {
        return repo.findEmailsUsers();
    }


}
