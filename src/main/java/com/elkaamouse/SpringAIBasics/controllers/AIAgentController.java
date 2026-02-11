package com.elkaamouse.SpringAIBasics.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIAgentController {
    private ChatClient chatClient;

    public AIAgentController(ChatClient.Builder builder, ChatMemory memory) {
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
    }

    @GetMapping("/chat")
    public String askAI(String prompt){
        return chatClient.prompt()
                .system("repondre toujours en majuscule")
                .user(prompt)
                .call()
                .content();
    }
}
