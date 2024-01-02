package net.tsuk1.mythsmod.effect.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.tsuk1.mythsmod.god_parent.PlayerGodParentProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GodsFoodEffectI extends MobEffect {
    public GodsFoodEffectI(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        pLivingEntity.getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).ifPresent(godParent -> {
            String godName = godParent.getGod();
            if(Objects.equals(godName, "") || godName == null) {
                pLivingEntity.setSecondsOnFire(30);
            }
        });
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}