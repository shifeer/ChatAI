package ru.troyanov.ai.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.troyanov.ai.domain.entity.Chat;
import ru.troyanov.ai.domain.repository.ChatRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    @Transactional
    public void create(String title) {
        var chat = Chat.builder()
                .title(title)
                .build();
        chatRepository.save(chat);
    }

    @Transactional(readOnly = true)
    public Chat findById(UUID id) {
        var chat = chatRepository.findById(id).orElseThrow();
        Hibernate.initialize(chat.getMessages());
        return chat;
    }

    @Transactional(readOnly = true)
    public List<Chat> findAll() {
        return chatRepository.findAllOrderByCreatedAtDesc();
    }
}
