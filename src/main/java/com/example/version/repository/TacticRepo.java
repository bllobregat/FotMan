package com.example.version.repository;

import com.example.version.model.Tactic;
import com.example.version.repository.dao.TacticDAO;
import com.example.version.repository.mappers.TacticMapper;
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
public class TacticRepo implements TacticDAO {

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
    public void save(Tactic object) {
        try {
            String insert = String.format("INSERT INTO tactic (idTeam,formation,type,personal_notes)"
                            + " VALUES('%d','%s','%s','%s')",
                    object.getIdTeam(), object.getFormation(), object.getType(), object.getPersonalNotes());
            jdbcTemplate.execute(insert);
            log.info(object.toString() + " SAVED IN DATABASE ");
        } catch (Exception e) {
            log.error(e);
        }
    }


    @Override
    public void update(Long id, Tactic object) {
        String update = String.format("UPDATE tactic SET formation = '%s', type = '%s', personal_notes = '%s'" +
                        "WHERE idTeam = '%d'",
                object.getFormation(), object.getType(), object.getPersonalNotes(), id);
        jdbcTemplate.execute(update);
        log.info(object.toString() + " UPDATED IN DATABASE ");
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            String sql = "delete from tactic where idTeam =" + id;
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
            String sql = "delete from tactic";
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }


    @Override
    public List<Tactic> findAll() {
        return jdbcTemplate.query("SELECT * FROM tactic",
                new TacticMapper());
    }

    @Override
    public Tactic findById(Long Id) {
        try {
            Object[] params = new Object[]{Id};
            return jdbcTemplate.queryForObject("SELECT * FROM tactic WHERE idTeam = ?",
                    params, new TacticMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error(e);
            return null;
        }
    }
}
