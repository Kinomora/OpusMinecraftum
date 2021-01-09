package com.kinomora.opusminecraftum;

import com.kinomora.opusminecraftum.glyphs.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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

    //Glyphs
    @ObjectHolder(OpusMinecraftum.ID + ":animismus_glyph_block")
    public static Block ANIMISMUS_GLYPH_BLOCK;
    @ObjectHolder(OpusMinecraftum.ID + ":bonding_glyph_block")
    public static Block BONDING_GLYPH_BLOCK;
    @ObjectHolder(OpusMinecraftum.ID + ":calcify_glyph_block")
    public static Block CALCIFY_GLYPH_BLOCK;
    @ObjectHolder(OpusMinecraftum.ID + ":duplication_glyph_block")
    public static Block DUPLICATION_GLYPH_BLOCK;
    @ObjectHolder(OpusMinecraftum.ID + ":projection_purification_glyph_block")
    public static Block PROJECTION_PURIFICATION_GLYPH_BLOCK;
    @ObjectHolder(OpusMinecraftum.ID + ":triplex_glyph_block")
    public static Block TRIPLEX_GLYPH_BLOCK;

    //Tile Entities
    /*@ObjectHolder(OpusMinecraftum.ID + ":animismus_glyph_tile")
    public static TileEntity ANIMISMUS_GLYPH_TILE;
    @ObjectHolder(OpusMinecraftum.ID + ":bonding_glyph_tile")
    public static TileEntity BONDING_GLYPH_TILE;*/
    @ObjectHolder(OpusMinecraftum.ID + ":calcify_glyph_tile")
    public static TileEntityType<CalcificationGlyphTile> CALCIFY_GLYPH_TILE;
    /*@ObjectHolder(OpusMinecraftum.ID + ":duplication_glyph_tile")
    public static TileEntity DUPLICATION_GLYPH_TILE;
    @ObjectHolder(OpusMinecraftum.ID + ":projection_purification_glyph_tile")
    public static TileEntity PROJECTION_GLYPH_TILE;
    @ObjectHolder(OpusMinecraftum.ID + ":triplex_glyph_tile")
    public static TileEntity TRIPLEX_GLYPH_TILE;*/


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new AtomBlock(AbstractBlock.Properties.create(Material.GLASS).sound(SoundType.SHROOMLIGHT)).setRegistryName(OpusMinecraftum.createRes("atom_block")));

        event.getRegistry().register(new AnimismusGlyphBlock(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.ANVIL)).setRegistryName(OpusMinecraftum.createRes("animismus_glyph_block")));
        event.getRegistry().register(new BondingGlyphBlock(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.ANVIL)).setRegistryName(OpusMinecraftum.createRes("bonding_glyph_block")));
        event.getRegistry().register(new CalcificationGlyphBlock(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.ANVIL)).setRegistryName(OpusMinecraftum.createRes("calcify_glyph_block")));
        event.getRegistry().register(new DuplicationGlyphBlock(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.ANVIL)).setRegistryName(OpusMinecraftum.createRes("duplication_glyph_block")));
        event.getRegistry().register(new ProjectionPurificationGlyphBlock(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.ANVIL)).setRegistryName(OpusMinecraftum.createRes("projection_purification_glyph_block")));
        event.getRegistry().register(new TriplexBondingGlyphBlock(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.ANVIL)).setRegistryName(OpusMinecraftum.createRes("triplexbonding_glyph_block")));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().register(new BlockItem(ANIMISMUS_GLYPH_BLOCK, new Item.Properties()).setRegistryName(OpusMinecraftum.createRes("animismus_glyph_block")));
        event.getRegistry().register(new BlockItem(BONDING_GLYPH_BLOCK, new Item.Properties()).setRegistryName(OpusMinecraftum.createRes("bonding_glyph_block")));
        event.getRegistry().register(new BlockItem(CALCIFY_GLYPH_BLOCK, new Item.Properties()).setRegistryName(OpusMinecraftum.createRes("calcify_glyph_block")));
        event.getRegistry().register(new BlockItem(DUPLICATION_GLYPH_BLOCK, new Item.Properties()).setRegistryName(OpusMinecraftum.createRes("duplication_glyph_block")));
        event.getRegistry().register(new BlockItem(PROJECTION_PURIFICATION_GLYPH_BLOCK, new Item.Properties()).setRegistryName(OpusMinecraftum.createRes("projection_purification_glyph_block")));
        event.getRegistry().register(new BlockItem(TRIPLEX_GLYPH_BLOCK, new Item.Properties()).setRegistryName(OpusMinecraftum.createRes("triplexbonding_glyph_block")));
    }

    @SubscribeEvent
    public static void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event){
        event.getRegistry().register(TileEntityType.Builder.create(CalcificationGlyphTile::new, ATOM_BLOCK).build(null).setRegistryName(OpusMinecraftum.createRes("calcify_glyph_tile")));
    }
}
