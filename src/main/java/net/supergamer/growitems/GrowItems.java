package net.supergamer.growitems;

import net.fabricmc.api.ModInitializer;
import net.supergamer.growitems.advancements.critera.ModCriteria;
import net.supergamer.growitems.block.ModBlocks;
import net.supergamer.growitems.block.entity.ModBlockEntities;
import net.supergamer.growitems.item.ModItems;
import net.supergamer.growitems.item.ModTags;
import net.supergamer.growitems.item.custom.component.ModComponentTypes;
import net.supergamer.growitems.screen.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrowItems implements ModInitializer {

    public static final String MOD_ID = "growitems";
    // logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModCriteria.registerModCriteria();
        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        ModTags.registerModTags();
        ModComponentTypes.registerModComponents();
        ModBlockEntities.registerModBlockEntities();
        ModScreenHandlers.registerScreenHandlers();
        LOGGER.info(MOD_ID + " mod initialized!");
    }
}
