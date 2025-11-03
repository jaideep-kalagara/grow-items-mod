package net.supergamer.growitems.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.supergamer.growitems.screen.ModScreenHandlers;
import net.supergamer.growitems.screen.custom.ItemGrowerScreen;

public class GrowItemsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.ITEM_GROWER_SCREEN_HANDLER, ItemGrowerScreen::new);
    }
}
