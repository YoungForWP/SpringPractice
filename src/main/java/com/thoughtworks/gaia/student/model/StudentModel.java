package com.thoughtworks.gaia.student.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;
import com.thoughtworks.gaia.student.entity.Kclass;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STUDENT")
public class StudentModel extends IdBaseModel {
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "age", nullable = false, length = 20)
    private Long age;

    @Column(name = "gender", nullable = false, length = 64)
    private String gender;


    @Column(name = "class_Id", nullable = false, length = 20)
    private Long classId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getClassId() {
        return classId;
    }


}


