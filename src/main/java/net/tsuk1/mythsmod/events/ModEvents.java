package net.tsuk1.mythsmod.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.tsuk1.mythsmod.MythsMod;
import net.tsuk1.mythsmod.commands.GetGodParentCommand;
import net.tsuk1.mythsmod.commands.RemoveGodParentCommand;
import net.tsuk1.mythsmod.commands.SetGodParentCommand;
import net.tsuk1.mythsmod.effect.ModEffects;
import net.tsuk1.mythsmod.god_parent.PlayerGodParent;
import net.tsuk1.mythsmod.god_parent.PlayerGodParentProvider;
import net.tsuk1.mythsmod.item.ModItems;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = MythsMod.MOD_ID)
public class ModEvents {
    static int timeToHeal = 0;

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerGodParent.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).isPresent()) {
                event.addCapability(new ResourceLocation(MythsMod.MOD_ID, "god_parent"), new PlayerGodParentProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        event.getOriginal().reviveCaps();
        event.getOriginal().getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).ifPresent(oldStore -> {
            event.getEntity().getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).ifPresent(newStore -> {
                newStore.copyFrom(oldStore);
            });
        });
        event.getOriginal().invalidateCaps();
    }

    @SubscribeEvent
    public static void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        if (event.getItem().getItem() == ModItems.GODS_AMBROSIA.get()) {
            LivingEntity pLivingEntity = event.getEntity();
            pLivingEntity.getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).ifPresent(godParent -> {
                String godName = godParent.getGod();
                if (godName == "" || godName == null) {
                    pLivingEntity.setSecondsOnFire(30);
                } else {
                    pLivingEntity.addEffect(new MobEffectInstance(MobEffects.HEAL));
                    if(pLivingEntity.hasEffect(ModEffects.GODS_FOOD_I.get())){
                        pLivingEntity.removeEffect(ModEffects.GODS_FOOD_I.get());
                        pLivingEntity.addEffect(new MobEffectInstance(ModEffects.GODS_FOOD_II.get(), 20*60));
                    } else if (pLivingEntity.hasEffect(ModEffects.GODS_FOOD_II.get())) {
                        pLivingEntity.removeEffect(ModEffects.GODS_FOOD_II.get());
                        pLivingEntity.addEffect(new MobEffectInstance(ModEffects.GODS_FOOD_III.get(), 20*120));
                    } else if (pLivingEntity.hasEffect(ModEffects.GODS_FOOD_III.get())) {
                        pLivingEntity.addEffect(new MobEffectInstance(ModEffects.GODS_FOOD_III.get(), 20*120));
                        pLivingEntity.setSecondsOnFire(30);
                    }else {
                        pLivingEntity.addEffect(new MobEffectInstance(ModEffects.GODS_FOOD_I.get(), 20*30));
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new GetGodParentCommand(event.getDispatcher());
        new SetGodParentCommand(event.getDispatcher());
        new RemoveGodParentCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        player.getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).ifPresent(godParent -> {
            String godName = godParent.getGod();
            /*Poseidon Children Passive Powers*/
            if (player.isInWater() && Objects.equals(godName, "Poseidon")){
                timeToHeal += 1;
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST));
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING));
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE));
                if (timeToHeal >= 20*10) {
                    player.heal(1f);
                    timeToHeal = 0;
                }
            }
        });
    }
}
