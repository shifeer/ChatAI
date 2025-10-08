package ru.troyanov.ai.domain.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "chat")
@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat extends BaseEntity {

    private String title;

    @CreatedDate
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @OrderBy("createdAt DESC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat",  fetch = FetchType.LAZY)
    private Set<Message> messages;

}
