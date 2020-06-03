package com.example.version.repository;

import com.example.version.model.User;
import com.example.version.repository.dao.UserDAO;
import com.example.version.repository.mappers.UserMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserRepo implements UserDAO {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private DataSource dataSource;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User object) {
        try {
            String insert = String.format("INSERT INTO user (name, surname, age, address, email, phoneNumber," +
                            "role,password,enabled)"
                            + " VALUES('%s','%s','%d','%s','%s','%s','%s','%s',%b)",
                    object.getName(), object.getSurname(), object.getAge(), object.getAddress(),
                    object.getEmail(), object.getPhoneNumber(), object.getRole(),
                    object.getPassword(),object.getEnabled());
            jdbcTemplate.execute(insert);
            log.info(object.toString() + " SAVED IN DATABASE ");

        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void update(Long id, User object) {
        String update = String.format("UPDATE user SET name = '%s', surname = '%s'," +
                        " age = '%d', address = '%s', email = '%s', phoneNumber = '%s',role = '%s',enabled=%b  " +
                        "WHERE idUser = '%d'",
                object.getName(), object.getSurname(), object.getAge(), object.getAddress(), object.getEmail(),
                object.getPhoneNumber(), object.getRole(),object.getEnabled(), id);
        jdbcTemplate.execute(update);
        log.info(object.toString() + " UPDATED IN DATABASE ");
    }


    @Override
    public boolean deleteById(Long id) {
        try {
            String sql = "delete from user where IdUser =" + id;
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        try {
            String sql = "delete from user";
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM user",
                new UserMapper());
    }

    @Override
    public User findById(Long Id) {
        try {
            Object[] params = new Object[]{Id};
            return jdbcTemplate.queryForObject("SELECT * FROM user WHERE idUser = ?",
                    params, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error(e);
            return null;
        }
    }


    @Override
    public User findByEmail(String email) {
        try {
            Object[] params = new Object[]{email};
            return jdbcTemplate.queryForObject("SELECT * FROM user WHERE email = ?",
                    params, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public List<String> findEmailsUsers() {
        try {
            String query = "Select email from user";
            return jdbcTemplate.queryForList(query, String.class);
        } catch (Exception e) {
            log.error(e);
            return null;
        }


    }


}
