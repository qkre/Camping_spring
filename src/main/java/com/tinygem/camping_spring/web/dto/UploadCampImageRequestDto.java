package com.tinygem.camping_spring.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class UploadCampImageRequestDto {
    private List<String> imagePathList;
    private int campID;
}
