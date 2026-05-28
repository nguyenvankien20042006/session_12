package com.example.bai2.controller;

import com.example.bai2.model.ApiDataResponse;
import com.example.bai2.model.Course;
import com.example.bai2.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Course>>> getAll() {
        log.info("getAll /api/courses called");
        return new ResponseEntity<>(new ApiDataResponse<>(true, "Get all courses successfully", courseService.getAll(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Course>> createCourse(@RequestBody Course course) {
        log.info("createCourse /api/courses called with course: {}", course);
        return new ResponseEntity<>(new ApiDataResponse<>(true, "Create course successfully", courseService.createCourse(course), null), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Course>> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        log.info("updateCourse /api/courses/{id} called with course: {}", course);
        return new ResponseEntity<>(new ApiDataResponse<>(true, "Update course successfully", courseService.updateCourse(id, course), null), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Course>> deleteCourse(@PathVariable Long id) {
        log.info("deleteCourse /api/courses/{id} called");
        courseService.deleteCourse(id);
        return new ResponseEntity<>(new ApiDataResponse<>(true, "Delete course successfully", null, null), HttpStatus.NO_CONTENT);
    }
}
