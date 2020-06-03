package com.example.version.model;

public class Tactic {

    private Long idTeam;
    private String formation;
    private String type;
    private String personalNotes;

    public Tactic(Long idTeam, String formation, String type, String personalNotes) {
        this.idTeam = idTeam;
        this.formation = formation;
        this.type = type;
        this.personalNotes = personalNotes;
    }

    public Tactic() {

    }

    public Long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Tactic {idTeam=%d, formation='%s', type='%s'}", idTeam, formation, type);
    }

    public String getPersonalNotes() {
        return personalNotes;
    }

    public void setPersonalNotes(String personalNotes) {
        this.personalNotes = personalNotes;
    }
}
