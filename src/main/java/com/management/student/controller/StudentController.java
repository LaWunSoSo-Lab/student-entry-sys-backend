package com.management.student.controller;

import com.management.student.entity.Student;
import com.management.student.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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

    // **Paginated endpoint**
    @GetMapping("/all_students")
    public Page<Student> getStudents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return studentService.getStudents(PageRequest.of(page, size));
    }

    @DeleteMapping("/delete_student/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id) {
        try {
            studentService.deleteStudentById(id);
            return new ResponseEntity<>("Student with ID " + id + " deleted successfully.", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity
                    .status(404)
                    .body("Student with ID " + id + " not found");
        }

        return ResponseEntity.ok(student);

    }

    @PutMapping("/update_student/{id}")
    public ResponseEntity<?> updateStudentById(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudentById(id, student);

        System.out.println("Update request received for student ID: " + id);
        System.out.println("Student data: " + student);

        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

}
