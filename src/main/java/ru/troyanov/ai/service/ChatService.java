package ru.troyanov.ai.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.troyanov.ai.domain.entity.Chat;
import ru.troyanov.ai.domain.entity.Message;
import ru.troyanov.ai.domain.enums.ChatRole;
import ru.troyanov.ai.domain.repository.ChatRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private static final String SYSTEM_PROMPT_NAME = "Give the name of the chat according to the first message, a maximum of 10 characters";

    private final ChatRepository chatRepository;
    private final ChatClient chatClient;

    @Autowired
    private ChatService proxy;

    @Transactional
    public Chat create(String title) {
        var chat = Chat.builder()
                .title(title)
                .build();
        return chatRepository.save(chat);
    }

    @Transactional(readOnly = true)
    public Chat findById(UUID id) {
        var chat = chatRepository.findById(id).orElseThrow();
        Hibernate.initialize(chat.getMessages());
        return chat;
    }

    @Transactional(readOnly = true)
    public List<Chat> findAll() {
        return chatRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public void delete(UUID id) {
        chatRepository.deleteById(id);
    }

    @Transactional
    public void processInteraction(UUID id, String payload) {
        var chat = chatRepository.findById(id).orElseThrow();
        if (chat.getTitle() == null) {
            var title = chatClient.prompt().system(SYSTEM_PROMPT_NAME).user(payload).call().content();
            chat.setTitle(title);
        }
        proxy.addMessage(id, payload, ChatRole.USER);
        var content = chatClient.prompt().user(payload).call().content();
        proxy.addMessage(id, content, ChatRole.SYSTEM);
    }

    @Transactional
    public void addMessage(UUID chatId, String payload, ChatRole role) {
        var chat = chatRepository.findById(chatId).orElseThrow();
        chat.addMessage(Message.builder()
                .payload(payload)
                .chatRole(role)
                .build());
    }
}
