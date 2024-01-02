package net.tsuk1.mythsmod.effect.custom;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.tsuk1.mythsmod.god_parent.PlayerGodParentProvider;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class GodsFoodEffectI extends InstantenousMobEffect {
    public GodsFoodEffectI(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        pLivingEntity.getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).ifPresent(godParent -> {
            String godName = godParent.getGod();
            if(godName != "") {
                pLivingEntity.heal((float)Math.max(4 << pAmplifier, 0));
            } else {
                pLivingEntity.displayFireAnimation();
            }
        });
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity pLivingEntity, int pAmplifier, double pHealth) {
        pLivingEntity.getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).ifPresent(godParent -> {
            String godName = godParent.getGod();
            if(godName != "") {
                int i = (int)(pHealth * (double)(4 << pAmplifier) + 0.5D);
                pLivingEntity.heal((float)i);
            } else {
                pLivingEntity.displayFireAnimation();
            }
        });
    }
}