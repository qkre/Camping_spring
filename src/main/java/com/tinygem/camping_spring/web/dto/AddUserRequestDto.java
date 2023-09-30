package com.tinygem.camping_spring.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequestDto {
    private int accessLevel;
    private String email;
    private String pwd;
    private String nick;
    private String provider;
    private String snsID;
}
