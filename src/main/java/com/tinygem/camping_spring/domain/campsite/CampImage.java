package com.tinygem.camping_spring.domain.campsite;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "CampImage")
@Entity
public class CampImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageID", nullable = false)
    private int imageID;

    @Column(name = "imagePath")
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campID", nullable = false)
    private CampSite campSite;

    @Builder
    public CampImage(String imagePath, CampSite campSite){
        this.imagePath = imagePath;
        this.campSite = campSite;
    }


}
