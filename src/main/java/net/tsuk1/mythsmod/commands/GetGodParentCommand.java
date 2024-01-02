package net.tsuk1.mythsmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.tsuk1.mythsmod.networking.ModPackets;
import net.tsuk1.mythsmod.networking.packet.GetGodParentC2SPacket;

public class GetGodParentCommand {
    public GetGodParentCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("get").then(Commands.literal("godParent").executes(this::getGodParent)));
    }

    private int getGodParent(CommandContext<CommandSourceStack> command) throws CommandSyntaxException {
        ModPackets.sendToServer(new GetGodParentC2SPacket());

        return Command.SINGLE_SUCCESS;
    }
}
