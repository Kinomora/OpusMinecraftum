package com.kinomora.opusminecraftum.mixin;

import net.minecraft.block.PistonBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonBlock.class)
public class PistonBlockMixin {

    @Inject(method = "doMove", at = @At(value = "RETURN"))
    private void printStuff(World worldIn, BlockPos pos, Direction directionIn, boolean extending, CallbackInfoReturnable<Boolean> cir) {

    }
}
