package com.example.version.repository;

import com.example.version.model.Player;
import com.example.version.repository.dao.PlayerDAO;
import com.example.version.repository.mappers.PlayerMapper;
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
public class PlayerRepo implements PlayerDAO {

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
    public void save(Player object) {
        try {
            String insert = String.format("INSERT INTO player (name, surname, age, address, email, phoneNumber, " +
                            "weight, height, nationality, personalNotes)"
                            + " VALUES('%s','%s','%d','%s','%s','%s', '%s','%s','%s','%s' )",
                    object.getName(), object.getSurname(), object.getAge(), object.getAddress(), object.getEmail(),
                    object.getPhoneNumber(), object.getWeight(), object.getHeight(), object.getNationality(),
                    object.getPersonalNotes());
            jdbcTemplate.execute(insert);
            log.info(object.toString() + " SAVED IN DATABASE ");
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void update(Long id, Player object) {
        String update = String.format("UPDATE player SET name = '%s', surname = '%s'," +
                        " age = '%d', address = '%s', email = '%s', phoneNumber = '%s', " +
                        "weight = '%s', height = '%s', nationality = '%s', personalNotes = '%s'" +
                        "WHERE idPlayer ='%d'",
                object.getName(), object.getSurname(), object.getAge(), object.getAddress(), object.getEmail(),
                object.getPhoneNumber(), object.getWeight(), object.getHeight(), object.getNationality(),
                object.getPersonalNotes(), id);
        jdbcTemplate.execute(update);
        log.info(object.toString() + " UPDATED IN DATABASE ");
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            String delete = String.format("DELETE FROM player WHERE idPlayer='%d'", id);
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
            String sql = "delete from player";
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public List<Player> findAll() {
        return jdbcTemplate.query("SELECT * FROM player",
                new PlayerMapper());
    }

    @Override
    public Player findById(Long Id) throws EmptyResultDataAccessException {
        Object[] params = new Object[]{Id};
        return jdbcTemplate.queryForObject("SELECT * FROM player WHERE idPlayer = ?",
                params, new PlayerMapper());
    }

    public List<Player> findPlayerTeam() {
        String SQL = "SELECT p.name,surname,age,height,weight,nationality,t.name team " +
                "FROM player p,team t,player_compose_team pt " +
                "WHERE p.idPlayer=pt.idPlayer AND t.idTeam=pt.idTeam;";
        List<Player> players = jdbcTemplate.query(SQL,
                rs -> {
                    List<Player> players1 = new ArrayList<Player>();
                    while (rs.next()) {
                        Player player = new Player();
                        player.setName(rs.getString("name"));
                        player.setSurname(rs.getString("surname"));
                        player.setAge(rs.getInt("age"));
                        player.setHeight(rs.getString("height"));
                        player.setWeight(rs.getString("weight"));
                        player.setNationality(rs.getString("nationality"));
                        player.setTeam(rs.getString("team"));
                        players1.add(player);
                    }
                    return players1;
                });
        return players;

    }

}
