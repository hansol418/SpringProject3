package com.busanit501.springproject3.hjt.controller;

import com.busanit501.springproject3.hjt.domain.ImageEntity;
import com.busanit501.springproject3.hjt.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageEntity> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            ImageEntity savedImage = imageService.saveImage(file);
            return new ResponseEntity<>(savedImage, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageEntity> getImage(@PathVariable Long id) {
        ImageEntity imageEntity = imageService.getImage(id);
        if (imageEntity != null) {
            return new ResponseEntity<>(imageEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}