package com.personal.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Priority {
    LOW(0),
    MEDIUM(1),
    HIGH(2);

    private int value;

    Priority(int value) {
        this.value = value;
    }
}
