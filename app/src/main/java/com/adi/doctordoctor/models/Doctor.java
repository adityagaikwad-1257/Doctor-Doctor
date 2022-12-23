package com.adi.doctordoctor.models;

import org.jetbrains.annotations.NotNull;

public class Doctor {
    private String name;
    private String email;
    private String gender;
    private String practice_frm_month;
    private String practice_frm_year;
    private String doctors_id;

    public Doctor(){}

    public Doctor(String name, String email, String gender, String practice_frm_month, String practice_frm_year) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.practice_frm_month = practice_frm_month;
        this.practice_frm_year = practice_frm_year;
    }

    @NotNull
    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", practice_frm_month='" + practice_frm_month + '\'' +
                ", practice_frm_year='" + practice_frm_year + '\'' +
                ", doctors_id='" + doctors_id + '\'' +
                '}';
    }

    public String getDoctors_id() {
        return doctors_id;
    }

    public void setDoctors_id(String doctors_id) {
        this.doctors_id = doctors_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPractice_frm_month() {
        return practice_frm_month;
    }

    public void setPractice_frm_month(String practice_frm_month) {
        this.practice_frm_month = practice_frm_month;
    }

    public String getPractice_frm_year() {
        return practice_frm_year;
    }

    public void setPractice_frm_year(String practice_frm_year) {
        this.practice_frm_year = practice_frm_year;
    }
}
