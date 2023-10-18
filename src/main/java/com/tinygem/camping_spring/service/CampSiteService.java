package com.tinygem.camping_spring.service;

import com.tinygem.camping_spring.domain.campsite.CampImage;
import com.tinygem.camping_spring.domain.campsite.CampSite;
import com.tinygem.camping_spring.repository.CampImageRepository;
import com.tinygem.camping_spring.repository.CampSiteRepository;
import com.tinygem.camping_spring.web.dto.AddCampSiteRequestDto;
import com.tinygem.camping_spring.web.dto.UploadCampImageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CampSiteService {

    private final CampSiteRepository campSiteRepository;
    private final CampImageRepository campImageRepository;

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
                                .price(requestDto.getPrice())
                                .build())
                .getCampID();
    }

    public int delete(int campID){
        try{
            campSiteRepository.deleteById(campID);
        } catch (Exception e){
            System.out.println("e = " + e);
            return -1;
        }
         return 1;
    }

    public List<CampSite> getAllCampSite() {
        return campSiteRepository.findAll();
    }

    public List<CampSite> findByBrands(String brands) {
        List<CampSite> campSites = campSiteRepository.findAll();

        return campSites.stream().filter(campSite -> campSite.getBrands().toLowerCase().contains(brands.toLowerCase())).toList();
    }

    public void uploadCampImages(UploadCampImageRequestDto requestDto) {
        Optional<CampSite> campSite = campSiteRepository.findById(requestDto.getCampID());

        try {
            requestDto.getImagePathList().forEach(imagePath -> {
                campImageRepository.save(CampImage.builder().imagePath(imagePath).campSite(campSite.get()).build());
                System.out.println("Saved");
            });
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public void clearCampSite(){
        campImageRepository.deleteAll();
        campSiteRepository.deleteAll();
    }
}
