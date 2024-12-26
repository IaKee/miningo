package com.miningo.task;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.World;

public class MerchantVillagerTrader extends VillagerEntity {

    public MerchantVillagerTrader(EntityType<? extends VillagerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void trade(TradeOffer offer) {
        System.out.println(getTrocado());
    }
    public String getTrocado(){
        return "troca realizada com sucesso!!!!!";
    }

    public void copyDataFrom(VillagerEntity villager, boolean b) {
    }
}
