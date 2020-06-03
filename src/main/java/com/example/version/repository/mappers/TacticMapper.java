package com.example.version.repository.mappers;

import com.example.version.model.Tactic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TacticMapper implements RowMapper<Tactic> {


    @Override
    public Tactic mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tactic tactic = new Tactic();
        tactic.setIdTeam(rs.getLong("idTeam"));
        tactic.setFormation(rs.getString("formation"));
        tactic.setType(rs.getString("type"));
        tactic.setPersonalNotes(rs.getString("personal_notes"));
        return tactic;
    }
}
