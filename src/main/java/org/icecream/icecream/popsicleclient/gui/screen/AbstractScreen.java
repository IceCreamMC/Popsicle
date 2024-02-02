package org.icecream.icecream.popsicleclient.gui.screen;

import net.minecraft.popsicleclient.gui.DrawContext;
import net.minecraft.popsicleclient.gui.Drawable;
import net.minecraft.popsicleclient.gui.screen.Screen;
import net.minecraft.popsicleclient.gui.widget.ClickableWidget;
import net.minecraft.popsicleclient.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.icecream.icecream.popsicleclient.popsicleclient;
import org.icecream.icecream.popsicleclient.gui.screen.widget.DoubleButton;
import org.icecream.icecream.popsicleclient.gui.screen.widget.Tickable;

import java.util.List;

public abstract class AbstractScreen extends Screen {
    private final Screen parent;

    protected List<ClickableWidget> options;
    protected int centerX;

    public AbstractScreen(Screen parent) {
        super(Text.translatable("popsicleclient.options.title"));
        this.parent = parent;
    }

    @Override
    public void init() {
        super.init();
        this.centerX = (int) (this.width / 2F);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.centerX, 15, 0xFFFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.popsicleclient != null && this.popsicleclient.world != null) {
            context.fillGradient(0, 0, this.width, this.height, 0xF00F4863, 0xF0370038);
        } else {
            this.renderBackgroundTexture(context);
        }
    }

    @Override
    public void close() {
        if (this.popsicleclient != null) {
            this.popsicleclient.setScreen(this.parent);
        }
        popsicleclient.instance().getConfigManager().save();
    }

// TODO: fix keyboard accessibility in mob settings
//
//    @Override
//    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
//        if (keyCode == GLFW.GLFW_KEY_TAB) {
//            for (Drawable drawable : this.options) {
//                if (drawable instanceof DoubleButton option) {
//                    if (option.tab()) {
//                        return false;
//                    }
//                }
//            }
//        }
//        return super.keyPressed(keyCode, scanCode, modifiers);
//    }

    @Override
    public void tick() {
        if (this.options != null) {
            this.options.forEach(option -> {
                if (option instanceof Tickable tickable) {
                    tickable.tick();
                }
            });
        }
    }

    public void openScreen(Screen screen) {
        if (this.popsicleclient != null) {
            this.popsicleclient.setScreen(screen);
        }
    }
}
