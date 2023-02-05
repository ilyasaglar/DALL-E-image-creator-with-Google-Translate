package com.ilyas.openaiproject.model;

import lombok.Data;

@Data
public class FormInput {

    private String prompt;
    private String size;
    private int piece;
}
