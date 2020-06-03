package com.example.version.repository;

import com.example.version.model.Training;
import com.example.version.repository.dao.TrainingDAO;
import com.example.version.repository.mappers.TrainingMapper;
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
public class TrainingRepo implements TrainingDAO {

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
    public void save(Training object) {
        try {
            String insert = String.format("INSERT INTO training(idPlayer,type, duration, exercises,personal_notes)"
                            + " VALUES('%d','%s','%s','%s','%s')",
                    object.getIdPlayer(), object.getType(), object.getDuration(),
                    object.getExercises(), object.getPersonalNotes());
            jdbcTemplate.execute(insert);
            log.info(object.toString() + " SAVED IN DATABASE ");
        } catch (Exception e) {
            log.error(e);
        }
    }


    @Override
    public void update(Long id, Training object) {
        String update = String.format("UPDATE training SET type = '%s', duration= '%s'," +
                        "exercises= '%s',personal_notes = '%s'   " +
                        "WHERE idPlayer = '%d'",
                object.getType(), object.getDuration(), object.getExercises(), object.getPersonalNotes(), id);
        jdbcTemplate.execute(update);
        log.info(object.toString() + " UPDATED IN DATABASE ");

    }

    @Override
    public boolean deleteById(Long id) {
        try {
            String sql = "delete from training where IdPlayer=" + id;
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
            String sql = "delete from training";
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public List<Training> findAll() {
        return jdbcTemplate.query("SELECT * FROM training",
                new TrainingMapper());
    }

    @Override
    public Training findById(Long Id) {
        try {
            Object[] params = new Object[]{Id};
            return jdbcTemplate.queryForObject("SELECT * FROM training WHERE idPlayer = ?",
                    params, new TrainingMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error(e);
            return null;
        }
    }
}
