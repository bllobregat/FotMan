package com.example.version.model;

public abstract class Person {

    private String name;
    private String surname;
    private int age;
    private String address;
    private String email;
    private String phoneNumber;


    public Person() {
    }

    public Person(String name, String surname, int age, String address, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("name='%s', surname='%s', age=%d, address='%s', email='%s', phoneNumber='%s'",
                name, surname, age, address, email, phoneNumber);
    }
}
