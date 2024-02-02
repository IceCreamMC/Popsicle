package org.icecream.icecream.popsicleclient.mixin.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.icecream.icecream.popsicleclient.entity.RidableEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StrayEntity.class)
public abstract class MixinStray extends MobEntity implements RidableEntity {
    public MixinStray(EntityType<? extends StrayEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Vec3d getPassengerRidingPos(Entity passenger) {
        return super.getPassengerRidingPos(passenger).add(getSeats().stray.x, getSeats().stray.y, getSeats().stray.z);
    }
}
