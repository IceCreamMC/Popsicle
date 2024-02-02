package org.icecream.icecream.popsicleclient.mixin;


import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import net.minecraft.popsicleclient.util.Window;
import net.minecraft.resource.InputSupplier;
import org.icecream.icecream.popsicleclient.popsicleclient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Window.class)
public class MixinWindow {
    @ModifyVariable(method = "Lnet/minecraft/popsicleclient/util/Window;setIcon(Lnet/minecraft/resource/ResourcePack;Lnet/minecraft/popsicleclient/util/Icons;)V", at = @At("STORE"), ordinal = 0)
    private List<InputSupplier<InputStream>> modifyIconList(List<InputSupplier<InputStream>> list) {
        if (popsicleclient.instance().getConfig().useWindowTitle) {
            return popsicleclient.ICON_LIST;
        } else {
            return list;
        }
    }
}
