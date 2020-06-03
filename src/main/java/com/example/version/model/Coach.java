package com.example.version.model;

public class Coach extends Person {

    private Long idCoach;
    private String Licence;


    public Coach(Long idCoach, String licence) {
        this.idCoach = idCoach;
        this.Licence = licence;
    }

    public Coach(Long idCoach, String name, String surname,
                 int age, String address, String email, String phoneNumber, String licence) {
        super(name, surname, age, address, email, phoneNumber);
        this.idCoach = idCoach;
        this.Licence = licence;
    }

    public Coach() {

    }

    public Long getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(Long idCoach) {
        this.idCoach = idCoach;
    }

    public String getLicence() {
        return Licence;
    }

    public void setLicence(String licence) {
        Licence = licence;
    }

    @Override
    public String toString() {
        return String.format("Coach{idCoach=%d, Licence='%s'}", idCoach, Licence);
    }
}
