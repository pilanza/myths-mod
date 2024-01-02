package net.tsuk1.mythsmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tsuk1.mythsmod.item.ModItems;

public class RiptidePenFormItem extends Item {
    public RiptidePenFormItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.setItemInHand(InteractionHand.MAIN_HAND,
                new ItemStack(ModItems.RIPTIDE_SWORD_FORM.get()));

        return super.use(level, player, hand);
    }
}
