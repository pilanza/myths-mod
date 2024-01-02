package net.tsuk1.mythsmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsuk1.mythsmod.MythsMod;
import net.tsuk1.mythsmod.effect.custom.GodsFoodEffectI;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MythsMod.MOD_ID);

    public static final RegistryObject<MobEffect> GODS_FOOD_I = MOB_EFFECTS.register("gods_food_i",
            () -> new GodsFoodEffectI(MobEffectCategory.NEUTRAL, 8501017));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
