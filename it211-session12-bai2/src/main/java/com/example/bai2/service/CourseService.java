package com.example.bai2.service;

import com.example.bai2.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CourseService {
    private final List<Course> courses;

    public CourseService() {
        this.courses = new ArrayList<>();
    }

    public List<Course> getAll() {
        return this.courses;
    }

    public Course createCourse(Course course) {
        this.courses.add(course);
        log.info("Tạo khoá học thành công");
        return course;
    }

    public Course findById(Long id) {
        return this.courses.stream().filter(course -> course.getId().equals(id)).findFirst().orElseThrow(() -> {
            log.warn("Khoá học không tồn tại");
            return new RuntimeException("Course not found");
        });
    }

    public Course updateCourse(Long id, Course course) {
        Course existingCourse = this.findById(id);
        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setInstructor(course.getInstructor());
        existingCourse.setDurationHours(course.getDurationHours());
        existingCourse.setFee(course.getFee());
        log.info("Cập nhật khoá học thành công");
        return existingCourse;
    }

    public void deleteCourse(Long id) {
        this.courses.removeIf(course -> course.getId().equals(id));
    }
}
