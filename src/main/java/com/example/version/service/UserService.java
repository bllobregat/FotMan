package com.example.version.service;

import com.example.version.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class UserService implements BaseService<User, Long> {

    public abstract String authenticate(String email, String password);

    public abstract List<String> findUserEmails();

}