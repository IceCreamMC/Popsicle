package org.icecream.icecream.popsicleclient.gui;

import net.minecraft.popsicleclient.resource.metadata.TextureResourceMetadata;
import net.minecraft.popsicleclient.texture.NativeImage;
import net.minecraft.popsicleclient.texture.ResourceTexture;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.icecream.icecream.popsicleclient.popsicleclient;

import java.io.IOException;
import java.io.InputStream;

public class SplashTexture extends ResourceTexture {
    public static final Identifier SPLASH = new Identifier("popsicleclient", "textures/splash.png");

    public SplashTexture() {
        super(SPLASH);
    }

    @Override
    protected TextureData loadTextureData(ResourceManager resourceManager) {
        TextureData data;
        try (InputStream in = popsicleclient.class.getResourceAsStream("/assets/popsicleclient/textures/splash.png")) {
            data = new TextureData(new TextureResourceMetadata(true, true), NativeImage.read(in));
        } catch (IOException e) {
            return new TextureData(e);
        }
        return data;
    }
}
