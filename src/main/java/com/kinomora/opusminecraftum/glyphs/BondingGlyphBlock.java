package com.kinomora.opusminecraftum.glyphs;

import com.kinomora.opusminecraftum.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BreakableBlock;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public class BondingGlyphBlock extends BreakableBlock{

    public static final EnumProperty<Connected> CONNECTED = EnumProperty.create("connected", Connected.class);

    public BondingGlyphBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(CONNECTED);
    }

    public enum Connected implements IStringSerializable {
        FALSE, TRUE;

        @Override
        public String getString() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}


