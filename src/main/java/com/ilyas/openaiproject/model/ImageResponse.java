package com.ilyas.openaiproject.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ImageResponse {

    private Date created;
    private List<ImageUrl> data;

}
