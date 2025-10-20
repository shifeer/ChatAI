package ru.troyanov.ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.troyanov.ai.service.ChatService;

import java.util.UUID;

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

    @PostMapping
    public String createChat(Model model) {
        var chat = chatService.create(UUID.randomUUID().toString());
        return String.format("redirect:/chats/%S", chat.getId());
    }

    @DeleteMapping("/{id}")
    public String deleteChat(@PathVariable UUID id) {
        chatService.delete(id);
        return "redirect:/chats";
    }

    @PostMapping("/send")
    public String onSend(UUID id, String payload) {



        return String.format("redirect:/chats/%S", id);
    }

    @GetMapping("/{id}")
    public String viewChat(@PathVariable UUID id, Model model) {
        var chats = chatService.findAll();
        var chat = chatService.findById(id);
        model.addAttribute("chats", chats);
        model.addAttribute("chat", chat);
        model.addAttribute("messages",  chat.getMessages());
        return "chat.html";
    }

}
