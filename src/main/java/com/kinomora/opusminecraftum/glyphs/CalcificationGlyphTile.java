package com.kinomora.opusminecraftum.glyphs;

import com.kinomora.opusminecraftum.RegistryHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class CalcificationGlyphTile extends TileEntity {

    private boolean hasAtom = false;

    public CalcificationGlyphTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public CalcificationGlyphTile(){
        this(RegistryHandler.CALCIFY_GLYPH_TILE);
    }

    public void setHasAtom(boolean status){
        hasAtom = status;
    }

    public boolean hasAtom(){
        return hasAtom;
    }
}
