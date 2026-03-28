package com.management.student.service;

import com.management.student.entity.Student;
import com.management.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


        // Check for duplicate (studentName + fatherName)
//        if (studentRepository.existsByStudentNameAndFatherName(
//                student.getStudentName(), student.getFatherName())) {
//
//            throw new RuntimeException("Duplicate Student");
//        }

        if (studentRepository.existsByStudentNameAndFatherName(
                student.getStudentName(), student.getFatherName())) {

            // ✅ Log to console for tracing
            System.out.println("Duplicate student detected:");
            System.out.println("Student Name: " + student.getStudentName());
            System.out.println("Father Name: " + student.getFatherName());

            throw new RuntimeException("Duplicate Student");
        }

        // Save student
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
