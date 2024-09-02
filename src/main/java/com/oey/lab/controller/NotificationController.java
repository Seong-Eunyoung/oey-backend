package com.oey.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RequiredArgsConstructor
@Controller
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyAllUsers(String content) {
        messagingTemplate.convertAndSend("/topic/notifications", content);
    }

}