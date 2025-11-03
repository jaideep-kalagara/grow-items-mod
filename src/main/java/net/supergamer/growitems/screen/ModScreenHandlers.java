package net.supergamer.growitems.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.supergamer.growitems.GrowItems;
import net.supergamer.growitems.screen.custom.ItemGrowerScreenHandler;

public class ModScreenHandlers {
    public static final ScreenHandlerType<ItemGrowerScreenHandler> ITEM_GROWER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(GrowItems.MOD_ID, "item_grower_screen_handler"),
                    new ExtendedScreenHandlerType<>(ItemGrowerScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        GrowItems.LOGGER.info("Registering screen handlers for " + GrowItems.MOD_ID);
    }
}
