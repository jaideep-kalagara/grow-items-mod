package net.supergamer.growitems.item;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.supergamer.growitems.GrowItems;

public class ModTags {

    public static final TagKey<Item> ITEM_GROWER_UPGRADES = TagKey.of(RegistryKeys.ITEM, Identifier.of(GrowItems.MOD_ID, "item_grower_upgrades"));

    public static void registerModTags() {
        GrowItems.LOGGER.info("Registering item tags for " + GrowItems.MOD_ID);
    }
}
