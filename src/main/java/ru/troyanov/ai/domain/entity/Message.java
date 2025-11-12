package ru.troyanov.ai.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.troyanov.ai.domain.enums.ChatRole;

import java.time.OffsetDateTime;

@Entity
@Table(name = "message")
@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseEntity {

    private String payload;

    @CreatedDate
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "chat_role")
    @Enumerated(EnumType.STRING)
    private ChatRole chatRole;

    @JoinColumn(name = "chat_id")
    @ManyToOne
    private Chat chat;

}
