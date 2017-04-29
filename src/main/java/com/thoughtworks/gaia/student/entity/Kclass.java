package com.thoughtworks.gaia.student.entity;

import com.thoughtworks.gaia.student.model.StudentModel;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangpu on 17-4-25.
 */
public class Kclass {
    private Long id;
    private String name;
    private Set<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long kclassId) {
        this.id = kclassId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }


}
