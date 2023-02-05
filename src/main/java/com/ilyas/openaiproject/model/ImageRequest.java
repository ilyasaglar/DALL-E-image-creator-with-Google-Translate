package com.ilyas.openaiproject.model;

import lombok.Data;

@Data
public class ImageRequest {

    private String prompt;
    private int n;
    private String size;
    private String response_format;
}
