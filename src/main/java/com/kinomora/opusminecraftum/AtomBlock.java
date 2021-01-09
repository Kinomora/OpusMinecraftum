package com.kinomora.opusminecraftum;

import com.kinomora.opusminecraftum.glyphs.CalcificationGlyphTile;
import net.minecraft.block.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OpusMinecraftum.ID)
public class AtomBlock extends AbstractAtomBlock {

    protected AtomBlock(Properties properties) {
        super(properties);
    }

    //For pushing into glyphs/TE's or the solver
    @SubscribeEvent
    public static void onPistonPushPre(PistonEvent.Pre event) {
        World world = (World) event.getWorld();
        BlockPos offset = event.getFaceOffsetPos();
        TileEntity glyph = world.getTileEntity(offset.offset(event.getDirection()));

        if(world.getBlockState(offset.offset(event.getDirection())).getBlock() == RegistryHandler.CALCIFY_GLYPH_BLOCK){
            System.out.println("Calcifier state: " + ((CalcificationGlyphTile) glyph).hasAtom());
        }

        if (event.getPistonMoveType().isExtend &&
                world.getBlockState(offset).getBlock() == RegistryHandler.ATOM_BLOCK &&
                world.getBlockState(offset.offset(event.getDirection())).getBlock() == RegistryHandler.CALCIFY_GLYPH_BLOCK &&
                !((CalcificationGlyphTile) glyph).hasAtom()) {
            event.setCanceled(true);
            world.setBlockState(event.getFaceOffsetPos(), Blocks.AIR.getDefaultState(), 3);
            ((CalcificationGlyphTile) glyph).setHasAtom(true);
        }
    }


    //For pulling out of glyphs/TE's or the solver
    @SubscribeEvent
    public static void onPistonPushPost(PistonEvent.Post event) {
        World world = (World) event.getWorld();
        BlockPos offset = event.getFaceOffsetPos();
        TileEntity glyph = world.getTileEntity(offset.offset(event.getDirection()));
        BlockState blockstate = event.getState();

        if(!event.getPistonMoveType().isExtend &&
                world.getBlockState(offset).getBlock() == RegistryHandler.CALCIFY_GLYPH_BLOCK &&
                ((CalcificationGlyphTile) glyph).hasAtom() &&
                blockstate.getBlock() == Blocks.STICKY_PISTON){
            world.setBlockState(event.getFaceOffsetPos(), RegistryHandler.ATOM_BLOCK.getDefaultState().with(ELEMENT,Element.SALT), 3);
            ((CalcificationGlyphTile) glyph).setHasAtom(false);
        }
    }
}
