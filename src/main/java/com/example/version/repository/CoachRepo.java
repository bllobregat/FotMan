package com.example.version.repository;

import com.example.version.model.Coach;
import com.example.version.repository.dao.CoachDAO;
import com.example.version.repository.mappers.CoachMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class CoachRepo implements CoachDAO {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private DataSource dataSource;
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
    public void save(Coach object) {
        try {
            String insert = String.format("INSERT INTO coach (name, surname, age, address, email, phoneNumber,licence)"
                            + " VALUES('%s','%s','%d','%s','%s','%s','%s')",
                    object.getName(), object.getSurname(), object.getAge(), object.getAddress(),
                    object.getEmail(), object.getPhoneNumber(), object.getLicence());
            jdbcTemplate.execute(insert);
            log.info(object.toString() + " SAVED IN DATABASE ");
        } catch (Exception e) {
            log.error(e);
        }
    }


    @Override
    public void update(Long id, Coach object) {
        String update = String.format("UPDATE coach SET name = '%s', surname = '%s'," +
                        " age = '%d', address = '%s', email = '%s', phoneNumber = '%s', licence = '%s'  " +
                        "WHERE idCoach = '%d'",
                object.getName(), object.getSurname(), object.getAge(), object.getAddress(), object.getEmail(),
                object.getPhoneNumber(), object.getLicence(), id);
        jdbcTemplate.execute(update);
        log.info(object.toString() + " UPDATED IN DATABASE ");
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            String sql = "delete from coach where IdCoach =" + id;
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
            String sql = "delete from coach";
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public List<Coach> findAll() {
        return jdbcTemplate.query("SELECT * FROM coach",
                new CoachMapper());
    }

    @Override
    public Coach findById(Long Id) {
        try {
            Object[] params = new Object[]{Id};
            return jdbcTemplate.queryForObject("SELECT * FROM coach WHERE idCoach = ?",
                    params, new CoachMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error(e);
            return null;
        }

    }

}
