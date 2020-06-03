package com.example.version;

import com.example.version.controller.javafx.users.UsersController;
import com.example.version.repository.UserRepo;
import com.example.version.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FotManApplicationTests {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    private UserService service;
    @Autowired
    private UsersController controller;

    @Test
    void contextLoads() {
    }

}
