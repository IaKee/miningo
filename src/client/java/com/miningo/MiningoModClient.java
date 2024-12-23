package com.miningo;

import com.miningo.events.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;

public class MiningoModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		System.out.println("test");
		KeyInputHandler.register();
	}
}