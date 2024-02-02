package org.icecream.icecream.popsicleclient.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.icecream.icecream.popsicleclient.popsicleclient;
import org.icecream.icecream.popsicleclient.config.Seats;

public interface RidableEntity {
    default Seats getSeats() {
        return popsicleclient.instance().getConfig().seats;
    }
}
