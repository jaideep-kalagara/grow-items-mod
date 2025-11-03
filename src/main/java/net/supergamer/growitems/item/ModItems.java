package net.supergamer.growitems.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.supergamer.growitems.GrowItems;
import net.supergamer.growitems.block.ModBlocks;
import net.supergamer.growitems.item.custom.component.ModComponentTypes;
import net.supergamer.growitems.item.custom.component.RichSoilUpgradeComponent;

import java.util.function.Function;

public class ModItems {

    // create upgrade items
    public static final Item RICH_SOIL_UPGRADE = register("rich_soil_upgrade", Item::new,
            new Item.Settings().maxCount(1)
                    .component(ModComponentTypes.RICH_SOIL_UPGRADE_COMPONENT_COMPONENT_TYPE, new RichSoilUpgradeComponent()));

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(GrowItems.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }
    public static void registerModItems() {
        // add to item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(ModBlocks.ITEM_GROWER, RICH_SOIL_UPGRADE));
        GrowItems.LOGGER.info("Registering items for " + GrowItems.MOD_ID);
    }
}
