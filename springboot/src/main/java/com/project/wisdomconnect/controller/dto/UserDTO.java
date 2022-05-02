package com.project.wisdomconnect.controller.dto;
import lombok.Data;


/**
 * get the parameters from front-end request
 */
@Data
public class UserDTO {

    private String username;
    private String password;
    private String email;
    private String token;




}
