package net.tsuk1.mythsmod.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsuk1.mythsmod.MythsMod;
import net.tsuk1.mythsmod.item.custom.RiptidePenFormItem;
import net.tsuk1.mythsmod.item.custom.RiptideSwordFormItem;

public class ModItems {
    public static DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MythsMod.MOD_ID);

    /*Raw Minerals*/
    public static final RegistryObject<Item> RAW_CELESTIAL_BRONZE = ITEMS.register("raw_celestial_bronze",
            () -> new Item(new Item.Properties()));

    /*Minerals*/
    public static final RegistryObject<Item> CELESTIAL_BRONZE_INGOT = ITEMS.register("celestial_bronze_ingot",
            () -> new Item(new Item.Properties()));

    /*Mythological Weapons*/
    public static final RegistryObject<Item> RIPTIDE_PEN_FORM = ITEMS.register("riptide_pen_form",
            () -> new RiptidePenFormItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> RIPTIDE_SWORD_FORM = ITEMS.register("riptide_sword_form",
            () -> new RiptideSwordFormItem(Tiers.NETHERITE, 8, 5, new Item.Properties()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
