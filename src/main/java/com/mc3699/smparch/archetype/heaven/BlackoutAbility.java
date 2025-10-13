package com.mc3699.smparch.archetype.heaven;

import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class BlackoutAbility extends BaseAbility {
    @Override
    public float getUseCost() {
        return 4;
    }

    @Override
    public Component getName() {
        return Component.literal("Blackout");
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);
        AABB range = player.getBoundingBox().inflate(8);
        ServerLevel serverLevel = player.serverLevel();

        List<LivingEntity> effectEntities = serverLevel.getEntitiesOfClass(LivingEntity.class, range);

        effectEntities.forEach(entity -> {
            if(!entity.is(player))
            {
                entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 8*20, 2));
            }
        });
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }
}
