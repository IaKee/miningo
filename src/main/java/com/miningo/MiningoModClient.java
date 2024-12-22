package com.miningo;

import com.miningo.client.events.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;

public class MiningoModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        KeyInputHandler.register();
        //Acho q tem q criar a pasta lang
    }
}
