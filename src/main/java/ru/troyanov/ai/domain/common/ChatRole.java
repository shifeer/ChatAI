package ru.troyanov.ai.domain.common;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

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
