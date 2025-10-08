package ru.troyanov.ai.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@Table(name = "chat")
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

    @JoinColumn(name = "chat_id")
    @ManyToOne
    private Chat chat;

}
