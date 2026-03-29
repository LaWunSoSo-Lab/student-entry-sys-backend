package com.management.student.service;

import com.management.student.entity.Student;
import com.management.student.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public Student postStudent(Student student) {

        // Trim all text inputs
        student.setStudentName(student.getStudentName().trim());
        student.setFatherName(student.getFatherName().trim());
        student.setClassName(student.getClassName().trim());


        if (studentRepository.existsByStudentNameAndFatherName(
                student.getStudentName(), student.getFatherName())) {

            System.out.println("Duplicate student detected:");
            System.out.println("Student Name: " + student.getStudentName());
            System.out.println("Father Name: " + student.getFatherName());

            throw new RuntimeException("Duplicate Student");
        }

        // Save student
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Page<Student> getStudents(Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id") // same field for descending
        );
        return studentRepository.findAll(sortedPageable);
    }

    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student with ID " + id + " not found.");
        }

        studentRepository.deleteById(id);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudentById(Long id, Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student updatedStudent = optionalStudent.get();

            updatedStudent.setStudentName(student.getStudentName());
            updatedStudent.setFatherName(student.getFatherName());
            updatedStudent.setClassName(student.getClassName());

            return studentRepository.save(updatedStudent);
        }
        return null;
    }
}
