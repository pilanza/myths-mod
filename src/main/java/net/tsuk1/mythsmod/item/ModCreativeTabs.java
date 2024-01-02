package net.tsuk1.mythsmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tsuk1.mythsmod.MythsMod;
import net.tsuk1.mythsmod.block.ModBlocks;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MythsMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab>MYTHOLOGICAL_NATURAL_TAB = CREATIVE_MODE_TABS.register("mythological_natural_tab",
            () -> CreativeModeTab.builder().icon(()->new ItemStack(ModBlocks.CELESTIAL_BRONZE_ORE.get()))
                    .title(Component.translatable("creativetab.mythological_natural_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.CELESTIAL_BRONZE_ORE.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab>MYTHOLOGICAL_INGREDIENTS_TAB = CREATIVE_MODE_TABS.register("mythological_ingredients_tab",
            () -> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.CELESTIAL_BRONZE_INGOT.get()))
                    .title(Component.translatable("creativetab.mythological_ingredients_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.RAW_CELESTIAL_BRONZE.get());
                        pOutput.accept(ModItems.CELESTIAL_BRONZE_INGOT.get());
                    })
                    .build());


    public static final RegistryObject<CreativeModeTab> MYTHOLOGICAL_ARMORY_TAB = CREATIVE_MODE_TABS.register("mythological_armory_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.RIPTIDE_SWORD_FORM.get()))
                    .title(Component.translatable("creativetab.mythological_armory_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.RIPTIDE_PEN_FORM.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MYTHOLOGICAL_FOOD_TAB = CREATIVE_MODE_TABS.register("mythological_food_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.GODS_AMBROSIA.get()))
                    .title(Component.translatable("creativetab.mythological_food_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.GODS_AMBROSIA.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

