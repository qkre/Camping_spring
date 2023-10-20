package com.tinygem.camping_spring.domain.campsite;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "CampSite")
@Entity
public class CampSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campID")
    private int campID;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name ="brands", nullable = false)
    private String brands;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "certified")
    private boolean certified;

    @Column(name = "description", length = 50000)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Builder
    public CampSite(String address, String brands, double latitude, double longitude, boolean certified, String description, double price){
        this.address = address;
        this.brands = brands;
        this.latitude = latitude;
        this.longitude = longitude;
        this.certified = certified;
        this.description = description;
        this.price = price;
    }

}
