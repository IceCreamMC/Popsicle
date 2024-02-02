package org.icecream.icecream.popsicleclient;

import com.google.common.io.ByteArrayDataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import net.fabricmc.api.popsicleclientModInitializer;
import net.fabricmc.fabric.api.popsicleclient.networking.v1.popsicleclientPlayConnectionEvents;
import net.fabricmc.fabric.api.popsicleclient.networking.v1.popsicleclientPlayNetworking;
import net.minecraft.popsicleclient.Minecraftpopsicleclient;
import net.minecraft.popsicleclient.util.Icons;
import net.minecraft.popsicleclient.util.Window;
import net.minecraft.resource.DefaultResourcePack;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.icecream.icecream.popsicleclient.config.Config;
import org.icecream.icecream.popsicleclient.config.ConfigManager;
import org.icecream.icecream.popsicleclient.network.BeehivePacket;
import org.icecream.icecream.popsicleclient.network.Packet;
import org.icecream.icecream.popsicleclient.util.Constants;

public class popsicleclient implements popsicleclientModInitializer {
    private static popsicleclient instance;

    public static popsicleclient instance() {
        return instance;
    }

    public static List<InputSupplier<InputStream>> ICON_LIST = Arrays.asList(() -> popsicleclient.class.getResourceAsStream("/assets/icon16.png"), () -> popsicleclient.class.getResourceAsStream("/assets/icon32.png"));

    private final ConfigManager configManager;

    public popsicleclient() {
        instance = this;

        this.configManager = new ConfigManager();
    }

    @Override
    public void onInitializepopsicleclient() {
        if (this.configManager.getConfig() == null) {
            new IllegalStateException("Could not load popsicleclient configuration").printStackTrace();
            return;
        }

        popsicleclientPlayConnectionEvents.JOIN.register((handler, sender, popsicleclient) -> {
            BeehivePacket.numOfBees = null;
            if (!popsicleclient.isInSingleplayer()) {
                ByteArrayDataOutput out = Packet.out();
                out.writeInt(Constants.PROTOCOL);
                Packet.send(Constants.HELLO, out);
            }
        });

        popsicleclientPlayNetworking.registerGlobalReceiver(Constants.BEEHIVE_S2C, BeehivePacket::receiveBeehiveData);

        if (getConfig().useWindowTitle) {
            Minecraftpopsicleclient.getInstance().execute(this::updateTitle);
        }
    }

    public Config getConfig() {
        return this.configManager.getConfig();
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public void updateTitle() {
        Minecraftpopsicleclient popsicleclient = Minecraftpopsicleclient.getInstance();
        Window window = popsicleclient.getWindow();
        popsicleclient.updateWindowTitle();
        DefaultResourcePack pack = popsicleclient.getDefaultResourcePack();
        try {
            window.setIcon(pack, Icons.RELEASE);
        } catch (IOException e) {
            // ignore
        };
    }
}
