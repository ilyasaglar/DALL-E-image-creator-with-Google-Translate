package com.ilyas.openaiproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.ilyas.openaiproject.model.ImageRequest;
import com.ilyas.openaiproject.model.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@Service
public class ImageService {

    @Autowired
    private ObjectMapper jsonMapper;

    private HttpClient client = HttpClient.newHttpClient();

    @Value("${openai.url}")
    private String openAIApiUrl;

    @Value("${openai.apiKey}")
    private String openAIApiKey;

    @Value("${google.tranaslate.apiKey}")
    private String translateApiKey;


    public ImageResponse getImages(String prompt, String size, Integer piece) throws Exception {
        if(StringUtils.isEmpty(prompt)){
            throw  new IllegalArgumentException("Prompt not nul!!!");
        }

        if(piece == null){
            piece = 1;
        }else if (piece < 1){
            piece =1;
        }else if(piece >10){
            piece = 10;
        }

        prompt = convertTextToEnglish(prompt);
        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setPrompt(prompt);
        imageRequest.setSize(size);
        imageRequest.setN(piece);
        imageRequest.setResponse_format("url");

        ImageResponse imageResponse = getOpenAiImages(imageRequest);
        return imageResponse;
    }

    private String convertTextToEnglish(String prompt){
        Translation translation = null;
        try{
            Translate translate = TranslateOptions.newBuilder().setApiKey(translateApiKey).build().getService();

            Detection detection = translate.detect(prompt);
            String detectedLanguage = detection.getLanguage();
            if(detectedLanguage.equals("en")){
                return prompt;
            }

            translation = translate.translate(
                    prompt,
                    Translate.TranslateOption.sourceLanguage(detectedLanguage),
                    Translate.TranslateOption.targetLanguage("en"));

        }catch (Exception e){
           e.printStackTrace();
        }

        if(translation == null){
            throw  new IllegalArgumentException("The entered word could not be translated into English!");
        }

        return  translation.getTranslatedText();
    }

    private ImageResponse getOpenAiImages(ImageRequest imageRequest) throws IOException, InterruptedException {
        String responseBody = postToOpenAiApi(imageRequest);
        ImageResponse imageResponse = jsonMapper.readValue(responseBody, ImageResponse.class);
        return  imageResponse;

    }

    private  String postToOpenAiApi(ImageRequest imageRequest) throws IOException, InterruptedException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(imageRequest);

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(openAIApiUrl))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openAIApiKey)
                .POST(HttpRequest.BodyPublishers.ofString(json)).build();
       return  client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }


}
