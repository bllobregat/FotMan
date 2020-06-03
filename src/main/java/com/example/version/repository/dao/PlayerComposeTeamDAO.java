package com.example.version.repository.dao;

import com.example.version.model.PlayerComposeTeam;
import com.example.version.repository.dao.base.BaseDAO;

import java.util.List;

public interface PlayerComposeTeamDAO extends BaseDAO<PlayerComposeTeam, Long> {

    List<PlayerComposeTeam> playersForTeam(Long idTeam);
}
