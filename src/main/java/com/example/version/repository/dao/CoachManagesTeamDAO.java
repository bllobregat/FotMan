package com.example.version.repository.dao;

import com.example.version.model.CoachManagesTeam;
import com.example.version.repository.dao.base.BaseDAO;

import java.util.List;

public interface CoachManagesTeamDAO extends BaseDAO<CoachManagesTeam, Long> {

    List<CoachManagesTeam> listCoachByTeam(Long idTeam);
}
