package net.supergamer.growitems.screen.custom.slots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.supergamer.growitems.advancements.critera.ModCriteria;

public class ItemGrowerOutputSlot extends Slot {
    public ItemGrowerOutputSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            ModCriteria.ITEM_GROWN.trigger(serverPlayer);
        }
        super.onTakeItem(player, stack);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false; // never allow inserting into output slot
    }
}
