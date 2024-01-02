package net.tsuk1.mythsmod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.tsuk1.mythsmod.god_parent.PlayerGodParentProvider;
import net.tsuk1.mythsmod.networking.ModPackets;

import java.util.function.Supplier;

public class SetGodParentC2SPacket {
    public static String parentName = "";

    public static void ChangeGodName(String godName) {
        parentName = godName;
        ModPackets.sendToServer(new SetGodParentC2SPacket());
    }

    public SetGodParentC2SPacket() {

    }

    public SetGodParentC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();

            player.getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).ifPresent(godParent -> {
                godParent.setGodParent(parentName);
            });
            player.sendSystemMessage(Component.literal(parentName+" has claimed you as their child"));
        });
    }
}
