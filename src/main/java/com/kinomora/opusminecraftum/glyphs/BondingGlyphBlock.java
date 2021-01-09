package com.kinomora.opusminecraftum.glyphs;

import com.kinomora.opusminecraftum.RegistryHandler;
import net.minecraft.block.BreakableBlock;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public class BondingGlyphBlock extends BreakableBlock{

    public static final EnumProperty<Connected> CONNECTED = EnumProperty.create("connected", Connected.class);

    public BondingGlyphBlock(Properties properties) {
        super(properties);
    }

    public enum Connected implements IStringSerializable {
        TRUE, FALSE;

        @Override
        public String getString() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}


