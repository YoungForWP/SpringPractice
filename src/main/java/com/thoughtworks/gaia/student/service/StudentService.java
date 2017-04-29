package com.thoughtworks.gaia.student.service;

import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.student.StudentMapper;
import com.thoughtworks.gaia.student.dao.StudentDao;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.student.StudentMapper;
import com.thoughtworks.gaia.student.dao.StudentDao;
import com.thoughtworks.gaia.student.entity.Student;
import com.thoughtworks.gaia.student.model.KclassModel;
import com.thoughtworks.gaia.student.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangpu on 17-4-24.
 */
@Component
@Transactional
public class StudentService implements Loggable {
    @Autowired
    private StudentMapper mapper;
    @Autowired
    private StudentDao studentDao;

    public Student getStudent(Long studentId) {
        StudentModel studentModel = studentDao.idEquals(studentId).querySingle();
        if (studentModel == null) {
            error("Student not found with id:" + studentId);
            throw new NotFoundException();
        }
        return mapper.map(studentModel, Student.class);
    }

    public void deleteStudent(Long studentId) {
        StudentModel studentModel = studentDao.idEquals(studentId).querySingle();
        if (studentModel == null) {
            error("Student not exist with id:" + studentId);
            throw new NotFoundException();
        }
        studentDao.remove(studentModel);
    }

    public Student addStudent(Student student) {
        StudentModel studentModel = mapper.map(student, StudentModel.class);
        studentDao.save(studentModel);
        return mapper.map(studentModel,Student.class);
    }

    public Student updateStudent(Student student) {
        StudentModel studentModel = mapper.map(student, StudentModel.class);
        studentDao.save(studentModel);
        return mapper.map(studentModel, Student.class);
    }

}
