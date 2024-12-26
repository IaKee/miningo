package com.miningo.task;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

public class CallbackTask extends Task{
    private final EntityType<?> entityType;

    public CallbackTask(String name, String description, EntityType<?> entityType) {
        super(name,description);
        this.entityType = entityType;
    }

    @Override
    public boolean isCompleted(PlayerEntity player, EntityType<?> killedEntityType) {
        return killedEntityType == this.entityType;

    }
}
