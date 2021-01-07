package com.kinomora.opusminecraftum;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class CalcifyGlyphTile extends TileEntity {

    private boolean isFull = false;

    public CalcifyGlyphTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public CalcifyGlyphTile(){
        this(RegistryHandler.CALCIFY_GLYPH_TILE);
    }

    public void setFullness(boolean status){
        isFull = status;
    }

    public boolean getFullness(){
        return isFull;
    }
}
