package net.tsuk1.mythsmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.tsuk1.mythsmod.networking.ModPackets;
import net.tsuk1.mythsmod.networking.packet.SetGodParentC2SPacket;

public class SetGodParentCommand {
    public SetGodParentCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("setGodParent").then(Commands.literal("Poseidon")
                .executes((command) -> {
                    return setParent(command, "Poseidon");
                })));
        dispatcher.register(Commands.literal("setGodParent").then(Commands.literal("Hades")
                .executes((command) -> {
                    return setParent(command, "Hades");
                })));
        dispatcher.register(Commands.literal("setGodParent").then(Commands.literal("Zeus")
                .executes((command) -> {
                    return setParent(command, "Zeus");
                })));
    }

    private int setParent(CommandContext<CommandSourceStack> command, String godName) throws CommandSyntaxException {
        SetGodParentC2SPacket.ChangeGodName(godName);

        return Command.SINGLE_SUCCESS;
    }
}
