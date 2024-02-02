package org.icecream.icecream.popsicleclient.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.popsicleclient.networking.v1.popsicleclientPlayNetworking;
import net.minecraft.popsicleclient.Minecraftpopsicleclient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public abstract class Packet {
    public static void send(Identifier channel, ByteArrayDataOutput out) {
        popsicleclientPlayNetworking.send(channel, new PacketByteBuf(Unpooled.wrappedBuffer(out.toByteArray())));
    }

    @SuppressWarnings("UnstableApiUsage")
    public static ByteArrayDataOutput out() {
        return ByteStreams.newDataOutput();
    }
}
