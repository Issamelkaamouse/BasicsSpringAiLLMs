package com.elkaamouse.SpringAIBasics.controllers;

import org.springframework.core.io.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class MultiModalController {
    private ChatClient chatClient;

    @Value("classpath:/images/facture.png")
    private Resource image;

    public MultiModalController(ChatClient.Builder chatClient){
        this.chatClient = chatClient.build();
    }

    @GetMapping("/describe")
    public String describeImage(){
        return chatClient.prompt()
                .system("Donner une description de l'image fournie?")
                .user(u->
                        u.text("donner moi le montant total ttc de la facture dans l'image ?")
                                .media(MediaType.IMAGE_JPEG, (org.springframework.core.io.Resource) image)
                ).call()
                .content();
    }


}
