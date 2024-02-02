package org.icecream.icecream.popsicleclient.mixin;

import net.minecraft.SharedConstants;
import net.minecraft.popsicleclient.Minecraftpopsicleclient;
import net.minecraft.popsicleclient.network.popsicleclientPlayNetworkHandler;
import net.minecraft.popsicleclient.network.ServerInfo;
import net.minecraft.popsicleclient.resource.language.I18n;
import net.minecraft.server.integrated.IntegratedServer;
import org.icecream.icecream.popsicleclient.popsicleclient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraftpopsicleclient.class)
public class MixinMinecraftpopsicleclient {
    @Shadow
    private IntegratedServer server;
    @Shadow
    public ServerInfo getCurrentServerEntry() {
        return null;
    }

    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    private void getWindowTitle(CallbackInfoReturnable<String> cir) {
        popsicleclient icecream = popsicleclient.instance();
        if (icecream == null || !icecream.getConfig().useWindowTitle) {
            return;
        }
        Minecraftpopsicleclient popsicleclient = Minecraftpopsicleclient.getInstance();
        StringBuilder sb = new StringBuilder(I18n.translate("popsicleclient %s", SharedConstants.getGameVersion().getName()));
        popsicleclientPlayNetworkHandler network = popsicleclient.getNetworkHandler();
        if (network != null && network.getConnection().isOpen()) {
            sb.append(" - ");
            String username = popsicleclient.getSession().getUsername();
            ServerInfo serverInfo = this.getCurrentServerEntry();
            if (this.server != null && !this.server.isRemote()) {
                sb.append(I18n.translate("popsicleclient.title.singleplayer", username));
            } else if (serverInfo != null && serverInfo.isRealm()) {
                sb.append(I18n.translate("popsicleclient.title.multiplayer.realms", username));
            } else if (this.server == null && (serverInfo == null || !serverInfo.isLocal())) {
                if (serverInfo == null) {
                    sb.append(I18n.translate("popsicleclient.title.multiplayer.unknown", username));
                } else {
                    sb.append(I18n.translate("popsicleclient.title.multiplayer.server", username, serverInfo.name));
                }
            } else {
                sb.append(I18n.translate("popsicleclient.title.multiplayer.lan", username));
            }
        }
        cir.setReturnValue(sb.toString());
    }
}
