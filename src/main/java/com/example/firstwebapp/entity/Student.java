package com.example.firstwebapp.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Student {

    @Id
    @Column(name = "Student_Id")
    @Positive(message = "Xahis olunur musbet eded daxil edin")
    @NotNull(message = "xahis olunur musbet eded daxil edin")
    private Integer id;

    @Column(name = "Name")
    @NotBlank(message = "Xahis olunur ad daxil edin")
    @Size(min = 3,max = 30)
    private String name;

    @Column(name = "Surname")
    @NotBlank(message = "Xahis olunur soyad daxil edin")
    @Size(min = 5,max = 30)
    private String surname;


    @ManyToMany
    @JoinTable(name = "Student_Courses",
            joinColumns = @JoinColumn(name = "Student_ID"),
            inverseJoinColumns = @JoinColumn(name = "Course_ID"))
    List<Course> courses;


    public Student(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
