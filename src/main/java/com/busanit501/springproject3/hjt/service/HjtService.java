package com.busanit501.springproject3.hjt.service;

import com.busanit501.springproject3.hjt.domain.HjtEntity;
import com.busanit501.springproject3.hjt.repository.HjtRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.startup.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class HjtService {

    @Autowired
    private HjtRepository hjtRepository;

    public List<HjtEntity> findAll() {
        return hjtRepository.findAll();
    }

    public Optional<HjtEntity> findById(Long id) {
        return hjtRepository.findById(id);
    }

    public void saveTools() {
        // 망치 데이터
        HjtEntity hammer = HjtEntity.builder()
                .tool_name("망치")
                .description("뭔가를 깨거나 못 등을 박는 용도로 주로 쓰이며, 공사현장에서는 작업의 종류에 따라 길이, 무게, 재질(고무, 구리, 쇠 등)이 다른 여러 종류의 망치를 사용한다. 보통 가정에서는 뒤에 노루발(못을 뽑을 때 사용하는 갈고리)이 달려 못을 박을 수도 있고 뽑을 수도 있는 장도리를 쓴다. 정확히는 슬레지해머처럼 큰 게 '망치'였고 작은 건 '마치'라고 했지만, 1980년대에도 이미 교과서에서 밖에 쓰지 않는 말이었고, 현재는 아예 쓰이지 않는 사어가 되었다. 한국에서는 슬렛지해머를 '오함마'라고 부르기도 한다.")
                .img_text("hammer.jpg")
                .build();

        // 니퍼 데이터
        HjtEntity nipper = HjtEntity.builder()
                .tool_name("니퍼")
                .description("피복된 전선을 드러내기 위해 절연 전선의 피복을 벗기거나, 가는 전선이나 철사 등 선재를 절단할 때 쓰는 공구다. 니퍼의 종류는 굉장히 다양한데 우리가 흔히 알고 있는 니퍼는 대각선형 컷팅식 니퍼(Diagonal Cutting Nipper)다.")
                .img_text("nipper.jpg")
                .build();

        // 줄자 데이터
        HjtEntity ruler = HjtEntity.builder()
                .tool_name("줄자")
                .description("줄로 된 자. 보통 플라스틱 막대 자로 잴 수 있는 길이보다 긴 길이를 잴 때 쓰인다. 단순히 치수를 재는 역할이지만, 사용 환경은 제각기 다르기 때문에 일상생활에서 쓰는 것은 길이 3~7m 정도에 폭은 약 17~20mm 정도가 되어 작고 휴대하기 편하거나 스마트폰에 걸고 다닐 수 있는 것으로 생산되는 것도 많다.")
                .img_text("tape_measure.jpg")
                .build();

        // 그라인더 데이터
        HjtEntity grinder = HjtEntity.builder()
                .tool_name("그라인더")
                .description("고속회전하는 원반형태의 날이나 원형컵으로 표면을 매끄럽게 갈아내는 전동공구. 절단기와 비슷하다. 전기를 동력으로 사용하는 것이 가장 흔하다.")
                .img_text("grinder.jpg")
                .build();

        // 드라이버 데이터
        HjtEntity driver = HjtEntity.builder()
                .tool_name("드라이버")
                .description("일상 생활에서 나사를 돌려 끼우고 빼는 도구. 정확한 이름은 스크루드라이버(Screwdriver)지만, 콩글리시로는 보통 드라이버로 불린다. 가장 기본적이자 대중적인 스크루드라이버로는 십(十)자 스크루 드라이버와 일(一)자 스크루 드라이버가 있다.")
                .img_text("screwdriver.jpg")
                .build();

        // 전동 드릴 데이터
        HjtEntity drill = HjtEntity.builder()
                .tool_name("전동 드릴")
                .description("전동공구의 얼굴마담으로 전기의 힘을 이용해 드릴비트나 스크류비트를 돌려 구멍을 뚫거나 나사를 고정시키는 공구이다.")
                .img_text("drill.jpg")
                .build();

        // 스패너 데이터
        HjtEntity spanner = HjtEntity.builder()
                .tool_name("스패너")
                .description("스크루드라이버와 비슷하게 너트나 볼트 따위를 죄고 풀며 물체를 조립하고 분해할 때 사용하는 도구.")
                .img_text("spanner.jpg")
                .build();

        // 공업용 가위 데이터
        HjtEntity scissors = HjtEntity.builder()
                .tool_name("공업용 가위")
                .description("가위는 두 개의 날을 교차시켜 물체를 자를 수 있도록 만들어진 도구로 실생활에서 널리 쓰인다. 지렛대의 원리를 사용한 도구로 고대부터 존재했다.")
                .img_text("scissors.jpg")
                .build();

        // 톱 데이터
        HjtEntity saw = HjtEntity.builder()
                .tool_name("톱")
                .description("좁고 긴 쇠판에 톱날을 내어 나무나 돌을 자르는 연장. 금속판이나 쇠줄 표면 등에 톱니를 만들어 물건을 자르거나 켜는 데 사용.")
                .img_text("saw.jpg")
                .build();

        // 버니어 캘리퍼스 데이터
        HjtEntity calipers = HjtEntity.builder()
                .tool_name("버니어 캘리퍼스")
                .description("길이나 높이, 너비 등 기계류의 치수를 정밀하게 측정하는 자의 일종으로, 주로 공학도나 인류학자가 사용.")
                .img_text("vernier_calipers.jpg")
                .build();

        // 모든 데이터를 저장
        hjtRepository.save(hammer);
        hjtRepository.save(nipper);
        hjtRepository.save(ruler);
        hjtRepository.save(grinder);
        hjtRepository.save(driver);
        hjtRepository.save(drill);
        hjtRepository.save(spanner);
        hjtRepository.save(scissors);
        hjtRepository.save(saw);
        hjtRepository.save(calipers);
    }
}
//    public HjtEntity saveTool(HjtEntity hjtEntity) {
//        return hjtRepository.save(hjtEntity);
//    }


