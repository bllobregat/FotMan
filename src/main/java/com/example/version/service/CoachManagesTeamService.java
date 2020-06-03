package com.example.version.service;

import com.example.version.model.CoachManagesTeam;

import java.util.List;

public abstract class CoachManagesTeamService implements BaseService<CoachManagesTeam, Long> {

    public abstract List<CoachManagesTeam> listCoachByTeam(Long idTeam);
}
