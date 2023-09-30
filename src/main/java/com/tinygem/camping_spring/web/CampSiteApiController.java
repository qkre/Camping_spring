package com.tinygem.camping_spring.web;

import com.tinygem.camping_spring.domain.campsite.CampSite;
import com.tinygem.camping_spring.service.CampSiteService;
import com.tinygem.camping_spring.web.dto.AddCampSiteRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/campsite")
public class CampSiteApiController {
    private final CampSiteService campSiteService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AddCampSiteRequestDto requestDto){
        int result = campSiteService.save(requestDto);

        return ResponseEntity.ok(String.valueOf(result));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CampSite>> getAllCampSite(){
        return ResponseEntity.ok(campSiteService.getAllCampSite());
    }

    @GetMapping("/search/{brands}")
    public ResponseEntity<List<CampSite>> findByBrands(@PathVariable String brands){
        return ResponseEntity.ok(campSiteService.findByBrands(brands));
    }
}
