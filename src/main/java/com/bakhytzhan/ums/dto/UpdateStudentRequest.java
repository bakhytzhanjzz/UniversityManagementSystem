package com.bakhytzhan.ums.dto;

import lombok.Data;

@Data
public class UpdateStudentRequest {
    private String firstName;
    private String lastName;
    private String email;
}
