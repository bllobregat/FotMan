package com.example.version.repository.mappers;

import com.example.version.model.Coach;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachMapper implements RowMapper<Coach> {

    @Override
    public Coach mapRow(ResultSet rs, int rowNum) throws SQLException {
        Coach coach = new Coach();
        coach.setIdCoach(rs.getLong("idCoach"));
        coach.setName(rs.getString("name"));
        coach.setSurname(rs.getString("surname"));
        coach.setAge((rs.getInt("age")));
        coach.setAddress(rs.getString("address"));
        coach.setEmail(rs.getString("email"));
        coach.setPhoneNumber(rs.getString("phoneNumber"));
        coach.setLicence(rs.getString("licence"));
        return coach;

    }
}
