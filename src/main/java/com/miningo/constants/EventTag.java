package com.miningo.constants;

public enum EventTag {
    SNOW(0),
    ICE(1),
    COMBAT(2),
    NEUTRAL(3),
    MONSTER(4),
    WORLD(5),
    SCARY(6),
    SCALING(7),
    INCONVENIENT(8),
    INFINITE(9),
    AGGRESSIVE(10);


    private final int value;

    private EventTag(int value) {
        this.value = value;
    }
}
