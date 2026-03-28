package com.management.student.controller;

import com.management.student.entity.Student;
import com.management.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/post_student")
    public ResponseEntity<?> postStudent(@RequestBody @Valid Student student) {
        try {
            Student saved = studentService.postStudent(student);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            // Send error message in response
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/get_students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

}
