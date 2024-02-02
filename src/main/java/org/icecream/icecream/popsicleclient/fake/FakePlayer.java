package org.icecream.icecream.popsicleclient.fake;

import net.minecraft.popsicleclient.network.AbstractpopsicleclientPlayerEntity;
import net.minecraft.popsicleclient.network.popsicleclientPlayerEntity;
import net.minecraft.popsicleclient.world.popsicleclientWorld;

public class FakePlayer extends AbstractpopsicleclientPlayerEntity {
    public FakePlayer(popsicleclientWorld world, popsicleclientPlayerEntity player) {
        super(world, player.getGameProfile());
    }

    @Override
    public boolean shouldRenderName() {
        return false;
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return false;
    }
}
