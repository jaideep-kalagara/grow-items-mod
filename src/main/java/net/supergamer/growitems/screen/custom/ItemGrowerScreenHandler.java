package net.supergamer.growitems.screen.custom;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.supergamer.growitems.block.entity.custom.ItemGrowerBlockEntity;
import net.supergamer.growitems.screen.ModScreenHandlers;
import net.supergamer.growitems.screen.custom.slots.ItemGrowerFuelSlot;
import net.supergamer.growitems.screen.custom.slots.ItemGrowerInputSlot;
import net.supergamer.growitems.screen.custom.slots.ItemGrowerOutputSlot;
import net.supergamer.growitems.screen.custom.slots.ItemGrowerUpgradeSlot;

public class ItemGrowerScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate properties;


    public ItemGrowerScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getEntityWorld().getBlockEntity(pos));
    }

    public ItemGrowerScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        this(syncId, playerInventory, blockEntity, ((ItemGrowerBlockEntity) blockEntity).getPropertyDelegate());
    }

    public ItemGrowerScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate delegate) {
        super(ModScreenHandlers.ITEM_GROWER_SCREEN_HANDLER, syncId);
        this.inventory = ((Inventory) blockEntity);

        this.addProperties(delegate);
        this.properties = delegate;

        this.addSlot(new ItemGrowerInputSlot(inventory, 0, 56, 17));
        this.addSlot(new ItemGrowerFuelSlot(inventory, 1, 56, 53));
        this.addSlot(new ItemGrowerOutputSlot(inventory, 2, 116, 35));

        // upgrade slots
        for (int i = 0; i < 3; i++) {
            this.addSlot(new ItemGrowerUpgradeSlot(inventory, i + 3, 179, 9  + i * 18));
        }

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    public int getProgress() {
        return this.properties.get(0);
    }

    public int getMaxProgress() {
        return this.properties.get(1);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot.hasStack()) {
            ItemStack original = slot.getStack();
            newStack = original.copy();

            int beSlotCount = this.inventory.size();

            if (invSlot < beSlotCount) {

                if (!this.insertItem(original, beSlotCount, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(original, newStack);
            }
            else {

                if (this.slots.get(3).canInsert(original)) {
                    if (!this.insertItem(original, 3, 6, false)) return ItemStack.EMPTY;
                }

                else if (this.slots.get(1).canInsert(original)) {
                    if (!this.insertItem(original, 1, 2, false)) return ItemStack.EMPTY;
                }

                else if (this.slots.get(0).canInsert(original)) {
                    if (!this.insertItem(original, 0, 1, false)) return ItemStack.EMPTY;
                }
                else {
                    return ItemStack.EMPTY;
                }
            }

            if (original.isEmpty()) slot.setStack(ItemStack.EMPTY);
            else slot.markDirty();

            if (original.getCount() == newStack.getCount()) return ItemStack.EMPTY;

            slot.onTakeItem(player, original);
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
