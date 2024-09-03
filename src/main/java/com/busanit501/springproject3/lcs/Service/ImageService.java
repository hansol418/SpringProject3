package com.busanit501.springproject3.lcs.Service;

import com.busanit501.springproject3.lcs.Dto.ImageDocument;
import com.busanit501.springproject3.lcs.Repository.ImageRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public ImageDocument saveImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] fileBytes = file.getBytes();

        // Base64로 인코딩
        String base64Data = Base64.encodeBase64String(fileBytes);

        // MongoDB에 저장할 문서 생성
        ImageDocument imageDocument = new ImageDocument();
        imageDocument.setFileName(fileName);
        imageDocument.setContentType(contentType);
        imageDocument.setBase64Data(base64Data);

        return imageRepository.save(imageDocument);
    }
}

