package net.supergamer.growitems.item.custom.component;

import com.mojang.serialization.Codec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import java.util.function.Consumer;

public class RichSoilUpgradeComponent implements TooltipAppender {
    public static final Codec<RichSoilUpgradeComponent> CODEC = Codec.unit(RichSoilUpgradeComponent::new);

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> consumer, TooltipType type, ComponentsAccess components) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.isShiftPressed() || mc.options.advancedItemTooltips) {
            consumer.accept(Text.translatable("tooltip.growitems.rich_soil_upgrade.detail"));
        } else {
            consumer.accept(Text.translatable("tooltip.growitems.rich_soil_upgrade"));
            consumer.accept(Text.translatable("tooltip.growitems.hold_shift"));
        }
    }

}
