package net.supergamer.growitems.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.supergamer.growitems.GrowItems;
import net.supergamer.growitems.block.custom.ItemGrower;

import java.util.function.Function;

public class ModBlocks {

    // create the Item Grower
    public static final Block ITEM_GROWER = register("item_grower", ItemGrower::new, AbstractBlock.Settings.create()
            .luminance(state -> state.get(Properties.LIT) ? 15 : 0)
            .sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().strength(1.5f), true);

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);

        Block block = blockFactory.apply(settings.registryKey(blockKey));


        // if shouldRegisterItem is true, register the block item
        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(GrowItems.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(GrowItems.MOD_ID, name));
    }

    public static void registerModBlocks() {
        // add item to group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(Blocks.SMOKER, ITEM_GROWER));

        GrowItems.LOGGER.info("Registering blocks for " + GrowItems.MOD_ID);
    }
}
