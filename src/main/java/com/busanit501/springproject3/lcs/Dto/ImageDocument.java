package com.busanit501.springproject3.lcs.Dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Classifyimages")
public class ImageDocument {
    @Id
    private String id;
    private String fileName;
    private String contentType;
    private String base64Data; // Base64로 인코딩된 이미지 데이터

    // getters and setters
}

