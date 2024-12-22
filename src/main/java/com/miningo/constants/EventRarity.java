package com.miningo.constants;

public enum EventRarity {
    VERY_COMMON(1),
    COMMON(2),
    UNCOMMON(3),
    RARE(4),
    VERY_RARE(5),
    LEGENDARY(6),
    MYTHIC(7),
    UNIQUE(8);

    private final int value;

    private EventRarity(int value) {
        this.value = value;
    }
}
