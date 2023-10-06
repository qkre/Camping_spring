package com.tinygem.camping_spring.repository;

import com.tinygem.camping_spring.domain.campsite.CampImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampImageRepository extends JpaRepository<CampImage, Integer> {
}
