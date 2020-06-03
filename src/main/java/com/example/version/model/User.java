package com.example.version.model;

public class User extends Person {

    private Long idUser;
    private String password;
    private String role;
    private Boolean enabled;

    public User(Long idUser) {
        this.idUser = idUser;
    }

    public User(String name, String surname, int age, String address, String email, String phoneNumber, Long idUser, String role, Boolean enabled) {
        super(name, surname, age, address, email, phoneNumber);
        this.idUser = idUser;
        this.role = role;
        this.enabled = enabled;
    }



    public User() {

    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return String.format("User {idUser=%d %s, role='%s', enabled= %b}",
                idUser, super.toString(), role,enabled);
    }
}
