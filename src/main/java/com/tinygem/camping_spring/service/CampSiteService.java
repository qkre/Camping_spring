package com.tinygem.camping_spring.service;

import com.tinygem.camping_spring.domain.campsite.CampSite;
import com.tinygem.camping_spring.repository.CampSiteRepository;
import com.tinygem.camping_spring.web.dto.AddCampSiteRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CampSiteService {

    private final CampSiteRepository campSiteRepository;

    public int save(AddCampSiteRequestDto requestDto) {
        if (campSiteRepository.findByAddress(requestDto.getAddress()).isPresent()) {
            return -1;
        }

        return campSiteRepository.save(
                CampSite.builder()
                        .address(requestDto.getAddress())
                        .brands(requestDto.getBrands())
                        .latitude(requestDto.getLatitude())
                        .longitude(requestDto.getLongitude())
                        .certified(requestDto.isCertified())
                        .description(requestDto.getDescription())
                        .build()
        ).getCampID();
    }

    public List<CampSite> getAllCampSite() {
        return campSiteRepository.findAll();
    }

    public List<CampSite> findByBrands(String brands) {
        List<CampSite> campSites = campSiteRepository.findAll();

        return campSites.stream().filter(campSite -> campSite.getBrands().toLowerCase().contains(brands.toLowerCase())).toList();
    }
}
