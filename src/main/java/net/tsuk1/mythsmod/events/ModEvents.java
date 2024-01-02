package net.tsuk1.mythsmod.events;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.tsuk1.mythsmod.MythsMod;
import net.tsuk1.mythsmod.commands.GetGodParentCommand;
import net.tsuk1.mythsmod.commands.SetGodParentCommand;
import net.tsuk1.mythsmod.god_parent.PlayerGodParent;
import net.tsuk1.mythsmod.god_parent.PlayerGodParentProvider;

@Mod.EventBusSubscriber(modid = MythsMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).isPresent()) {
                event.addCapability(new ResourceLocation(MythsMod.MOD_ID, "god_parent"), new PlayerGodParentProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerGodParent.class);
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).ifPresent(oldStore -> {
                event.getEntity().getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new GetGodParentCommand(event.getDispatcher());
        new SetGodParentCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}