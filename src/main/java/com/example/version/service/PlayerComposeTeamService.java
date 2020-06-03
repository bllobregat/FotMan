package com.example.version.service;

import com.example.version.model.PlayerComposeTeam;

import java.util.List;

public abstract class PlayerComposeTeamService implements BaseService<PlayerComposeTeam, Long> {

    public abstract List<PlayerComposeTeam> listPlayerByTeam(Long idTeam);
}
