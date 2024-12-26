package com.miningo.task;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.advancement.criterion.EnterBlockCriterion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

import java.util.function.Predicate;

public class EventTask extends Task{
    private final EntityType<?> entityType;

    public EventTask(String name, String description, EntityType<?> entityType) {
        super(name,description);
        this.entityType = entityType;
    }

    @Override
    public boolean isCompleted(PlayerEntity player, EntityType<?> killedEntityType) {
        return killedEntityType == this.entityType;

    }
}
