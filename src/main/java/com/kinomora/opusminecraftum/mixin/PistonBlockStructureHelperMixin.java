package com.kinomora.opusminecraftum.mixin;

import com.kinomora.opusminecraftum.AtomBlock;
import com.kinomora.opusminecraftum.RegistryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonBlockStructureHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PistonBlockStructureHelper.class)
public abstract class PistonBlockStructureHelperMixin {

    @Shadow
    @Final
    private Direction moveDirection;
    @Inject(method = "addBlockLine",
            at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 0),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onPullBlockPre(BlockPos origin, Direction facingIn, CallbackInfoReturnable<Boolean> cir, BlockState pullingState, int var1) {
        setStickType(AtomBlock.PistonStickType.PULL);
        setStickDirection(null, null, this.moveDirection.getOpposite());
    }

    @Inject(method = "addBlockLine",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onPullBlock(BlockPos origin, Direction facingIn, CallbackInfoReturnable<Boolean> cir, BlockState pullingState, int var1, BlockState pulledState) {
        setStickDirection(pullingState, pulledState, this.moveDirection.getOpposite());
    }

    @Inject(method = "canMove",
            at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;get(I)Ljava/lang/Object;", ordinal = 0),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onBranchBlockPre(CallbackInfoReturnable<Boolean> cir) {
        setStickType(null);
    }

    @Inject(method = "addBranchingBlocks",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onBranchBlock(BlockPos fromPos, CallbackInfoReturnable<Boolean> cir, BlockState source, Direction[] var0, int var1, int var2, Direction dir, BlockPos var3, BlockState branch) {
        setStickType(AtomBlock.PistonStickType.BRANCH);
        setStickDirection(source, branch, dir);
    }

    private static void setStickDirection(BlockState from, BlockState to, Direction direction) {
        if (isAtomOrStickyOrNull(from) && isAtomOrStickyOrNull(to)) {
            AtomBlock.stickDirection = direction;
        } else {
            AtomBlock.stickDirection = null;
        }
    }

    private static boolean isAtomOrStickyOrNull(BlockState state) {
        return state == null || state.isIn(RegistryHandler.ATOM_BLOCK) || state.isStickyBlock();
    }

    private static void setStickType(AtomBlock.PistonStickType type) {
        AtomBlock.stickType = type;
    }


}
