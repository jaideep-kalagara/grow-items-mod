package net.supergamer.growitems.screen.custom.slots;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.supergamer.growitems.block.ModBlocks;

public class ItemGrowerInputSlot extends Slot {
    public ItemGrowerInputSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return !stack.isOf(ModBlocks.ITEM_GROWER.asItem());
    }
}
