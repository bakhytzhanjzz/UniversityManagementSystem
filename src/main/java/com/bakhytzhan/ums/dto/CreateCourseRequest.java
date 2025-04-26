package com.bakhytzhan.ums.dto;

import lombok.Data;

@Data
public class CreateCourseRequest {
    private String name;
    private String description;
    private int credits;
}
