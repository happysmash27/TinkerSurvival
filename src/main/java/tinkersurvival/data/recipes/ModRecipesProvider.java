package tinkersurvival.data.recipes;

import java.util.function.Consumer;

import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.ItemLike;

import net.minecraftforge.common.Tags;

import tinkersurvival.TinkerSurvival;
import tinkersurvival.util.TagManager;
import tinkersurvival.world.TinkerSurvivalWorld;

public class ModRecipesProvider extends RecipeProvider {

    public ModRecipesProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public String getName() {
        return "TinkerSurvival - Recipies";
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ItemLike rockStone = TinkerSurvivalWorld.ROCK_STONE.get();
        ItemLike flintShard = TinkerSurvivalWorld.FLINT_SHARD.get();
        ItemLike plantFiber = TinkerSurvivalWorld.PLANT_FIBER.get();
        ItemLike plantString = TinkerSurvivalWorld.PLANT_STRING.get();
        ItemLike mortar = TinkerSurvivalWorld.MORTAR_AND_PESTLE.get();
        ItemLike plantPaste = TinkerSurvivalWorld.PLANT_PASTE.get();
        ItemLike ointment = TinkerSurvivalWorld.OINTMENT.get();
        ItemLike cloth = TinkerSurvivalWorld.CLOTH.get();
        ItemLike crudeKnife = TinkerSurvivalWorld.CRUDE_KNIFE.get();

        // Material Recipes
        ShapedRecipeBuilder.shaped(Blocks.COBBLESTONE)
                .define('R', rockStone)
                .pattern("RR")
                .pattern("RR")
                .unlockedBy("has_loose_rock", has(rockStone))
                .save(consumer, new ResourceLocation(TinkerSurvival.MODID, "cobblestone_from_rocks"));

        ShapedRecipeBuilder.shaped(Items.FLINT)
                .define('S', flintShard)
                .pattern("SS")
                .pattern("SS")
                .unlockedBy("has_flint_shard", has(flintShard))
                .save(consumer, new ResourceLocation(TinkerSurvival.MODID, "flint_from_shards"));

        ShapedRecipeBuilder.shaped(plantString)
                .define('F', plantFiber)
                .pattern("FF")
                .pattern("F ")
                .unlockedBy("has_plant_fiber", has(plantFiber))
                .save(consumer);

        ShapedRecipeBuilder.shaped(plantPaste)
                .define('F', plantFiber)
                .define('U', mortar)
                .pattern("F")
                .pattern("U")
                .unlockedBy("has_plant_fiber", has(plantFiber))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ointment)
                .define('P', plantPaste)
                .pattern("PP")
                .pattern("PP")
                .unlockedBy("has_plant_paste", has(plantPaste))
                .save(consumer);

