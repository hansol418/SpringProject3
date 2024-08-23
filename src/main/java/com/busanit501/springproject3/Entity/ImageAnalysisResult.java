package com.busanit501.springproject3.Entity;

import lombok.Data;


import java.util.Map;

@Data
public class ImageAnalysisResult {

    private int predictedClassIndex;
    private String predictedClassLabel;
    private float confidence;
    private Map<String, Float> classConfidences;

    // Getters and Setters

}
