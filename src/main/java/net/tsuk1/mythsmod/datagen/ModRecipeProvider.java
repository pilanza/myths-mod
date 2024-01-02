package net.tsuk1.mythsmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.tsuk1.mythsmod.MythsMod;
import net.tsuk1.mythsmod.block.ModBlocks;
import net.tsuk1.mythsmod.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> CELESTIAL_BRONZE_SMELTABLES = List.of(
            ModItems.RAW_CELESTIAL_BRONZE.get(),
            ModBlocks.CELESTIAL_BRONZE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        /*ORE TO INGOT*/
        oreBlasting(pWriter, CELESTIAL_BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.CELESTIAL_BRONZE_INGOT.get(), 0.25f, 100, "celestial_bronze");
        oreSmelting(pWriter, CELESTIAL_BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.CELESTIAL_BRONZE_INGOT.get(), 0.25f, 200, "celestial_bronze");

        /*MYTHOLOGICAL WEAPONS*/
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.RIPTIDE_PEN_FORM.get())
                .pattern("WCW")
                .pattern(" C ")
                .pattern(" S ")
                .define('W', Items.WATER_BUCKET)
                .define('C', ModItems.CELESTIAL_BRONZE_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.CELESTIAL_BRONZE_INGOT.get()), has(ModItems.CELESTIAL_BRONZE_INGOT.get()))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  MythsMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
