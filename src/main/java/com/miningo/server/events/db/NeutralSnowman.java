package com.miningo.server.events.db;

import com.miningo.server.events.ChaosEvent;

import java.util.Arrays;

import static com.miningo.constants.EventRarity.UNCOMMON;
import static com.miningo.constants.EventTag.*;
import static com.miningo.constants.EventType.WORLD_EFFECT;

public class NeutralSnowman extends ChaosEvent {
    public NeutralSnowman() {
        super(
            300,
            UNCOMMON,
            WORLD_EFFECT,
            0,
            "Looks like you made a friend...",
            "Let it snow",
            "NEUTRAL_SNOWMAN",
            Arrays.asList(SNOW, ICE, COMBAT, NEUTRAL, MONSTER, WORLD, SCARY, SCALING, INCONVENIENT, INFINITE),
            "Spawns a random amount of Snowman's nearby. Scaling with the player's current xp level. They seem to be harmless, for now...");
    }
}