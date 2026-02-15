package com.elkaamouse.SpringAIBasics.controllers;

import com.elkaamouse.SpringAIBasics.outputs.MovieList;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIStructuredController {

    private ChatClient chatClient;

    public AIStructuredController(ChatClient.Builder builder, ChatMemory memory) {
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
    }

    @GetMapping("/askAgent")
    public MovieList askAI(String prompt){
        String sysMsg = """
                Vous etes dans le domaine de cinema
                """;
        return chatClient.prompt()
                .system(sysMsg)
                .user(prompt)
                .call()
                .entity(MovieList.class);
    }
}
