package com.miningo.server.events.db;

import com.miningo.server.events.ChaosEvent;

import java.util.Arrays;

import static com.miningo.constants.EventRarity.UNCOMMON;
import static com.miningo.constants.EventTag.*;
import static com.miningo.constants.EventType.WORLD_EFFECT;

public class AggressiveSnowman extends ChaosEvent {
    public AggressiveSnowman() {
        super(
            300,
            UNCOMMON,
            WORLD_EFFECT,
            0,
            "Looks like you made a few enemies...",
            "Let it snow",
            "AGRESSIVE_SNOWMAN",
            Arrays.asList(SNOW, ICE, COMBAT, AGGRESSIVE, MONSTER, WORLD, SCARY, SCALING, INCONVENIENT, INFINITE),
            "Spawns a random amount of angry Snowman's nearby. Scaling with the player's current xp level.");
    }
}