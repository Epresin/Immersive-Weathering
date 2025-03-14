package com.ordana.immersive_weathering.registry.blocks.rustable;

import java.util.Random;

import com.ordana.immersive_weathering.registry.ModTags;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class RustableSlabBlock extends SlabBlock implements Rustable {
    private final Rustable.RustLevel rustLevel;

    public RustableSlabBlock(Rustable.RustLevel rustLevel, AbstractBlock.Settings settings) {
        super(settings);
        this.rustLevel = rustLevel;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random){
        if (world.getBlockState(pos).isIn(ModTags.CLEAN_IRON)) {
            for (Direction direction : Direction.values()) {
                var targetPos = pos.offset(direction);
                BlockState neighborState = world.getBlockState(targetPos);
                if (world.getBlockState(pos.offset(direction)).isOf(Blocks.AIR) || neighborState.getFluidState().getFluid() == Fluids.FLOWING_WATER || neighborState.getFluidState().getFluid() == Fluids.WATER) {
                    this.tickDegradation(state, world, pos, random);
                }
                if (world.getBlockState(pos.offset(direction)).isOf(Blocks.BUBBLE_COLUMN)) {
                    float f = 0.06f;
                    if (random.nextFloat() > 0.06f) {
                        this.tryDegrade(state, world, pos, random);
                    }
                }
            }
        }
        if (world.getBlockState(pos).isIn(ModTags.EXPOSED_IRON)) {
            for (Direction direction : Direction.values()) {
                var targetPos = pos.offset(direction);
                BlockState neighborState = world.getBlockState(targetPos);
                if (world.hasRain(pos.up()) || neighborState.getFluidState().getFluid() == Fluids.FLOWING_WATER || neighborState.getFluidState().getFluid() == Fluids.WATER) {
                    this.tickDegradation(state, world, pos, random);
                }
                if (world.getBlockState(pos.offset(direction)).isOf(Blocks.BUBBLE_COLUMN)) {
                    float f = 0.06f;
                    if (random.nextFloat() > 0.06f) {
                        this.tryDegrade(state, world, pos, random);
                    }
                }
                if (world.hasRain(pos.offset(direction)) && world.getBlockState(pos.up()).isIn(ModTags.WEATHERED_IRON)) {
                    if (BlockPos.streamOutwards(pos, 2, 2, 2)
                            .map(world::getBlockState)
                            .filter(b->b.isIn(ModTags.WEATHERED_IRON))
                            .toList().size() <= 9) {
                        float f = 0.06f;
                        if (random.nextFloat() > 0.06f) {
                            this.tryDegrade(state, world, pos, random);
                        }
                    }
                }
            }
        }
        if (world.getBlockState(pos).isIn(ModTags.WEATHERED_IRON)) {
            for (Direction direction : Direction.values()) {
                var targetPos = pos.offset(direction);
                BlockState neighborState = world.getBlockState(targetPos);
                if (neighborState.getFluidState().getFluid() == Fluids.WATER || neighborState.getFluidState().getFluid() == Fluids.FLOWING_WATER) {
                    this.tickDegradation(state, world, pos, random);
                }
                if (world.getBlockState(pos.offset(direction)).isOf(Blocks.BUBBLE_COLUMN)) {
                    float f = 0.07f;
                    if (random.nextFloat() > 0.07f) {
                        this.tryDegrade(state, world, pos, random);
                    }
                }
            }
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return Rustable.getIncreasedRustBlock(state.getBlock()).isPresent();
    }

    @Override
    public Rustable.RustLevel getDegradationLevel() {
        return this.rustLevel;
    }
}
