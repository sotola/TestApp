package com.marek.testapp;

import java.util.Date;

public class PersonForm {

    private String firstName;
    private String lastName;
    private String country;
    private Date date;

    private Integer age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getFirstName() + ' ' + getLastName() + " from " + getCountry() + " on " + getDate();
    }
}
