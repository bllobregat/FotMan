package com.example.version.model;

public class Training {

    private Long idPlayer;
    private String type;
    private String duration;
    private String exercises;
    private String personalNotes;

    public Training(Long idPlayer, String type, String duration, String exercises, String personalNotes) {
        this.idPlayer = idPlayer;
        this.type = type;
        this.duration = duration;
        this.exercises = exercises;
        this.personalNotes = personalNotes;
    }

    public Training() {

    }

    public Long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    public String getPersonalNotes() {
        return personalNotes;
    }

    public void setPersonalNotes(String personalNotes) {
        this.personalNotes = personalNotes;
    }

    @Override
    public String toString() {
        return String.format("Training {idPlayer=%d, type='%s', duration='%s', exercises='%s', personalNotes='%s'}",
                idPlayer, type, duration, exercises, personalNotes);
    }
}
