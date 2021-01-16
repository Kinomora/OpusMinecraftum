package com.kinomora.opusminecraftum;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BreakableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.Mod;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = OpusMinecraftum.ID)
public class AtomBlock extends BreakableBlock {

    public static final EnumProperty<Element> ELEMENT = EnumProperty.create("element", Element.class);
    public static final BooleanProperty BOND_DOWN = BooleanProperty.create("bond_down");
    public static final BooleanProperty BOND_UP = BooleanProperty.create("bond_up");
    public static final BooleanProperty BOND_NORTH = BooleanProperty.create("bond_north");
    public static final BooleanProperty BOND_SOUTH = BooleanProperty.create("bond_south");
    public static final BooleanProperty BOND_WEST = BooleanProperty.create("bond_west");
    public static final BooleanProperty BOND_EAST = BooleanProperty.create("bond_east");

    public static final BooleanProperty[] BONDS = new BooleanProperty[] {
            BOND_DOWN, BOND_UP, BOND_NORTH, BOND_SOUTH, BOND_WEST, BOND_EAST
    };

    public enum PistonStickType {
        PULL, BRANCH
    }

    public static PistonStickType stickType = null;
    public static Direction stickDirection = null;

    protected AtomBlock(Properties properties) {
        super(properties);
        BlockState defState = this.getDefaultState();
        for (int i = 0; i < BONDS.length; i++) {
            defState = defState.with(BONDS[i], false);
        }
        this.setDefaultState(defState);
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        if (stickType == null)
            return true;
        else if (stickType == PistonStickType.BRANCH) {
            return stickDirection != null && state.get(BONDS[stickDirection.getIndex()]);
        } else if (stickType == PistonStickType.PULL) {
            return stickDirection != null && state.get(BONDS[stickDirection.getIndex()]);
        }

        return false;
    }

    @Override
    public boolean canStickTo(BlockState state, BlockState other) {
        if (other.isIn(this)) {
            if (stickDirection != null) {
                if (stickType == PistonStickType.PULL)
                    return state.get(BONDS[stickDirection.getIndex()]);
                else
                    return other.get(BONDS[stickDirection.getIndex()]);
            }
        }

        return super.canStickTo(state, other);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            if (player.isSneaking()) {
                Direction face = hit.getFace();
                boolean faceBonded = state.get(BONDS[face.getIndex()]);
                worldIn.setBlockState(pos, state.with(BONDS[face.getIndex()], !faceBonded));
                if (worldIn.getBlockState(pos).get(BONDS[face.getIndex()])) {
                    player.sendStatusMessage(new StringTextComponent("Atom is now bonded at " + face), true);
                } else {
                    player.sendStatusMessage(new StringTextComponent("Atom is now un-bonded at " + face), true);
                }
            } else {
                Element element = state.get(ELEMENT);
                worldIn.setBlockState(pos, state.with(ELEMENT, Element.values()[(element.ordinal()+1)%Element.values().length]));
            }
        }

        return ActionResultType.SUCCESS;
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
        builder.add(ELEMENT);
        builder.add(BONDS);
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return null;
    }

    public enum Element implements IStringSerializable {
        AIR, FIRE, EARTH, WATER, SALT, QUICKSILVER, VITAE, MORS, LEAD, TIN, IRON, COPPER, SILVER, GOLD;

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
