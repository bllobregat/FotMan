package com.example.version.repository;

import com.example.version.model.Record;
import com.example.version.repository.dao.RecordDAO;
import com.example.version.repository.mappers.RecordMapper;
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
public class RecordRepo implements RecordDAO {

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
    public void save(Record object) {
        try {
            String insert = String.format("INSERT INTO record (idPlayer,seasons, matches, goals)"
                            + " VALUES('%d','%d','%d','%d')",
                    object.getIdPlayer(), object.getSeasons(), object.getMatches(), object.getGoals());
            jdbcTemplate.execute(insert);
            log.info(object.toString() + " SAVED IN DATABASE ");
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void update(Long id, Record object) {
        String update = String.format("UPDATE record SET seasons='%d'," +
                        "matches='%d', goals='%d' WHERE idPlayer= '%d'",
                object.getSeasons(), object.getMatches(), object.getGoals(), id);
        jdbcTemplate.execute(update);
        log.info(object.toString() + " UPDATED IN DATABASE ");
    }


    @Override
    public boolean deleteById(Long id) {
        try {
            String sql = "delete from record where idPlayer =" + id;
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
            String sql = "delete from record";
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public List<Record> findAll() {
        return jdbcTemplate.query("SELECT * FROM record",
                new RecordMapper());
    }

    @Override
    public Record findById(Long Id) {
        try {
            Object[] params = new Object[]{Id};
            return jdbcTemplate.queryForObject("SELECT * FROM record WHERE idPlayer = ?",
                    params, new RecordMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
