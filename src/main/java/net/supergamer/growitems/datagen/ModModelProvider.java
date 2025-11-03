package net.supergamer.growitems.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.supergamer.growitems.block.ModBlocks;
import net.supergamer.growitems.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.ITEM_GROWER, TextureMap.textureSideTop(ModBlocks.ITEM_GROWER));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RICH_SOIL_UPGRADE, Models.GENERATED);
    }
}
