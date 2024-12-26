package com.miningo.task;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

abstract class Task {
    final String name;
    final String description;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }




    // Método para verificar se o jogador completou a tarefa
    public boolean isCompleted(PlayerEntity player) {
        return false;
    }
    public boolean isCompleted(PlayerEntity player, EntityType<?> killedEntityType){
        return false;
    }
}
