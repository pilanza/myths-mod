package net.tsuk1.mythsmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.tsuk1.mythsmod.effect.ModEffects;

public class ModFoods {
    public static final FoodProperties GODS_AMBROSIA = new FoodProperties.Builder().nutrition(20).fast()
            .saturationMod(0.5f)
            .effect(()->new MobEffectInstance(ModEffects.GODS_FOOD_I.get(), 20*30), 1).build();
}
