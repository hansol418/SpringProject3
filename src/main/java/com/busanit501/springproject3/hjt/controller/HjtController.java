package com.busanit501.springproject3.hjt.controller;

import com.busanit501.springproject3.hjt.service.HjtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hammor")
@Log4j2
@RequiredArgsConstructor
public class HjtController {

    @Autowired
    private HjtService hjtService;
}
