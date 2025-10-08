package ru.troyanov.ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.troyanov.ai.service.ChatService;

@Controller
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService  chatService;

    @GetMapping
    public String index(Model model) {
        var chats = chatService.findAll();
        model.addAttribute("chats", chats);
        return "chat.html";
    }


}
