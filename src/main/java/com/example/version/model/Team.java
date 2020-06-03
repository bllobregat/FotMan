package com.example.version.model;

public class Team {

    private Long idTeam;
    private String name;
    private String division;
    private String stadium;

    public Team() {
    }

    public Team(Long idTeam, String name, String division, String stadium) {
        this.idTeam = idTeam;
        this.name = name;
        this.division = division;
        this.stadium = stadium;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    @Override
    public String toString() {
        return String.format("Team { name='%s', division='%s', stadium='%s'}", name, division, stadium);
    }

    public void setCoach(String coach) {
    }
}
