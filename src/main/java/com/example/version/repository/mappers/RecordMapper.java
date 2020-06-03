package com.example.version.repository.mappers;

import com.example.version.model.Record;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordMapper implements RowMapper<Record> {

    @Override
    public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
        Record record = new Record();
        record.setIdPlayer(rs.getLong("idPlayer"));
        record.setSeasons(rs.getInt("seasons"));
        record.setMatches(rs.getInt("matches"));
        record.setGoals(rs.getInt("goals"));
        return record;
    }
}
