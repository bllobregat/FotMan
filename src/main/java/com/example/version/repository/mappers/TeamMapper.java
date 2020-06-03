package com.example.version.repository.mappers;

import com.example.version.model.Team;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamMapper implements RowMapper<Team> {
    @Override
    public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
        Team team = new Team();
        team.setIdTeam(rs.getLong("idTeam"));
        team.setName(rs.getString("name"));
        team.setDivision(rs.getString("division"));
        team.setStadium(rs.getString("stadium"));
        return team;
    }
}
