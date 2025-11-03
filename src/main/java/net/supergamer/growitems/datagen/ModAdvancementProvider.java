package net.supergamer.growitems.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.advancement.AdvancementProvider;
import net.minecraft.data.advancement.vanilla.VanillaStoryTabAdvancementGenerator;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.supergamer.growitems.GrowItems;
import net.supergamer.growitems.advancements.critera.ModCriteria;
import net.supergamer.growitems.advancements.critera.custom.ItemGrownCriterion;
import net.supergamer.growitems.block.ModBlocks;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry root = Advancement.Builder.create()
                .display(
                        ModBlocks.ITEM_GROWER.asItem().getDefaultStack(),
                        Text.literal("GrowItems"),
                        Text.literal("Begin your item cultivation journey"),
                        Identifier.of("minecraft", "gui/advancements/backgrounds/stone"),
                        AdvancementFrame.TASK,
                        true,  // show toast
                        true,  // announce to chat
                        false  // hidden
                )
                .criterion(
                        "has_item_grower",
                        InventoryChangedCriterion.Conditions.items(ModBlocks.ITEM_GROWER.asItem())
                )
                .build(consumer, Identifier.of(GrowItems.MOD_ID, "root").toString());

        AdvancementEntry itemGrown = Advancement.Builder.create()
                .parent(root)
                .display(
                        ModBlocks.ITEM_GROWER.asItem().getDefaultStack(),
                        Text.literal("Multiply and Flourish"),
                        Text.literal("Grow an item using the Item Grower"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion(
                        "grow_item",
                        ModCriteria.ITEM_GROWN.create(new ItemGrownCriterion.Conditions(Optional.empty()))
                )
                .build(consumer, Identifier.of(GrowItems.MOD_ID, "grow_item").toString());
    }
}
