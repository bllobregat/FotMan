package com.example.version.model;

public class Record {

    private Long idPlayer;
    private int seasons;
    private int matches;
    private int goals;

    public Record(Long idPlayer, int seasons, int matches, int goals) {
        this.idPlayer = idPlayer;
        this.seasons = seasons;
        this.matches = matches;
        this.goals = goals;
    }

    public Record() {

    }

    public Long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    @Override
    public String toString() {
        return String.format("Record {idPlayer=%d, seasons=%d, matches=%d, goals=%d}",
                idPlayer, seasons, matches, goals);
    }
}
