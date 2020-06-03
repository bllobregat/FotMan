package com.example.version.service;

import com.example.version.model.Player;

import java.util.List;

public abstract class PlayerService implements BaseService<Player, Long> {

    public abstract List<Player> listByTeam();
}
