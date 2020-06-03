package com.example.version.repository;

import com.example.version.model.CoachManagesTeam;
import com.example.version.repository.dao.CoachManagesTeamDAO;
import com.example.version.repository.mappers.CoachManagesTeamMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CoachManagesTeamRepo implements CoachManagesTeamDAO {

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
    public void save(CoachManagesTeam object) {
        try {
            String insert = String.format("INSERT INTO coach_manage_team (idCoach,idTeam,contract_starts,contract_ends)"
                            + " VALUES('%d','%d','%s','%s')",
                    object.getIdCoach(), object.getIdTeam(),
                    object.getContractStarts(), object.getContractEnds());
            jdbcTemplate.execute(insert);
            log.info(object.toString() + " SAVED IN DATABASE ");
        } catch (Exception e) {
            log.error(e);
        }
    }


    @Override
    public void update(Long id, CoachManagesTeam object) {
        String update = String.format("UPDATE coach_manage_team SET " +
                        "idTeam='%d', contract_starts = '%s', contract_ends = '%s'" +
                        "WHERE idCoach = '%d'",
                object.getIdTeam(), object.getContractStarts(), object.getContractEnds(), id);
        jdbcTemplate.execute(update);
        log.info(object.toString() + " UPDATED IN DATABASE ");
    }


    @Override
    public boolean deleteById(Long id) {
        try {
            String sql = String.format("delete from coach_manage_team where idCoach= '%d'", id);
            jdbcTemplate.execute(sql);
            log.info(id + " ERASED FROM DATABASE ");
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        try {
            String sql = "delete from coach_manage_team";
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }


    @Override
    public List<CoachManagesTeam> findAll() {
        return jdbcTemplate.query("SELECT * FROM coach_manage_team",
                new CoachManagesTeamMapper());
    }

    @Override
    public CoachManagesTeam findById(Long Id) {
        try {
            Object[] params = new Object[]{Id};
            return jdbcTemplate.queryForObject("SELECT * FROM coach_manage_team WHERE idCoach = ?",
                    params, new CoachManagesTeamMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public List<CoachManagesTeam> listCoachByTeam(Long idTeam) {
        String sql = "SELECT c.idCoach,c.name,c.surname,ct.contract_starts,ct.contract_ends " +
                "FROM coach c,coach_manage_team ct,team t " +
                "WHERE (ct.idTeam=" +idTeam+") = t.idTeam AND ct.idCoach = c.idCoach;";
        List<CoachManagesTeam> coaches = jdbcTemplate.query(sql,
                rs -> {
                    List<CoachManagesTeam> coachManagesTeams = new ArrayList<>();
                    while (rs.next()) {
                        CoachManagesTeam coachManagesTeam = new CoachManagesTeam();
                        coachManagesTeam.setIdCoach(rs.getLong("idCoach"));
                        coachManagesTeam.setName(rs.getString("name"));
                        coachManagesTeam.setSurname(rs.getString("surname"));
                        coachManagesTeam.setContractStarts(rs.getString("contract_starts"));
                        coachManagesTeam.setContractEnds(rs.getString("contract_ends"));
                        coachManagesTeams.add(coachManagesTeam);
                    }
                    return coachManagesTeams;
                });
        return coaches;
    }
}
