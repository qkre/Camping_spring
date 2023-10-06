package com.tinygem.camping_spring.web;

import com.tinygem.camping_spring.domain.campsite.CampSite;
import com.tinygem.camping_spring.service.CampSiteService;
import com.tinygem.camping_spring.web.dto.AddCampSiteRequestDto;
import com.tinygem.camping_spring.web.dto.UploadCampImageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/campsite")
public class CampSiteApiController {
    private final CampSiteService campSiteService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AddCampSiteRequestDto requestDto) {
        int result = campSiteService.save(requestDto);

        return ResponseEntity.ok(String.valueOf(result));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CampSite>> getAllCampSite() {
        return ResponseEntity.ok(campSiteService.getAllCampSite());
    }

    @GetMapping("/search/{brands}")
    public ResponseEntity<List<CampSite>> findByBrands(@PathVariable String brands) {
        return ResponseEntity.ok(campSiteService.findByBrands(brands));
    }

    @PostMapping("/upload/images")
    public ResponseEntity<String> uploadImage(@RequestParam("images") List<MultipartFile> images, @RequestHeader("campID") String campID) {
        String uploadDirectory = "images/"+campID+"/";

        File directory = new File(uploadDirectory);
        if(!directory.exists()){
            directory.mkdirs();
        }

        UploadCampImageRequestDto requestDto = new UploadCampImageRequestDto();
        List<String> imagePathList = new ArrayList<>();

        for (MultipartFile image: images){
            try {
                String imagePath = image.getOriginalFilename();
                String absolutePath = directory.getAbsolutePath()+"/" + imagePath;
                System.out.println("absolutePath = " + absolutePath);

                File dest = new File(absolutePath);
                image.transferTo(dest);


            } catch (IOException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload images. Cause " + e);
            }
        }

        requestDto.setImagePathList(imagePathList);
        requestDto.setCampID(Integer.parseInt(campID));

        campSiteService.uploadCampImages(requestDto);

        return ResponseEntity.ok("Images uploaded successfully.");
    }

    @GetMapping("/clear")
    public ResponseEntity<String> clearCampSite(){
        campSiteService.clearCampSite();
        return ResponseEntity.ok("캠핑장 DB 초기화");
    }
}
