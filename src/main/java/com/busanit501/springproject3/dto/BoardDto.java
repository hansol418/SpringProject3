package com.busanit501.springproject3.dto;

import com.busanit501.springproject3.entity.Board;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardDto {
    private Long id;
    private String title;
    private String writer;
    private String boardContent;

}

