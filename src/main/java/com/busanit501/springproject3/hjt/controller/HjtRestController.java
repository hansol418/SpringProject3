package com.busanit501.springproject3.hjt.controller;

import com.busanit501.springproject3.hjt.domain.HjtEntity;
import com.busanit501.springproject3.hjt.service.HjtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tools")
public class HjtRestController {

    @Autowired
    private HjtService hjtService;

    @GetMapping("/list")
    public List<HjtEntity> listTools() {
        List<HjtEntity> hjtList = hjtService.findAll();
//        log.info("list" + list);
        return hjtList;
    }

    @GetMapping("/detail/{id}")
    public HjtEntity toolDetail(@PathVariable Long id) {
        HjtEntity hjtDetail = hjtService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid tool Id:" + id));
//        log.info("hjtDetail" + hjtDetail);
        return hjtDetail;
    }


}
