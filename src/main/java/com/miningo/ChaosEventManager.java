package com.miningo;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class ChaosEventManager {
    private static final Random random = new Random();
    private static int tickCounter = 0;
    private static int spawnCount = 0;

    public static void initialize() {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            tickCounter++;
            if (tickCounter >= 20 && spawnCount < 20){
                tickCounter = 0;
                server.getWorlds().forEach(world -> {
                    if (world instanceof ServerWorld) {
                        triggerCreeperSpawn((ServerWorld) world);
                    }
                });
            }
        });
    }

    private static void triggerCreeperSpawn(ServerWorld world) {
        world.getPlayers().forEach(player -> {
            Vec3d playerPos = player.getPos();
            double offsetX = (random.nextDouble() * 10) - 5; // -5 a +5
            double offsetZ = (random.nextDouble() * 10) - 5; // -5 a +5

            BlockPos spawnPos = new BlockPos(
                (int) Math.floor(playerPos.x + offsetX),
                (int) Math.floor(playerPos.y),
                (int) Math.floor(playerPos.z + offsetZ)
            );

            // Spawns a creeper at the calculated position
            CreeperEntity newCreeper = EntityType.CREEPER.spawn(world, spawnPos, SpawnReason.SPAWN_ITEM_USE);
            spawnCount++;
        });
    }

}
