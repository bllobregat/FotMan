package com.example.version.repository.mappers;

import com.example.version.model.CoachManagesTeam;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachManagesTeamMapper implements RowMapper<CoachManagesTeam> {
    @Override
    public CoachManagesTeam mapRow(ResultSet rs, int rowNum) throws SQLException {
        CoachManagesTeam coachManagesTeam = new CoachManagesTeam();
        coachManagesTeam.setIdCoach(rs.getLong("idCoach"));
        coachManagesTeam.setIdTeam(rs.getLong("idTeam"));
        coachManagesTeam.setContractStarts(rs.getString("contract_starts"));
        coachManagesTeam.setContractEnds(rs.getString("contract_ends"));
        return coachManagesTeam;
    }
}
