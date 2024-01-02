package net.tsuk1.mythsmod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.tsuk1.mythsmod.god_parent.PlayerGodParentProvider;

import java.util.function.Supplier;

public class GetGodParentC2SPacket {
    public GetGodParentC2SPacket() {

    }

    public GetGodParentC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();

            player.getCapability(PlayerGodParentProvider.PLAYER_GOD_PARENT).ifPresent(godParent -> {
                String godName = godParent.getGod();
                if(godName != "" && godName != null) {
                    player.sendSystemMessage(Component.literal("Your Godly Parent is " + godName));
                } else {
                    player.sendSystemMessage(Component.literal("Your Godly Parent didn't claimed you yet."));
                }
            });
        });
    }
}
