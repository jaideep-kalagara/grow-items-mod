package net.supergamer.growitems.screen.custom;

import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.supergamer.growitems.GrowItems;

public class ItemGrowerScreen extends HandledScreen<ItemGrowerScreenHandler> {
    public static final Identifier GUI_TEXTURE = Identifier.of(GrowItems.MOD_ID, "textures/gui/item_grower.png");
    public static final Identifier ARROW_TEXTURE = Identifier.of(GrowItems.MOD_ID, "textures/gui/growth_progress.png");

    public ItemGrowerScreen(ItemGrowerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 166;
        this.backgroundWidth = 201;
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {

        int progress = handler.getProgress();
        int maxProgress = handler.getMaxProgress();


        this.drawMouseoverTooltip(context, mouseX, mouseY);
        context.drawTexture(
                RenderPipelines.GUI_TEXTURED,
                GUI_TEXTURE,
                x,
                y,
                0,
                0,
                backgroundWidth,
                backgroundHeight,
                256,
                256);

        int arrowWidth = (int)((progress / (float)maxProgress) * 24); // 0 to 24

        if (arrowWidth > 0) {
            context.drawTexture(
                    RenderPipelines.GUI_TEXTURED,
                    ARROW_TEXTURE,
                    x + 80, y + 35,
                    0, 0,
                    arrowWidth, 16,
                    24, 16
            );
        }


    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);
        int progress = handler.getProgress();
        int maxProgress = handler.getMaxProgress();

        if (progress > 0) {
            context.drawText(this.textRenderer, progress + "/" + maxProgress, x + 8, y + 16, Colors.GRAY, false);
        }
    }
}