package net.tsuk1.mythsmod.item.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class YankeeCapItem extends Item {
    public YankeeCapItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return armorType.getType() == EquipmentSlot.HEAD.getType();
    }
}
