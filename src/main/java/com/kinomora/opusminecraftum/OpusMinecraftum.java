package com.kinomora.opusminecraftum;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OpusMinecraftum.ID)
public class OpusMinecraftum
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String ID = "opusminecraftum";

    public static ResourceLocation createRes(String name) {
        return new ResourceLocation(ID, name);
    }

    public OpusMinecraftum() {

    }

    private void clientSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(RegistryHandler.ATOM_BLOCK, RenderType.getTranslucent());
    }

    private void setup(FMLCommonSetupEvent event) {
    }
}
