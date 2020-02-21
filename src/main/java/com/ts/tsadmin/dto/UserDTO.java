package com.ts.tsadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String emailId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String gender;
    private Date dateOfBirth;
    private String password;

}
