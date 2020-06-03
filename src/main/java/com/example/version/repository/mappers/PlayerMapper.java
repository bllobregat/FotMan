package com.example.version.repository.mappers;

import com.example.version.model.Player;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerMapper implements RowMapper<Player> {

    @Override
    public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
        Player player = new Player();
        player.setIdPlayer(rs.getLong("idPlayer"));
        player.setName(rs.getString("name"));
        player.setSurname(rs.getString("surname"));
        player.setAge((rs.getInt("age")));
        player.setAddress(rs.getString("address"));
        player.setEmail(rs.getString("email"));
        player.setPhoneNumber(rs.getString("phoneNumber"));
        player.setWeight(rs.getString("weight"));
        player.setHeight(rs.getString("height"));
        player.setNationality(rs.getString("nationality"));
        player.setPersonalNotes(rs.getString("personalNotes"));
        return player;
    }
}