        ShapedRecipeBuilder.shaped(cloth)
                .define('S', Items.STRING)
                .pattern("SSS")
                .unlockedBy("has_string", has(Items.STRING))
                .save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(plantString), Items.STRING, 0.1F, 50)
                .unlockedBy("has_plant_string", has(plantString))
                .save(consumer, new ResourceLocation(TinkerSurvival.MODID, "string_from_plant_string"));


        // Saw Blades
        ShapedRecipeBuilder.shaped(TinkerSurvivalWorld.CRUDE_SAW_BLADE.get())
                .define('D', flintShard)
                .define('S', plantString)
                .define('I', Items.STICK)
                .pattern("ID")
                .pattern("SD")
                .unlockedBy("has_plant_string", has(plantString))
                .save(consumer);
        
        // Tool Recipes
        ShapedRecipeBuilder.shaped(crudeKnife)
                .define('S', flintShard)
                .define('T', Items.STICK)
                .pattern("S")
                .pattern("T")
                .unlockedBy("has_flint_shard", has(flintShard))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TinkerSurvivalWorld.CRUDE_HATCHET.get())
                .define('R', rockStone)
                .define('S', plantString)
                .define('I', Items.STICK)
                .pattern("SR")
                .pattern("I ")
                .unlockedBy("has_loose_rock", has(rockStone))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TinkerSurvivalWorld.CRUDE_SAW_HANDLE.get())
                .define('S', plantString)
                .define('I', Items.STICK)
                .pattern("IS")
                .pattern(" I")
                .unlockedBy("has_plant_string", has(plantString))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TinkerSurvivalWorld.CRUDE_SAW.get())
                .define('H', TinkerSurvivalWorld.CRUDE_SAW_HANDLE.get())
                .define('B', TinkerSurvivalWorld.CRUDE_SAW_BLADE.get())
                .define('S', plantString)
                .pattern("BS")
                .pattern(" H")
                .unlockedBy("has_crude_saw_handle", has(TinkerSurvivalWorld.CRUDE_SAW_HANDLE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(mortar)
                .define('I', Items.STICK)
                .define('P', ItemTags.PLANKS)
                .define('R', rockStone)
                .pattern("  I")
                .pattern("PRP")
                .pattern(" P ")
                .unlockedBy("has_plant_fiber", has(plantFiber))
                .save(consumer);

        // Knife Recipes
        ShapelessRecipeBuilder.shapeless(Items.STICK)
                .requires(ItemTags.SAPLINGS)
                .requires(crudeKnife)
                .group("sticks")
                .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
                .save(consumer, new ResourceLocation(TinkerSurvival.MODID, "stick_from_sapling"));

        ShapelessRecipeBuilder.shapeless(Items.STRING, 2)
                .requires(ItemTags.WOOL)
                .requires(crudeKnife)
                .group("string")
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, new ResourceLocation(TinkerSurvival.MODID, "string_from_wool"));

        ShapelessRecipeBuilder.shapeless(flintShard, 2)
                .requires(TagManager.Items.FLINT_KNAPPABLE)
                .requires(TagManager.Items.KNIFE_TOOLS)
                .group("flint_shards")
                .unlockedBy("has_crude_knife", has(crudeKnife))
                .save(consumer);

        //Bandages
        ShapedRecipeBuilder.shaped(TinkerSurvivalWorld.CRUDE_BANDAGE.get())
                .define('P', plantString)
                .define('S', Items.STICK)
                .define('F', plantFiber)
                .pattern("SF")
                .pattern("PF")
                .unlockedBy("has_plant_string", has(plantString))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TinkerSurvivalWorld.BANDAGE.get())
                .define('P', plantString)
                .define('S', Items.STICK)
                .define('C', cloth)
                .define('O', ointment)
                .pattern("SC")
                .pattern("PO")
                .unlockedBy("has_ointment", has(ointment))
                .save(consumer);

        // Saw Recipes
        plankRecipeBuilder(consumer, Blocks.OAK_PLANKS, ItemTags.OAK_LOGS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.ACACIA_PLANKS, ItemTags.ACACIA_LOGS, "has_log");
        plankRecipeBuilder(consumer, Blocks.BIRCH_PLANKS, ItemTags.BIRCH_LOGS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.DARK_OAK_PLANKS, ItemTags.DARK_OAK_LOGS, "has_log");
        plankRecipeBuilder(consumer, Blocks.JUNGLE_PLANKS, ItemTags.JUNGLE_LOGS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.SPRUCE_PLANKS, ItemTags.SPRUCE_LOGS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.WARPED_PLANKS, ItemTags.WARPED_STEMS, "has_logs");
        plankRecipeBuilder(consumer, Blocks.CRIMSON_PLANKS, ItemTags.CRIMSON_STEMS, "has_logs");

        ShapelessRecipeBuilder.shapeless(Items.STICK, 2)
                .requires(ItemTags.PLANKS)
                .requires(TagManager.Items.SAW_TOOLS)
                .group("sticks")
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(consumer);

    }

    private static void plankRecipeBuilder(Consumer<FinishedRecipe> consumer, ItemLike item, Tag<Item> itemTag, String label) {
        ShapelessRecipeBuilder.shapeless(item, 2)
                .requires(itemTag)
                .requires(TinkerSurvivalWorld.CRUDE_SAW.get())
                .group("planks")
                .unlockedBy(label, has(itemTag))
                .save(consumer);
    }

}