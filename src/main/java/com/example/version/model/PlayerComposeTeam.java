package com.example.version.model;

public class PlayerComposeTeam {

    private Long idPlayer;
    private Long idTeam;
    private String name;
    private String surname;
    private String team;
    private int number;
    private String position;
    private String ContractStarts;
    private String ContractEnds;

    public PlayerComposeTeam(Long idPlayer, Long idTeam, String name, String surname, String team, int number, String position, String contractStarts, String contractEnds) {
        this.idPlayer = idPlayer;
        this.idTeam = idTeam;
        this.name = name;
        this.surname = surname;
        this.team = team;
        this.number = number;
        this.position = position;
        this.ContractStarts = contractStarts;
        this.ContractEnds = contractEnds;
    }

    public PlayerComposeTeam() {

    }

    public Long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContractStarts() {
        return ContractStarts;
    }

    public void setContractStarts(String contractStarts) {
        this.ContractStarts = contractStarts;
    }

    public String getContractEnds() {
        return ContractEnds;
    }

    public void setContractEnds(String contractEnds) {
        this.ContractEnds = contractEnds;
    }


    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return String.format("PlayerComposeTeam{idPlayer=%d, idTeam=%d, name='%s', surname='%s', team='%s', number=%d, " +
                        "position='%s', ContractStarts='%s', ContractEnds='%s'}",
                idPlayer, idTeam, name, surname, team, number, position, ContractStarts, ContractEnds);
    }
}
