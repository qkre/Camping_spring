package com.tinygem.camping_spring.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCampSiteRequestDto {
    private String address;
    private String brands;
    private double latitude;
    private double longitude;
    private boolean certified;
    private String description;
    private double price;
}
