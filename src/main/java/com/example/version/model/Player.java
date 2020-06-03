package com.example.version.model;

public class Player extends Person {

    private Long idPlayer;
    private String weight;
    private String height;
    private String personalNotes;
    private String nationality;

    private String team;

    public Player() {
    }

    public Player(Long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public Player(Long idPlayer, String name, String surname, int age, String address, String email, String phoneNumber,
                  String weight, String height, String personalNotes, String nationality) {
        super();
        this.idPlayer = idPlayer;
        this.weight = weight;
        this.height = height;
        this.personalNotes = personalNotes;
        this.nationality = nationality;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPersonalNotes() {
        return personalNotes;
    }

    public void setPersonalNotes(String personalNotes) {
        this.personalNotes = personalNotes;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return String.format("Player {%s, weight=%s, height=%s, personalNotes='%s', nationality='%s'}",
                super.toString(), weight, height, personalNotes, nationality);
    }


}
