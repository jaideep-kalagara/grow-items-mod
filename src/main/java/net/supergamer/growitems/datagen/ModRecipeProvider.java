package net.supergamer.growitems.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.supergamer.growitems.block.ModBlocks;
import net.supergamer.growitems.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                createShaped(RecipeCategory.MISC, ModBlocks.ITEM_GROWER, 1)
                        .pattern("ccc")
                        .pattern("ada")
                        .pattern("ccc")
                        .input('c', ItemTags.STONE_CRAFTING_MATERIALS)
                        .input('a', Items.AMETHYST_SHARD)
                        .input('d', Items.DIAMOND_BLOCK)
                        .group("growitems")
                        .criterion(hasItem(Items.DIAMOND_BLOCK), conditionsFromItem(Items.DIAMOND_BLOCK))
                        .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.RICH_SOIL_UPGRADE, 1)
                        .pattern("dcd")
                        .pattern("ppp")
                        .pattern("dcd")
                        .input('c', Items.COARSE_DIRT)
                        .input('p', Items.PAPER)
                        .input('d', Items.DIAMOND)
                        .group("growitems")
                        .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                        .criterion(hasItem(Items.COARSE_DIRT), conditionsFromItem(Items.COARSE_DIRT))
                        .offerTo(exporter);
            }
        };
    }


    @Override
    public String getName() {
        return "";
    }
}
