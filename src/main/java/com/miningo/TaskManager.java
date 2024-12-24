package com.miningo;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;


import java.util.*;

public class TaskManager {
    private static final List<Task> tasks = new ArrayList<>();
    private static final Map<UUID, Set<String>> completedTasks = new HashMap<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean isTaskCompleted(PlayerEntity player, String taskName) {
        UUID playerId = player.getUuid();
        return completedTasks.getOrDefault(playerId, new HashSet<>()).contains(taskName);
    }

    public static void initialize() {

        ServerTickEvents.START_SERVER_TICK.register(server -> {
            server.getPlayerManager().getPlayerList().forEach(player -> {
                checkAndMarkTask(player);
            });
        });
    }

    public static void checkAndMarkTask(PlayerEntity player) {
        UUID playerId = player.getUuid();
        Set<String> playerCompleted = completedTasks.getOrDefault(playerId, new HashSet<>());

        for (Task task : tasks) {
            if (!playerCompleted.contains(task.getName()) && task.isCompleted(player) && task.isCompletedQuantidade(player)) {
                playerCompleted.add(task.getName());
                player.giveItemStack(new ItemStack(Items.DIAMOND, 1));
            }
        }

        completedTasks.put(playerId, playerCompleted);
    }
}
