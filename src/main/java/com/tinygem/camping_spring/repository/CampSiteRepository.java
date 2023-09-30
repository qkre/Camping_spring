package com.tinygem.camping_spring.repository;

import com.tinygem.camping_spring.domain.campsite.CampSite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampSiteRepository extends JpaRepository<CampSite, Integer> {
    Optional<CampSite> findByAddress(String address);
}
