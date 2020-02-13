package com.example.websocketdemo.controller;

import com.example.websocketdemo.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.GenericMessagingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate template;


    //一对一推送消息
    @Scheduled(fixedRate = 10000)
    @RequestMapping("/username")
    public void sendQueueMessage() {
        System.out.println("后台一对一推送！");
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender("fasong");
        chatMessage.setContent(new Date().toString());
        chatMessage.setType(ChatMessage.MessageType.CHAT);
        this.template.convertAndSend("/topic/public", chatMessage);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
