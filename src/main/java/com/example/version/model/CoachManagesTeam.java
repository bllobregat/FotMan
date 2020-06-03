package com.example.version.model;

public class CoachManagesTeam {

    private Long idCoach;
    private Long idTeam;
    private String ContractStarts;
    private String ContractEnds;
    private String name;
    private String surname;

    public CoachManagesTeam(Long idCoach, Long idTeam, String contractStarts, String contractEnds, String name, String surname) {
        this.idCoach = idCoach;
        this.idTeam = idTeam;
        ContractStarts = contractStarts;
        ContractEnds = contractEnds;
        this.name = name;
        this.surname = surname;
    }

    public CoachManagesTeam() {

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

    public Long getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(Long idCoach) {
        this.idCoach = idCoach;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    public String getContractStarts() {
        return ContractStarts;
    }

    public void setContractStarts(String contractStarts) {
        ContractStarts = contractStarts;
    }

    public String getContractEnds() {
        return ContractEnds;
    }

    public void setContractEnds(String contractEnds) {
        ContractEnds = contractEnds;
    }

    @Override
    public String toString() {
        return String.format("CoachManagesTeam{idCoach=%d, idTeam=%d, ContractStarts='%s', ContractEnds='%s'}", idCoach, idTeam, ContractStarts, ContractEnds);
    }
}
