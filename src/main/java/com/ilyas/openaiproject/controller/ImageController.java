package com.ilyas.openaiproject.controller;

import com.ilyas.openaiproject.model.FormInput;
import com.ilyas.openaiproject.model.ImageResponse;
import com.ilyas.openaiproject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(path = "/")
    public String drawImage(Model model, FormInput dto) throws Exception {
        model.addAttribute("prompt", dto.getPrompt());
        model.addAttribute("size", dto.getSize());
        model.addAttribute("piece",dto.getPiece());

        ImageResponse imageResponse = imageService.getImages(dto.getPrompt(),dto.getSize(), dto.getPiece());
        model.addAttribute("createdDate", imageResponse.getCreated());
        model.addAttribute("imageUriList", imageResponse.getData());
        model.addAttribute("isSearched", true);
        return "image";
    }

    @GetMapping(path = "/")
    public String paintImage() {
        return "image";
    }


}
