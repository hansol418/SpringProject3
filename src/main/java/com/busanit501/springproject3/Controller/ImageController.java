package com.busanit501.springproject3.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class ImageController {

    @Value("${django.api.url}")
    private String djangoApiUrl;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String handleFileUpload(MultipartFile file, Model model) {
        try {
            // 이미지 파일을 바이너리 데이터로 변환하여 전송
            byte[] fileContent = file.getBytes();

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", new org.springframework.core.io.ByteArrayResource(fileContent) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<Map> response = restTemplate.exchange(djangoApiUrl, HttpMethod.POST, requestEntity, Map.class);

            // 결과를 모델에 추가하여 뷰로 전달
            model.addAttribute("predictedClassLabel", response.getBody().get("predicted_class_label"));
            model.addAttribute("confidence", response.getBody().get("confidence"));
            model.addAttribute("classConfidences", response.getBody().get("class_confidences"));
            model.addAttribute("imageName", file.getOriginalFilename());
        } catch (IOException e) {
            model.addAttribute("message", "Error reading image file: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("message", "Error uploading image: " + e.getMessage());
        }

        return "result";
    }
}
