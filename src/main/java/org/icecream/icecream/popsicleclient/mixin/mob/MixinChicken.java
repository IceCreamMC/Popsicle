package org.icecream.icecream.popsicleclient.mixin.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.icecream.icecream.popsicleclient.entity.RidableEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChickenEntity.class)
public abstract class MixinChicken extends MobEntity implements RidableEntity {
    public MixinChicken(EntityType<? extends ChickenEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Vec3d getPassengerRidingPos(Entity passenger) {
        return super.getPassengerRidingPos(passenger).add(getSeats().chicken.x, getSeats().chicken.y, getSeats().chicken.z);
    }
}
