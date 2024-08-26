package com.busanit501.springproject3.hjt.controller;

import com.busanit501.springproject3.hjt.domain.HjtEntity;
import com.busanit501.springproject3.hjt.service.HjtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hammor")
@Log4j2
@RequiredArgsConstructor
public class HjtController {

    @Autowired
    private HjtService hjtService;

    @GetMapping("/")
    public String listTools(Model model) {
        List<HjtEntity> tools = hjtService.findAll();
        model.addAttribute("tools", tools);
        return "tool_list";
    }

    @GetMapping("/tool/{id}")
    public String toolDetail(@PathVariable Long id, Model model) {
        HjtEntity hjtEntity = hjtService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid tool Id:" + id));
        model.addAttribute("tool", hjtEntity);
        return "tool_detail";
    }
}
