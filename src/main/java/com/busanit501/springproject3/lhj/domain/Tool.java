package com.busanit501.springproject3.lhj.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tool")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toolName;
    private String description;
    private String images;
    private String imgText;
    private String metadata;

    // Getters and setters
}