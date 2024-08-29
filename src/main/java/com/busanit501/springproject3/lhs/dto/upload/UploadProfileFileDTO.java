package com.busanit501.springproject3.lhs.dto.upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadProfileFileDTO {
    private MultipartFile file;
}
