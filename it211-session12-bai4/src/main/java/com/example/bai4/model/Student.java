package com.example.bai4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student {
    private Long id;
    private String studentCode;
    private String fullName;
    private String major;
    private Double gpa;
}
