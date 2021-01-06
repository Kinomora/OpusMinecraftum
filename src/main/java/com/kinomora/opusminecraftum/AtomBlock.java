package com.kinomora.opusminecraftum;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OpusMinecraftum.ID)
public class AtomBlock extends AbstractAtomBlock {

    protected AtomBlock(Properties properties) {
        super(properties);
    }

    //For pushing into glyphs/TE's or the solver
    @SubscribeEvent
    public static void onPistonPushPre(PistonEvent.Pre event){
        event.setCanceled(true);
        System.out.println("owo");
        event.getWorld().setBlockState(event.getFaceOffsetPos(), Blocks.AIR.getDefaultState(), 3);
        event.setCanceled(false); //This is EXTREMELY cursed
    }



    //For pulling out of glyphs/TE's or the solver
    @SubscribeEvent
    public static void onPistonPushPost(PistonEvent.Post event){
        System.out.println("uwu");
    }
}
