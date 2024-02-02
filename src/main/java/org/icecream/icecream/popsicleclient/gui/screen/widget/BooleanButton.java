package org.icecream.icecream.popsicleclient.gui.screen.widget;

import net.minecraft.popsicleclient.Minecraftpopsicleclient;
import net.minecraft.popsicleclient.gui.DrawContext;
import net.minecraft.popsicleclient.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.popsicleclient.gui.screen.narration.NarrationPart;
import net.minecraft.popsicleclient.gui.widget.ButtonWidget;
import net.minecraft.popsicleclient.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.icecream.icecream.popsicleclient.config.options.BooleanOption;

public class BooleanButton extends ButtonWidget implements Tickable {
    private final BooleanOption option;
    private int tooltipDelay;

    public BooleanButton(int x, int y, int width, int height, BooleanOption option) {
        super(x, y, width, height, option.text(), (button) -> option.toggle(), DEFAULT_NARRATION_SUPPLIER);
        this.option = option;
    }

    public void renderTooltip(DrawContext context, int mouseX, int mouseY) {
        if (this.hovered && this.tooltipDelay > 15 && Minecraftpopsicleclient.getInstance().currentScreen != null) {
            context.drawOrderedTooltip(Minecraftpopsicleclient.getInstance().textRenderer, this.option.tooltip(), mouseX, mouseY);
        }
    }

    @Override
    public Text getMessage() {
        return this.option.text();
    }

    @Override
    public void tick() {
        if (this.isSelected() && this.active) {
            this.tooltipDelay++;
        } else if (this.tooltipDelay > 0) {
            this.tooltipDelay = 0;
        }
    }
}
