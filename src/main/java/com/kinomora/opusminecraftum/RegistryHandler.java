package com.kinomora.opusminecraftum;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = OpusMinecraftum.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

    //Atoms
    /*@ObjectHolder(OpusMinecraftum.ID + ":atom_block")
    public static Block ATOM_BLOCK;*/
    @ObjectHolder(OpusMinecraftum.ID + ":air_atom")
    public static Block AIR_ATOM;
    /*@ObjectHolder(OpusMinecraftum.ID + ":earth_atom")
    public static Block EARTH_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":fire_atom")
    public static Block FIRE_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":water_atom")
    public static Block WATER_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":vitae_atom")
    public static Block VITAE_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":mors_atom")
    public static Block MORS_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":salt_atom")
    public static Block SALT_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":quicksilver_atom")
    public static Block QUICKSILVER_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":lead_atom")
    public static Block LEAD_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":tin_atom")
    public static Block TIN_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":iron_atom")
    public static Block IRON_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":copper_atom")
    public static Block COPPER_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":silver_atom")
    public static Block SILVER_ATOM;
    @ObjectHolder(OpusMinecraftum.ID + ":gold_atom")
    public static Block GOLD_ATOM;*/

    //Tile Entities
    @ObjectHolder(OpusMinecraftum.ID + ":calcify_glyph")
    public static TileEntity CALCIFY_GLYPH;
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
        //event.getRegistry().register(getAtomProps("atom_block"));
        event.getRegistry().register(getAtomProps("air_atom"));/*
        event.getRegistry().register(getAtomProps("earth_atom"));
        event.getRegistry().register(getAtomProps("fire_atom"));
        event.getRegistry().register(getAtomProps("water_atom"));
        event.getRegistry().register(getAtomProps("vitae_atom"));
        event.getRegistry().register(getAtomProps("mors_atom"));
        event.getRegistry().register(getAtomProps("salt_atom"));
        event.getRegistry().register(getAtomProps("quicksilver_atom"));
        event.getRegistry().register(getAtomProps("lead_atom"));
        event.getRegistry().register(getAtomProps("tin_atom"));
        event.getRegistry().register(getAtomProps("iron_atom"));
        event.getRegistry().register(getAtomProps("copper_atom"));
        event.getRegistry().register(getAtomProps("silver_atom"));
        event.getRegistry().register(getAtomProps("gold_atom"));*/
    }

    private static Block getAtomProps(String name){
        return new AtomBlock(AbstractBlock.Properties.create(Material.GLASS).sound(SoundType.SHROOMLIGHT)).setRegistryName(OpusMinecraftum.createRes(name));
    }

    private static TileEntity getGlyphProps(String name){
        return null;
    }
}
