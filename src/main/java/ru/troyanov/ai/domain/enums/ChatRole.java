package ru.troyanov.ai.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ChatRole {
    SYSTEM("system"),
    USER("user");

    private final String value;

    public static ChatRole findByValue(String value) {
        return Arrays.stream(ChatRole.values())
                .filter(chatRole -> chatRole.value.equals(value))
                .findFirst()
                .orElseThrow();
    }

}
