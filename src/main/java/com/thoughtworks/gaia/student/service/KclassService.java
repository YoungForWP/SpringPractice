package com.thoughtworks.gaia.student.service;

import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.student.KclassMapper;
import com.thoughtworks.gaia.student.StudentMapper;
import com.thoughtworks.gaia.student.dao.KclassDao;
import com.thoughtworks.gaia.student.dao.StudentDao;
import com.thoughtworks.gaia.student.entity.Kclass;
import com.thoughtworks.gaia.student.entity.Student;
import com.thoughtworks.gaia.student.model.KclassModel;
import com.thoughtworks.gaia.student.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangpu on 17-4-25.
 */
@Component
@Transactional
public class KclassService implements Loggable {
    @Autowired
    private KclassMapper mapper;
    @Autowired
    private KclassDao kclassDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentMapper studentMapper;

    public Kclass getKclass(Long kclassId) {
        KclassModel kclassModel = kclassDao.idEquals(kclassId).querySingle();
        if (kclassModel == null) {
            error("Class not found with id:" + kclassId);
            throw new NotFoundException();
        }
        return mapper.map(kclassModel, Kclass.class);
    }

    public  Student getKclassStudent(Long kclassId, Long studentId){
        KclassModel kclassModel = kclassDao.idEquals(kclassId).querySingle();
        if (kclassModel == null) {
            error("Class not found with id:" + kclassId);
            throw new NotFoundException();
        }
        StudentModel studentModel = studentDao.idEquals(studentId).querySingle();
        if (studentModel == null) {
            error("student not found with id:" + kclassId);
            throw new NotFoundException();
        }
        return  mapper.map(studentModel, Student.class);
    }
    public Kclass addKclass(Kclass kclass) {
        KclassModel kclassModel = mapper.map(kclass, KclassModel.class);
        kclassDao.save(kclassModel);
        return mapper.map(kclassModel, Kclass.class);
    }
    public Kclass addKclassStudent(Long kclassId,Student student){
        KclassModel kclassModel = kclassDao.idEquals(kclassId).querySingle();
        student.setClassId(kclassId);
        StudentModel studentModel = studentMapper.map(student, StudentModel.class);
        Set<StudentModel> students = new HashSet<StudentModel>();
        students.add(studentModel);
        studentDao.save(studentModel);
        kclassModel.setStudents(students);
        return mapper.map(kclassModel,Kclass.class);
    }

    public void deleteKclass(Long kclassId) {
        KclassModel kclassModel = kclassDao.idEquals(kclassId).querySingle();
        if (kclassModel == null) {
            error("class not found with id:" + kclassId);
        }
        kclassDao.remove(kclassModel);
    }

    public void deleteKclassStudent(Long kclassId,Long studentId){

        StudentModel studentModel = studentDao.idEquals(studentId).querySingle();
        if(studentModel == null){
            error("class not found with id:" + studentId);
            throw new NotFoundException();
        }
        studentModel.setClassId(kclassId);
        studentDao.remove(studentModel);
    }

    public Kclass updateKclass(Kclass kclass) {
        KclassModel kclassModel = mapper.map(kclass, KclassModel.class);
        kclassDao.update(kclassModel);
        return mapper.map(kclassModel, Kclass.class);
    }

    public Kclass updateKclassStudent(Long classId,Student student){
        KclassModel kclassModel = kclassDao.idEquals(classId).querySingle();
        if(kclassModel == null){
            error("class not found with id:" + classId);
            throw new NotFoundException();
        }
        StudentModel studentModel = mapper.map(student,StudentModel.class);
        Set<StudentModel> students = new HashSet<StudentModel>();
        students.add(studentModel);
        kclassModel.setStudents(students);
        kclassDao.update(kclassModel);
        return mapper.map(kclassModel,Kclass.class);
    }
}


