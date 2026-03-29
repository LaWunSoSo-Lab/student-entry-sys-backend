package com.management.student.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Student name is required")
    @Column(name = "student_name", nullable = false)
    private String studentName;

    @NotBlank(message = "Father name is required")
    @Column(name = "father_name", nullable = false)
    private String fatherName;

    @NotBlank(message = "Class name is required")
    @Column(name = "class_name", nullable = false)
    private String className;
}
