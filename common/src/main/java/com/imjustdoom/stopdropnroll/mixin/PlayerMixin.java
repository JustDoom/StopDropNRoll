package com.imjustdoom.stopdropnroll.mixin;

import com.imjustdoom.stopdropnroll.StopDropNRollPlayer;
import com.imjustdoom.stopdropnroll.config.Config;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends Entity implements StopDropNRollPlayer {

    @Shadow public abstract void setRemainingFireTicks(int ticks);

    @Unique
    private boolean isLastCrouching;

    public PlayerMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci) {

        if (level().isClientSide) return;

        if (isCrouching() && !isLastCrouching() && isOnFire() && Math.random() < Config.CHANCE) {
            setRemainingFireTicks((int) (getRemainingFireTicks() * (1f - Config.FIRE_TICK_REMOVE_PERCENTAGE)));
        }

        this.isLastCrouching = isCrouching();
    }

    public boolean isLastCrouching() {
        return this.isLastCrouching;
    }
}
