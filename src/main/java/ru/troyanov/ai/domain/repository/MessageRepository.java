package ru.troyanov.ai.domain.repository;

import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.troyanov.ai.domain.entity.Chat;
import ru.troyanov.ai.domain.entity.Message;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findByChat(UUID chatId);

}
