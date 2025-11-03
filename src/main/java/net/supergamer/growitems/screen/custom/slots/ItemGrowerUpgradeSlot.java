package net.supergamer.growitems.screen.custom.slots;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.supergamer.growitems.item.ModTags;

public class ItemGrowerUpgradeSlot extends Slot {
    public ItemGrowerUpgradeSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }

    @Override
    public boolean canInsert(ItemStack stack) {

        return stack.isIn(ModTags.ITEM_GROWER_UPGRADES);
    }
}
