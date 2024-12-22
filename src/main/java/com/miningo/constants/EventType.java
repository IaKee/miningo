package com.miningo.constants;

public enum EventType {
    PLAYER_EFFECT(0), // targeted at the player
    TEAM_EFFECT(1), // targeted at your team
    GRIEFING_EFFECT(2), // targeted at enemy team
    GLOBAL_EFFECT(2), // targeted at everyone
    WORLD_EFFECT(3), // not targeted at anyone
    INVENTORY_EFFECT(4); // targeted at the player's inventory

    private final int value;

    private EventType(int value) {
        this.value = value;
    }
}
