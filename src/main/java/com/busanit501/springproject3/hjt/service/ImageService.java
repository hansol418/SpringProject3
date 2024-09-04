package com.busanit501.springproject3.hjt.service;

import com.busanit501.springproject3.hjt.domain.ImageEntity;
import com.busanit501.springproject3.hjt.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private final String uploadDir = "src/main/resources/static/images/";

    @Autowired
    private ImageRepository imageRepository;

    public ImageEntity saveImage(MultipartFile file) throws IOException {
        // 고유한 파일 이름 생성
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);

        // 파일 저장
        Files.copy(file.getInputStream(), filePath);

        // 이미지 엔티티 생성 및 저장
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(fileName);
        imageEntity.setFilePath("/images/" + fileName);
        return imageRepository.save(imageEntity);
    }

    public ImageEntity getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
