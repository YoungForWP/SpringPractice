package com.thoughtworks.gaia.student.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangpu on 17-4-25.
 */
@Entity
@Table(name = "KCLASS")
public class KclassModel extends IdBaseModel {
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "classId")
    // @JoinColumn(name = "class_id")
    public Set<StudentModel> students;


    public Set<StudentModel> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentModel> students) {
        this.students = students;
    }

}
