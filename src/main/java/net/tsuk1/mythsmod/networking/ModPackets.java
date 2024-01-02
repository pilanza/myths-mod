package net.tsuk1.mythsmod.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.tsuk1.mythsmod.MythsMod;
import net.tsuk1.mythsmod.networking.packet.GetGodParentC2SPacket;
import net.tsuk1.mythsmod.networking.packet.SetGodParentC2SPacket;
import org.lwjgl.system.windows.MSG;

public class ModPackets {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(MythsMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(SetGodParentC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(SetGodParentC2SPacket::toBytes)
                .decoder(SetGodParentC2SPacket::new)
                .consumerMainThread(SetGodParentC2SPacket::handle)
                .add();

        net.messageBuilder(GetGodParentC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(GetGodParentC2SPacket::toBytes)
                .decoder(GetGodParentC2SPacket::new)
                .consumerMainThread(GetGodParentC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG msg) {
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(MSG msg, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }
}
