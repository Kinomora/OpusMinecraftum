package com.kinomora.opusminecraftum;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BreakableBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockReader;

import java.util.Locale;

public class CalcifyGlyphBlock extends BreakableBlock {

    public static final EnumProperty<Fullness> FULLNESS = EnumProperty.create("fullness", Fullness.class);

    //FULLNESS = EnumProperty.create("fullness", CalcifyGlyphBlock.Fullness.class);

    public CalcifyGlyphBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CalcifyGlyphTile();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.IGNORE;
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
