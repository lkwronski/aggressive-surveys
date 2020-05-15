package com.agh.surveys.controller;

import com.agh.surveys.model.message.dto.MessageResponseDto;
import com.agh.surveys.service.message.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("messages")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8080"})
public class MessageController {

    @Autowired
    IMessageService messageService;

    @GetMapping("/{messageId}")
    public MessageResponseDto getMessage(@PathVariable(value = "messageId") Integer messageId) {
        return messageService.getMessageById(messageId);
    }

    @DeleteMapping("/{messageId}")
    public void deleteMessage(@PathVariable(value = "messageId") Integer messageId) {
        messageService.removeMessageById(messageId);
    }

    @PostMapping("/{messageId}/acknowledgments/{userNick}")
    public void acknowledgeMessage(@PathVariable(value = "messageId") Integer messageId,
                                   @PathVariable(value = "userNick") String userNick) {
        messageService.acknowledgeMessage(messageId, userNick);
    }

}
