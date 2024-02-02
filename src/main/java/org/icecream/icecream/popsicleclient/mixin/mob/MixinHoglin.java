package org.icecream.icecream.popsicleclient.mixin.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.icecream.icecream.popsicleclient.entity.RidableEntity;
import org.icecream.icecream.popsicleclient.mixin.accessor.AccessHoglin;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HoglinEntity.class)
public abstract class MixinHoglin extends MobEntity implements RidableEntity {
    public MixinHoglin(EntityType<? extends HoglinEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Vec3d getPassengerRidingPos(Entity passenger) {
        return super.getPassengerRidingPos(passenger).add(getSeats().hoglin.x, getSeats().hoglin.y, getSeats().hoglin.z);
    }

    @Override
    public boolean isAiDisabled() {
        // silly hack to stop hoglin from shaking on preview screen
        return ((AccessHoglin) this).getTimeInOverworld() < 0 || super.isAiDisabled();
    }
}
