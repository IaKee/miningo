package com.miningo.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_BINGO = "key.category.miningo.bingo";
    public static final String KEY_BINGO_CARD = "key.category.miningo.bingo_card";
    public static final String KEY_BINGO_CARD_NEXT = "key.miningo.bingo_card.next";
    public static final String KEY_BINGO_CARD_PREVIOUS = "key.miningo.bingo_card.previous";

    public static KeyBinding bingoCardKey;


    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (bingoCardKey.wasPressed()) {
                // TODO: Open Bingo Card
                //client.player.sendChatMessage("Bingo Card");
            }
        });
    }

    public static void register() {
        bingoCardKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            KEY_BINGO_CARD,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_TAB,
            KEY_CATEGORY_BINGO));

        registerKeyInputs();
    }
}
