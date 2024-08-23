package com.busanit501.springproject3.hjt.service;

import com.busanit501.springproject3.hjt.domain.HjtEntity;
import com.busanit501.springproject3.hjt.repository.HjtRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class HjtService {

    @Autowired
    private HjtRepository hjtRepository;

    public void click() throws JsonProcessingException {
        String apiUrl = "https://apis.data.go.kr/6260000/AttractionService/getAttractionKr";
        String apiKey = "ViNhf9KFHrhlepP82G2riFCwzySQxL4juLIE5itFGrf8lpCixgdypSpsC930g7033hqAO8PKM99K5eNbt13uSA==";
        String url = apiUrl + "?serviceKey=" + apiKey + "&pageNo=1&numOfRows=21";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String response = responseEntity.getBody();

        XmlMapper mapper = new XmlMapper();

        JsonNode root = mapper.readTree(response);
        JsonNode itemsNode = root.path("body").path("items").path("item");

        List<HjtEntity> hjtEntityList = new ArrayList<>();

        for (JsonNode node : itemsNode) {
            HjtEntity hjtEntity = HjtEntity.builder()
                    .tool_name(node.path("MAIN_TITLE").asText(null))
                    .description(node.path("ITEMCNTNTS").asText(null))
                    .img_text(node.path("MAIN_IMG_THUMB").asText(null))
                    .build();
            hjtEntityList.add(hjtEntity);
        }

    }
}
