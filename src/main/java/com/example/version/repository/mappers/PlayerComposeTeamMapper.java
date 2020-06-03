package com.example.version.repository.mappers;

import com.example.version.model.PlayerComposeTeam;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerComposeTeamMapper implements RowMapper<PlayerComposeTeam> {
    @Override
    public PlayerComposeTeam mapRow(ResultSet rs, int rowNum) throws SQLException {
        PlayerComposeTeam playerComposeTeam = new PlayerComposeTeam();
        playerComposeTeam.setIdPlayer(rs.getLong("idPlayer"));
        playerComposeTeam.setIdTeam(rs.getLong("idTeam"));
        playerComposeTeam.setPosition(rs.getString("position"));
        playerComposeTeam.setNumber(rs.getInt("number"));
        playerComposeTeam.setContractStarts(rs.getString("contract_starts"));
        playerComposeTeam.setContractEnds(rs.getString("contract_ends"));
        return playerComposeTeam;
    }
}
