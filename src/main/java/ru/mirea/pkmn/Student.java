package ru.mirea.pkmn;

import java.io.Serializable;

public class Student implements Serializable {
    private String firstName;
    public static final long serialVersionUID = 1L;
    private String surName;
    private String fatherName;

    private String group;

    public Student(String firstName, String surName, String familyMame, String group) {
        this.firstName = firstName;
        this.surName = surName;
        this.fatherName = familyMame;
        this.group = group;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", familyMame='" + fatherName + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getFamilyMame() {
        return fatherName;
    }

    public String getGroup() {
        return group;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setFamilyMame(String familyMame) {
        this.fatherName = familyMame;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
