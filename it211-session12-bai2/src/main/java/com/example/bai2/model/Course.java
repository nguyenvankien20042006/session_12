package com.example.bai2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Course {
    private Long id;
    private String courseName;
    private String instructor;
    private Integer durationHours;
    private Double fee;
}
