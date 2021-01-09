package com.kinomora.opusminecraftum.glyphs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BreakableBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Locale;

public class CalcificationGlyphBlock extends BreakableBlock {

    public static final EnumProperty<Fullness> FULLNESS = EnumProperty.create("fullness", Fullness.class);

    public CalcificationGlyphBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tile = worldIn.getTileEntity(pos);

        if (!worldIn.isRemote && tile instanceof CalcificationGlyphTile) {
            System.out.println("Has Atom: " + ((CalcificationGlyphTile)tile).hasAtom());
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CalcificationGlyphTile();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.PUSH_ONLY;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FULLNESS);
    }

    public enum Fullness implements IStringSerializable {
        EMPTY, FULL;

        @Override
        public String getString() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}
