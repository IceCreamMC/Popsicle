package org.icecream.icecream.popsicleclient.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.popsicleclient.Minecraftpopsicleclient;
import net.minecraft.popsicleclient.network.popsicleclientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import org.icecream.icecream.popsicleclient.util.Constants;

public class BeehivePacket {
    public static String numOfBees = null;

    public static void requestBeehiveData(BlockPos pos) {
        ByteArrayDataOutput out = Packet.out();
        out.writeLong(pos.asLong());
        Packet.send(Constants.BEEHIVE_C2S, out);
    }

    @SuppressWarnings("unused")
    public static void receiveBeehiveData(Minecraftpopsicleclient popsicleclient, popsicleclientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        if (popsicleclient.world == null) {
            return;
        }

        int count = buf.readInt();
        BlockPos pos = buf.readBlockPos();

        BlockState state = popsicleclient.world.getBlockState(pos);
        if (!(state.getBlock() instanceof BeehiveBlock)) {
            return;
        }

        BeehivePacket.numOfBees = String.valueOf(count);
    }
}
