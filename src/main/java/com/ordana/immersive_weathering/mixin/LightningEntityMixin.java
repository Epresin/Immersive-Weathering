package com.ordana.immersive_weathering.mixin;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public abstract class LightningEntityMixin extends Entity {

    public LightningEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }


    @Inject(method = "powerLightningRod", at = @At("HEAD"))
    private void powerLightningRod(CallbackInfo ci) {
        BlockPos blockPos = this.getAffectedBlockPos();

        BlockState blockState = this.world.getBlockState(blockPos);
        if (blockState.isIn(BlockTags.SAND)) {

            BlockPos downPos = blockPos.down();
            BlockPos northPos = blockPos.north();
            BlockPos southPos = blockPos.south();
            BlockPos eastPos = blockPos.east();
            BlockPos westPos = blockPos.west();
            BlockState downState = world.getBlockState(downPos);
            BlockState northState = world.getBlockState(northPos);
            BlockState southState = world.getBlockState(southPos);
            BlockState eastState = world.getBlockState(eastPos);
            BlockState westState = world.getBlockState(westPos);

            world.setBlockState(blockPos, Blocks.GLASS.getDefaultState());
            if (downState.isIn(BlockTags.SAND)) {
                world.setBlockState(downPos, Blocks.GLASS.getDefaultState());
            }
            if (world.random.nextFloat() < 0.5f && northState.isIn(BlockTags.SAND)) {
                world.setBlockState(northPos, Blocks.GLASS.getDefaultState());
            }
            if (world.random.nextFloat() < 0.5f && southState.isIn(BlockTags.SAND)) {
                world.setBlockState(southPos, Blocks.GLASS.getDefaultState());
            }
            if (world.random.nextFloat() < 0.5f && eastState.isIn(BlockTags.SAND)) {
                world.setBlockState(eastPos, Blocks.GLASS.getDefaultState());
            }
            if (world.random.nextFloat() < 0.5f && westState.isIn(BlockTags.SAND)) {
                world.setBlockState(westPos, Blocks.GLASS.getDefaultState());
            }
        }
    }

    public BlockPos getAffectedBlockPos() {
        Vec3d vec3d = this.getPos();
        return new BlockPos(vec3d.x, vec3d.y - 1.0E-6D, vec3d.z);
    }
}
