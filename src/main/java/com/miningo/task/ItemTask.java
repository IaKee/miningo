package com.miningo.task;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class ItemTask extends Task{
    final ItemStack condition;
    final int quantidade;

    public ItemTask(String name, String description, ItemStack condition, int quantidade){
        super(name,description);
        this.condition = condition;
        this.quantidade = quantidade;
    }

    @Override
    public boolean isCompleted(PlayerEntity player) {
        if(player.getInventory().contains(condition) && isCompletedQuantity(player)) {
            return true;
        };
        return false;
    }

    private boolean isCompletedQuantity(PlayerEntity player) {
        // Conta a quantidade total do item no inventário do jogador
        int totalCount = player.getInventory().main.stream()
                .filter(stack -> stack.getItem() == condition.getItem()) // Filtra pelo item correspondente
                .mapToInt(ItemStack::getCount) // Soma a quantidade de cada stack
                .sum();

        // Verifica se o total é maior ou igual à quantidade necessária
        return totalCount >= quantidade;
    }
}
