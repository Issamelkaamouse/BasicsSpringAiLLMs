package com.elkaamouse.SpringAIBasics.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamingController {
    private ChatClient chatClient;

    public StreamingController(ChatClient.Builder chatClient){
        this.chatClient = chatClient.build();
    }

    String sysMsg = "Vous etes un generateur des histoires";

    @GetMapping("/nostream")
    public String nostream(String query){
        return chatClient.prompt()
                .system(sysMsg)
                .user(query)
                .call()
                .content();
    }
    @GetMapping("/stream")
    public Flux<String> stream(String query){
        return chatClient.prompt()
                .system(sysMsg)
                .user(query)
                .stream()
                .content();
    }

}
