package com.kinomora.opusminecraftum;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BreakableBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = OpusMinecraftum.ID)
public class AtomBlock extends BreakableBlock {

    public static final EnumProperty<Element> ELEMENT = EnumProperty.create("element", Element.class);
    public static final BooleanProperty BOUND_DOWN = BooleanProperty.create("bound_down");
    public static final BooleanProperty BOUND_UP = BooleanProperty.create("bound_up");
    public static final BooleanProperty BOUND_NORTH = BooleanProperty.create("bound_north");
    public static final BooleanProperty BOUND_SOUTH = BooleanProperty.create("bound_south");
    public static final BooleanProperty BOUND_WEST = BooleanProperty.create("bound_west");
    public static final BooleanProperty BOUND_EAST = BooleanProperty.create("bound_east");

    public static final BooleanProperty[] BOUND_PROPS = new BooleanProperty[] {
            BOUND_DOWN, BOUND_UP, BOUND_NORTH, BOUND_SOUTH, BOUND_WEST, BOUND_EAST
    };

    public static Direction STICK_DIRECTION = null;

    protected AtomBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return true;
    }

    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.fullCube();
    }

    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ELEMENT, BOUND_DOWN, BOUND_UP, BOUND_NORTH, BOUND_SOUTH, BOUND_WEST, BOUND_EAST);
    }

    public enum Element implements IStringSerializable {
        AIR, FIRE, EARTH, WATER, SALT;

        @Override
        public String getString() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }

    //For pushing into glyphs/TE's or the solver
    @SubscribeEvent
    public static void onPistonPushPre(PistonEvent.Pre event) {
        World world = (World) event.getWorld();
        if (!world.isRemote) {
            Direction dir = event.getDirection();
            BlockPos pistonPos = event.getPos();
            BlockPos atomPos = event.getFaceOffsetPos();
            BlockPos glyphPos = event.getFaceOffsetPos().offset(dir);
            //System.out.println((event.getPistonMoveType().isExtend ? "Before Extending" : "Before Retracting") + " | Self: " + pistonPos + " ->" + world.getBlockState(pistonPos) + ", Target: " + atomPos + " -> " + world.getBlockState(atomPos));

            BlockState piston = world.getBlockState(pistonPos);
            BlockState atom = world.getBlockState(atomPos);
            BlockState glyph = world.getBlockState(glyphPos);
        }
        /*
        BlockPos offset = event.getFaceOffsetPos();
        TileEntity glyph = world.getTileEntity(offset.offset(event.getDirection()));

        if (event.getPistonMoveType().isExtend && world.getBlockState(offset.offset(event.getDirection())).getBlock() == RegistryHandler.CALCIFY_GLYPH_BLOCK) {
            System.out.println("Calcifier state: " + ((CalcifyGlyphTile) glyph).hasAtom());
        }

        if (event.getPistonMoveType().isExtend &&
                world.getBlockState(offset).getBlock() == RegistryHandler.ATOM_BLOCK &&
                world.getBlockState(offset.offset(event.getDirection())).getBlock() == RegistryHandler.CALCIFY_GLYPH_BLOCK &&
                !((CalcifyGlyphTile) glyph).hasAtom()) {
            event.setCanceled(true);
            world.setBlockState(event.getFaceOffsetPos(), Blocks.AIR.getDefaultState(), 3);
            ((CalcifyGlyphTile) glyph).setHasAtom(true);
        }

         */
    }


    //For pulling out of glyphs/TE's or the solver
    @SubscribeEvent
    public static void onPistonPushPost(PistonEvent.Post event) {
        World world = (World) event.getWorld();
        if (!world.isRemote) {
            BlockPos selfPos = event.getPos();
            BlockPos targetPos = event.getFaceOffsetPos();
            //System.out.println((event.getPistonMoveType().isExtend ? "After Extending" : "After Retracting") + " | Self: " + selfPos + " ->" + world.getBlockState(selfPos) + ", Target: " + targetPos + " -> " + world.getBlockState(targetPos));
        }
        //TileEntity glyph = world.getTileEntity(offset.offset(event.getDirection()));
        //BlockState blockstate = event.getState();

        /*if (!event.getPistonMoveType().isExtend && world.getBlockState(offset.offset(event.getDirection())).getBlock() == RegistryHandler.CALCIFY_GLYPH_BLOCK) {
            //System.out.println("Calcifier state: " + ((CalcifyGlyphTile) glyph).hasAtom());
        }

        System.out.println(world.getBlockState(offset));

        if (!event.getPistonMoveType().isExtend &&
                world.getBlockState(offset).getBlock() == RegistryHandler.CALCIFY_GLYPH_BLOCK &&
                ((CalcifyGlyphTile) glyph).hasAtom() &&
                blockstate.getBlock() == Blocks.STICKY_PISTON) {
            System.out.println("Got here");
            world.setBlockState(event.getFaceOffsetPos(), RegistryHandler.ATOM_BLOCK.getDefaultState().with(ELEMENT, Element.SALT), 3);
            ((CalcifyGlyphTile) glyph).setHasAtom(false);
        }*/
    }
}
