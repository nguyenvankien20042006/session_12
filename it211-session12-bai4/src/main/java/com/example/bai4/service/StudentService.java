package com.example.bai4.service;

import com.example.bai4.exception.StudentNotFoundException;
import com.example.bai4.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StudentService {
    private List<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    public List<Student> getAll() {
        return students;
    }

    public Student save(Student student) {
        students.add(student);
        return student;
    }

    public Student findById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    log.warn("Student not found");
                    return new StudentNotFoundException("Student not found");
                });
    }

    public Student update(Long id, Student student) {
        Student existStudent = findById(id);
        existStudent.setStudentCode(student.getStudentCode());
        existStudent.setFullName(student.getFullName());
        existStudent.setMajor(student.getMajor());
        existStudent.setGpa(student.getGpa());
        return existStudent;
    }

    public void delete(Long id) {
        Student student = findById(id);
        students.remove(student);
    }
}
