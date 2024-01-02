package net.tsuk1.mythsmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.tsuk1.mythsmod.networking.packet.SetGodParentC2SPacket;

public class RemoveGodParentCommand {
    public RemoveGodParentCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("godParent").then(Commands.literal("remove")
                .executes((command) -> {
                    return setParent(command, "");
                })));
    }

    private int setParent(CommandContext<CommandSourceStack> command, String godName) throws CommandSyntaxException {
        SetGodParentC2SPacket.ChangeGodName(godName);

        return Command.SINGLE_SUCCESS;
    }
}
