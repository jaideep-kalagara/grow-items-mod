package net.supergamer.growitems.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.supergamer.growitems.GrowItems;
import net.supergamer.growitems.block.ModBlocks;
import net.supergamer.growitems.block.entity.custom.ItemGrowerBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<ItemGrowerBlockEntity> ITEM_GROWER_BLOCK_ENTITY =
            register("item_grower_block_entity", ItemGrowerBlockEntity::new, ModBlocks.ITEM_GROWER);


    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(GrowItems.MOD_ID, name), FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void registerModBlockEntities() {
        GrowItems.LOGGER.info("Registering block entities for " + GrowItems.MOD_ID);
    }
}
