package com.example.version.repository;

import com.example.version.model.PlayerComposeTeam;
import com.example.version.repository.dao.PlayerComposeTeamDAO;
import com.example.version.repository.mappers.PlayerComposeTeamMapper;
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
public class PlayerComposeTeamRepo implements PlayerComposeTeamDAO {

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
    public void save(PlayerComposeTeam object) {
        try {
            String insert = String.format("INSERT INTO player_compose_team (idPlayer,idTeam,position,number," +
                            "contract_starts,contract_ends)"
                            + " VALUES('%d','%d','%s','%d','%s','%s')",
                    object.getIdPlayer(), object.getIdTeam(), object.getPosition(),
                    object.getNumber(), object.getContractStarts(), object.getContractEnds()
            );
            jdbcTemplate.execute(insert);
            log.info(object.toString() + " SAVED IN DATABASE ");
        } catch (Exception e) {
            log.error(e);
        }
    }


    @Override
    public void update(Long id, PlayerComposeTeam object) {
        String update = String.format("UPDATE player_compose_team SET idTeam='%d', position = '%s',number= '%d', " +
                        "contract_starts = '%s', contract_ends = '%s'" +
                        "WHERE idPlayer ='%d'",
                object.getIdTeam(), object.getPosition(), object.getNumber(),
                object.getContractStarts(), object.getContractEnds(), id);
        jdbcTemplate.execute(update);
        log.info(object.toString() + " UPDATED IN DATABASE ");
    }


    @Override
    public boolean deleteById(Long id) {
        try {
            String delete = String.format("DELETE FROM player_compose_team WHERE idPlayer='%d'", id);
            jdbcTemplate.execute(delete);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        try {
            String sql = "delete from player_compose_team";
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public List<PlayerComposeTeam> findAll() {
        return jdbcTemplate.query("SELECT * FROM player_compose_team",
                new PlayerComposeTeamMapper());
    }

    @Override
    public PlayerComposeTeam findById(Long Id) {
        try {
            Object[] params = new Object[]{Id};
            return jdbcTemplate.queryForObject("SELECT * FROM player_compose_team WHERE idPlayer = ?",
                    params, new PlayerComposeTeamMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<PlayerComposeTeam> playersForTeam(Long idTeam) {
        String sql = "SELECT p.idPlayer,p.name,p.surname,pt.number,pt.position,pt.contract_starts,pt.contract_ends " +
                "FROM player p,player_compose_team pt,team t " +
                "WHERE pt.idPlayer=p.idPlayer AND t.idTeam=(pt.idTeam=" +idTeam+");";
        List<PlayerComposeTeam> players = jdbcTemplate.query(sql,
                rs -> {
                    List<PlayerComposeTeam> playerComposeTeams = new ArrayList<>();
                    while (rs.next()) {
                        PlayerComposeTeam playerComposeTeam = new PlayerComposeTeam();
                        playerComposeTeam.setIdPlayer(rs.getLong("idPlayer"));
                        playerComposeTeam.setName(rs.getString("name"));
                        playerComposeTeam.setSurname(rs.getString("surname"));
                        playerComposeTeam.setNumber(rs.getInt("number"));
                        playerComposeTeam.setPosition(rs.getString("position"));
                        playerComposeTeam.setContractStarts(rs.getString("contract_starts"));
                        playerComposeTeam.setContractEnds(rs.getString("contract_ends"));
                        playerComposeTeams.add(playerComposeTeam);
                    }
                    return playerComposeTeams;
                });
        return players;

    }
}