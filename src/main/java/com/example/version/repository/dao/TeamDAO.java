package com.example.version.repository.dao;

import com.example.version.model.Team;
import com.example.version.repository.dao.base.BaseDAO;

import java.util.List;

public interface TeamDAO extends BaseDAO<Team, Long> {

    List<Team> teamsWitchCoach();
}
