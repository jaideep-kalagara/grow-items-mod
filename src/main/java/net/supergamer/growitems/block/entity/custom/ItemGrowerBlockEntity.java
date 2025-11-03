package net.supergamer.growitems.block.entity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.supergamer.growitems.block.entity.ImplementedInventory;
import net.supergamer.growitems.block.entity.ModBlockEntities;
import net.supergamer.growitems.item.ModItems;
import net.supergamer.growitems.screen.custom.ItemGrowerScreenHandler;
import org.jetbrains.annotations.Nullable;

public class ItemGrowerBlockEntity extends BlockEntity implements ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    // create inventory
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(6, ItemStack.EMPTY);

    // tick progress
    private int progress = 0;
    private final int MAX_PROGRESS = 400; // 20 seconds
    private int max_progress = 400; // 20 seconds

    public ItemGrowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ITEM_GROWER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    // property delegate
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> max_progress;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) progress = value;
            if (index == 1) max_progress = value;
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    // NBT

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        progress = view.getInt("progress", 0);
        max_progress = view.getInt("max_progress", this.MAX_PROGRESS);
        Inventories.readData(view, items);
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        view.putInt("progress", progress);
        view.putInt("max_progress", max_progress);
        Inventories.writeData(view, items);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.growitems.item_grower");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ItemGrowerScreenHandler(syncId, playerInventory, player.getEntityWorld().getBlockEntity(this.pos), this.getPropertyDelegate());
    }

    public static void tick(World world, BlockPos pos, BlockState state, ItemGrowerBlockEntity be) {
        if (world.isClient()) return;

        ItemStack input  = be.items.get(0);
        ItemStack fuel   = be.items.get(1);
        ItemStack output = be.items.get(2);
        // upgrade slot slice
        DefaultedList<ItemStack> upgradeSlots = DefaultedList.ofSize(3, ItemStack.EMPTY);
        upgradeSlots.set(0, be.items.get(3));
        upgradeSlots.set(1, be.items.get(4));
        upgradeSlots.set(2, be.items.get(5));

        // get the speed subtraction modifier
        float speedSubtractionModifier = 0;
        for (ItemStack upgrade : upgradeSlots) {
            if (upgrade.isOf(ModItems.RICH_SOIL_UPGRADE)){
                speedSubtractionModifier += 0.1f; // 10% speed reduction x3 = 30% speed reduction

            }
        }


        // true max progress
        be.max_progress = (int) (be.MAX_PROGRESS * (1 - speedSubtractionModifier));
        if (!canRun(input, fuel, output)) {
            be.progress = 0;
            setLit(world, pos, state, false);
            markDirty(world, pos, state);
            return;
        }

        be.progress++;
        setLit(world, pos, state, be.progress > 0); // change the lighting of the block

        if (be.progress >= be.max_progress) {
            be.progress = 0;
            fuel.decrement(1);

            if (output.isEmpty()) {
                be.items.set(2, input.copyWithCount(1));
            } else {
                output.increment(1);
            }
        }

        markDirty(world, pos, state);
    }

    private static boolean canRun(ItemStack input, ItemStack fuel, ItemStack output) {
        if (input.isEmpty() || fuel.isEmpty()) return false;
        if (!output.isEmpty() && !input.isOf(output.getItem())) return false;
        return output.isEmpty() || output.getCount() < 64;
    }

    private static void setLit(World world, BlockPos pos, BlockState state, boolean lit) {
        if (state.get(Properties.LIT) != lit) {
            world.setBlockState(pos, state.with(Properties.LIT, lit), 3);
        }
    }
}
