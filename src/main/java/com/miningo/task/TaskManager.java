package com.miningo.task;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.village.VillagerProfession;


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

        /*AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            //TODO alguma task relacionada a atacar uma vez alguma entidade
            return ActionResult.PASS; // Permite que outros eventos continuem
        });
        */

        /*UseEntityCallback.EVENT.register((playerEntity, world, hand, entity, entityHitResult) -> {
            //com isso tu consegue receber a entidade que tu apertou botao direito
            //a entity é a entidade que foi interagida

        });
        */
            //checkAndMarkCallbackTask(playerEntity,entity);
            //return ActionResult.PASS;


        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, player, entityKilled) -> {
            // Marca a tarefa como concluída ao matar a entidade

            checkAndMarkEventTask((PlayerEntity) player, entityKilled);

        });

        ServerTickEvents.START_SERVER_TICK.register(server -> {
            server.getPlayerManager().getPlayerList().forEach(player -> {
                checkAndMarkItemTask(player);
            });
        });
    }

    private static void checkAndMarkCallbackTask(PlayerEntity player,Entity callbackedEntity) {
        UUID playerId = player.getUuid();
        Set<String> playerCompleted = completedTasks.getOrDefault(playerId, new HashSet<>());

        for (Task task : tasks) {
            if (!playerCompleted.contains(task.getName()) && task.isCompleted(player, callbackedEntity.getType())) {
                System.out.println("passou do segundo IF");
                playerCompleted.add(task.getName());
                player.giveItemStack(new ItemStack(Items.DIAMOND, 1));
            }
            System.out.println("Não passou do segundo if, mas contabilizou a criatura morrendo");
        }

        completedTasks.put(playerId, playerCompleted);
    }

    private static void checkAndMarkEventTask(PlayerEntity player,Entity killedEntity) {
        UUID playerId = player.getUuid();
        Set<String> playerCompleted = completedTasks.getOrDefault(playerId, new HashSet<>());

        for (Task task : tasks) {
            if (!playerCompleted.contains(task.getName()) && task.isCompleted(player, killedEntity.getType())) {
                System.out.println("passou do segundo IF");
                playerCompleted.add(task.getName());
                player.giveItemStack(new ItemStack(Items.DIAMOND, 1));
            }
            System.out.println("Não passou do segundo if, mas contabilizou a criatura morrendo");
        }

        completedTasks.put(playerId, playerCompleted);
    }


    private static void checkAndMarkItemTask(PlayerEntity player) {
        UUID playerId = player.getUuid();
        Set<String> playerCompleted = completedTasks.getOrDefault(playerId, new HashSet<>());

        for (Task task : tasks) {
            if (!playerCompleted.contains(task.getName()) && task.isCompleted(player)) {
                playerCompleted.add(task.getName());
                player.giveItemStack(new ItemStack(Items.DIAMOND, 1));
            }
        }

        completedTasks.put(playerId, playerCompleted);
    }
}
