package com.kinomora.opusminecraftum.mixin;

import com.kinomora.opusminecraftum.AtomBlock;
import com.kinomora.opusminecraftum.RegistryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonBlockStructureHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PistonBlockStructureHelper.class)
public abstract class PistonBlockStructureHelperMixin {

    @Inject(method = "addBranchingBlocks",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true
    )
    private void stickBranchedBlocks(BlockPos basePos, CallbackInfoReturnable<Boolean> cir, BlockState pushState, Direction var3[], int var4, int var5, Direction dir, BlockPos offPos, BlockState branchState) {
        System.out.println("Branching to " + branchState);
        if (isAtom(pushState) && isAtom(branchState)) {
            AtomBlock.STICK_DIRECTION = dir;
        } else {
            AtomBlock.STICK_DIRECTION = null;
        }
    }

    @Inject(method = "addBlockLine",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true
    )
    private void stickForwardBlocks(BlockPos origin, Direction facingIn, CallbackInfoReturnable<Boolean> cir, BlockState pushState, int unused, BlockState forwardState) {
        if (isAtom(pushState) && isAtom(forwardState)) {
            AtomBlock.STICK_DIRECTION = facingIn;
        } else {
            AtomBlock.STICK_DIRECTION = null;
        }
    }

    // TODO Replace this with a call out to a helper class, hopefully
    private static boolean areAtomsBonded(BlockState pushedBlock, BlockState branchBlock, Direction dir) {
        //System.out.println("Blocks Bonded from " + pushedBlock + " to " + branchBlock + " in dir " + dir);
        //System.out.println(branchBlock.isIn(net.minecraft.block.Blocks.GLASS));
        // TODO actual logic lmao
        return branchBlock.isIn(RegistryHandler.ATOM_BLOCK);
    }

    private static boolean isAtom(BlockState state) {
        return state.isIn(RegistryHandler.ATOM_BLOCK);
    }


}
