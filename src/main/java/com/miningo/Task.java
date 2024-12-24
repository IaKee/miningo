package com.miningo;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackSet;
import net.minecraft.item.Items;

public class Task {
    private final String name;
    private final String description;
    private final ItemStack condition;
    private final int quantidade;

    public Task(String name, String description, ItemStack condition, int quantidade) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.quantidade = quantidade;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public boolean isCompletedQuantidade(PlayerEntity player) {
        // Conta a quantidade total do item no inventário do jogador
        int totalCount = player.getInventory().main.stream()
                .filter(stack -> stack.getItem() == condition.getItem()) // Filtra pelo item correspondente
                .mapToInt(ItemStack::getCount) // Soma a quantidade de cada stack
                .sum();

        // Verifica se o total é maior ou igual à quantidade necessária
        return totalCount >= quantidade;
    }

    // Método para verificar se o jogador completou a tarefa
    public boolean isCompleted(PlayerEntity player) {
        if(player.getInventory().contains(condition)) {
            return true;
        };
        return false;
    }
}
