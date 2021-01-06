package com.kinomora.opusminecraftum;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = OpusMinecraftum.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

    //Atoms
    @ObjectHolder(OpusMinecraftum.ID + ":atom_block")
    public static Block ATOM_BLOCK;
    @ObjectHolder(OpusMinecraftum.ID + ":calcify_glyph_block")
    public static Block CALCIFY_GLYPH_BLOCK;

    //Tile Entities
    @ObjectHolder(OpusMinecraftum.ID + ":calcify_glyph_tile")
    public static TileEntityType<CalcifyGlyphTile> CALCIFY_GLYPH_TILE;
    /*@ObjectHolder(OpusMinecraftum.ID + ":duplication_glyph")
    public static TileEntity DUPLICATION_GLYPH;
    @ObjectHolder(OpusMinecraftum.ID + ":animismus_glyph")
    public static TileEntity ANIMISMUS_GLYPH;
    @ObjectHolder(OpusMinecraftum.ID + ":projection_glyph")
    public static TileEntity PROJECTION_GLYPH;
    @ObjectHolder(OpusMinecraftum.ID + ":purification_glyph")
    public static TileEntity PURIFICATION_GLYPH;
    @ObjectHolder(OpusMinecraftum.ID + ":triplex_glyph")
    public static TileEntity TRIPLEX_GLYPH;*/


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new AtomBlock(AbstractBlock.Properties.create(Material.GLASS).sound(SoundType.SHROOMLIGHT)).setRegistryName(OpusMinecraftum.createRes("atom_block")));
        event.getRegistry().register(new CalcifyGlyphBlock(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.ANVIL)).setRegistryName(OpusMinecraftum.createRes("calcify_glyph_block")));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().register(new BlockItem(CALCIFY_GLYPH_BLOCK, new Item.Properties()).setRegistryName(OpusMinecraftum.createRes("calcify_glyph_block")));
    }

    @SubscribeEvent
    public static void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event){
        event.getRegistry().register(TileEntityType.Builder.create(CalcifyGlyphTile::new, ATOM_BLOCK).build(null).setRegistryName(OpusMinecraftum.createRes("calcify_glyph_tile")));
    }
}
