package org.icecream.icecream.popsicleclient.gui.screen;

import net.minecraft.popsicleclient.gui.screen.Screen;
import net.minecraft.popsicleclient.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.icecream.icecream.popsicleclient.popsicleclient;
import org.icecream.icecream.popsicleclient.config.Config;
import org.icecream.icecream.popsicleclient.config.options.BooleanOption;
import org.icecream.icecream.popsicleclient.config.options.Button;
import org.icecream.icecream.popsicleclient.gui.screen.widget.BooleanButton;

import java.util.ArrayList;

public class OptionsScreen extends AbstractScreen {
    public final static MutableText MOBS_BTN = Text.translatable("popsicleclient.options.mobs");

    public OptionsScreen(Screen parent) {
        super(parent);
    }

    @Override
    public void init() {
        super.init();

        final Config config = popsicleclient.instance().getConfig();

        this.options = new ArrayList<>();
        this.options.add(new BooleanButton(this.centerX - 160, 50, 150, 20, new BooleanOption("bee-count-in-debug", () -> config.beeCountInDebug, (value) -> config.beeCountInDebug = value)));
        this.options.add(new BooleanButton(this.centerX + 10, 50, 150, 20, new BooleanOption("splash-screen", () -> config.useSplashScreen, (value) -> config.useSplashScreen = value)));
        this.options.add(new BooleanButton(this.centerX - 160, 80, 150, 20, new BooleanOption("window-title", () -> config.useWindowTitle, (value) -> {
            config.useWindowTitle = value;
            popsicleclient.instance().updateTitle();
        })));
        this.options.add(new Button(this.centerX + 10, 80, 150, 20, MOBS_BTN, button -> openScreen(new MobsScreen(this))));
        this.options.add(ButtonWidget.builder(ScreenTexts.DONE, (button) -> close()).dimensions(this.centerX - 100, 150, 200, 20).build());

        this.options.forEach(this::addDrawableChild);
    }
}
