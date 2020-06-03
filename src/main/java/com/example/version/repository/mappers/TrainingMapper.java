package com.example.version.repository.mappers;

import com.example.version.model.Training;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingMapper implements RowMapper<Training> {
    @Override
    public Training mapRow(ResultSet rs, int rowNum) throws SQLException {
        Training training = new Training();
        training.setIdPlayer(rs.getLong("idPlayer"));
        training.setType(rs.getString("type"));
        training.setDuration(rs.getString("duration"));
        training.setExercises(rs.getString("exercises"));
        training.setPersonalNotes(rs.getString("personal_notes"));
        return training;
    }
}
