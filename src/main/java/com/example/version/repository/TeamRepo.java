package com.example.version.repository;

import com.example.version.model.Team;
import com.example.version.repository.dao.TeamDAO;
import com.example.version.repository.mappers.TeamMapper;
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
public class TeamRepo implements TeamDAO {

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
    public void save(Team object) {
        try {
            String insert = String.format("INSERT INTO team(name, division,stadium)"
                            + " VALUES('%s','%s','%s')",
                    object.getName(), object.getDivision(), object.getStadium());
            jdbcTemplate.execute(insert);
            log.info(object.toString() + " SAVED IN DATABASE ");
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void update(Long id, Team object) {
        String update = String.format("UPDATE team SET name = '%s',division= '%s', stadium = '%s'" +
                        "WHERE idTeam = '%d'",
                object.getName(), object.getDivision(), object.getStadium(), id);
        jdbcTemplate.execute(update);
        log.info(object.toString() + " UPDATED IN DATABASE ");

    }

    @Override
    public boolean deleteById(Long id) {
        try {
            String sql = "delete from team where IdTeam=" + id;
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
            String sql = "delete from team";
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public List<Team> findAll() {
        return jdbcTemplate.query("SELECT * FROM team",
                new TeamMapper());
    }

    @Override
    public Team findById(Long id) {
        try {
            Object[] params = new Object[]{id};
            return jdbcTemplate.queryForObject("SELECT * FROM team WHERE idTeam = ?",
                    params, new TeamMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public List<Team> teamsWitchCoach() {
        String SQL = "SELECT p.name,division,age,height,weight,nationality,t.name team " +
                "FROM player p,team t,player_compose_team pt " +
                "WHERE p.idPlayer=pt.idPlayer AND t.idTeam=pt.idTeam;";
        List<Team> teams = jdbcTemplate.query(SQL,
                rs -> {
                    List<Team> teamList = new ArrayList<>();
                    while (rs.next()) {
                        Team team = new Team();
                        team.setName(rs.getString("name"));
                        team.setDivision(rs.getString("division"));
                        team.setStadium(rs.getString("stadium"));
                        team.setCoach(rs.getString("coach"));
                        teamList.add(team);
                    }
                    return teamList;
                });
        return teams;

    }
}
