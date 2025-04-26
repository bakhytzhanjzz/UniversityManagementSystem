package com.bakhytzhan.ums.dto;

import lombok.Data;

@Data
public class CreateStudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
