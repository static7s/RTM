package com.static7s.relearning_to_mod.block;

import com.static7s.relearning_to_mod.creativetab.CreativeTabRTM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public class BlockRedstoneWireBlockWIP extends BlockRTM{

        boolean field_150181_a = true;
        Set field_150179_b = new HashSet();

        public BlockRedstoneWireBlockWIP()
        {
            super(Material.circuits);
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            this.setBlockName("redstoneWireBlock");
            this.setBlockTextureName("redstoneWireBlock");
            this.setResistance(6000000.0F);
            this.setStepSound(soundTypeStone);
            this.setCreativeTab(CreativeTabRTM.RTM_TAB);
        }

       //        @SideOnly(Side.CLIENT)
//        private IIcon field_150182_M;
//        @SideOnly(Side.CLIENT)
//        private IIcon field_150183_N;
//        @SideOnly(Side.CLIENT)
//        private IIcon field_150184_O;
//        @SideOnly(Side.CLIENT)
//        private IIcon field_150180_P;
//        private static final String __OBFID = "CL_00000295";
//        /**
//         * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
//         * cleared to be reused)
//         */
//    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
//    {
//        return null;
//    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }
    public boolean isBlockNormalCube()
    {
        return true;
    }
//    public boolean isNormalCube(IBlockAccess world, int x, int y, int z)
//    {
//        return getMaterial().isOpaque() && renderAsNormalBlock();
//    }
    public boolean isBlockSolid()
    {
        return true;

    }
    public boolean isCollidable()
    {
        return true;
    }
    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return true;
    }

//    /**
//     * The type of render function that is called for this block
//     */
//    public int getRenderType()
//    {
//        return 5;
//    }

//    /**
//     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
//     * when first determining what to render.
//     */
//    @SideOnly(Side.CLIENT)
//    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
//    {
//        return 8388608;
//    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
//        return World.doesBlockHaveSolidTopSurface(p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_) || p_149742_1_.getBlock(p_149742_2_, p_149742_3_ - 1, p_149742_4_) == Blocks.glowstone;
        return true;

    }
    public static boolean doesBlockHaveSolidTopSurface(IBlockAccess p_147466_0_, int p_147466_1_, int p_147466_2_, int p_147466_3_)
    {
//        Block block = p_147466_0_.getBlock(p_147466_1_, p_147466_2_, p_147466_3_);
//        return block.isSideSolid(p_147466_0_, p_147466_1_, p_147466_2_, p_147466_3_, ForgeDirection.UP);
        return true;
    }
    public void func_150177_e(World p_150177_1_, int p_150177_2_, int p_150177_3_, int p_150177_4_)
    {
        this.func_150175_a(p_150177_1_, p_150177_2_, p_150177_3_, p_150177_4_, p_150177_2_, p_150177_3_, p_150177_4_);
        ArrayList arraylist = new ArrayList(this.field_150179_b);
        this.field_150179_b.clear();

        for (int l = 0; l < arraylist.size(); ++l)
        {
            ChunkPosition chunkposition = (ChunkPosition)arraylist.get(l);
            p_150177_1_.notifyBlocksOfNeighborChange(chunkposition.chunkPosX, chunkposition.chunkPosY, chunkposition.chunkPosZ, this);
        }
    }

    public void func_150175_a(World p_150175_1_, int p_150175_2_, int p_150175_3_, int p_150175_4_, int p_150175_5_, int p_150175_6_, int p_150175_7_)
    {
        int k1 = p_150175_1_.getBlockMetadata(p_150175_2_, p_150175_3_, p_150175_4_);
        byte b0 = 0;
        int i3 = this.func_150178_a(p_150175_1_, p_150175_5_, p_150175_6_, p_150175_7_, b0);
        this.field_150181_a = false;
        int l1 = p_150175_1_.getStrongestIndirectPower(p_150175_2_, p_150175_3_, p_150175_4_);
        this.field_150181_a = true;

        if (l1 > 0 && l1 > i3 - 1)
        {
            i3 = l1;
        }

        int i2 = 0;

        for (int j2 = 0; j2 < 4; ++j2)
        {
            int k2 = p_150175_2_;
            int l2 = p_150175_4_;

            if (j2 == 0)
            {
                k2 = p_150175_2_ - 1;
            }

            if (j2 == 1)
            {
                ++k2;
            }

            if (j2 == 2)
            {
                l2 = p_150175_4_ - 1;
            }

            if (j2 == 3)
            {
                ++l2;
            }

            if (k2 != p_150175_5_ || l2 != p_150175_7_)
            {
                i2 = this.func_150178_a(p_150175_1_, k2, p_150175_3_, l2, i2);
            }

            if (p_150175_1_.getBlock(k2, p_150175_3_, l2).isNormalCube() && !p_150175_1_.getBlock(p_150175_2_, p_150175_3_ + 1, p_150175_4_).isNormalCube())
            {
                if ((k2 != p_150175_5_ || l2 != p_150175_7_) && p_150175_3_ >= p_150175_6_)
                {
                    i2 = this.func_150178_a(p_150175_1_, k2, p_150175_3_ + 1, l2, i2);
                }
            }
            else if (!p_150175_1_.getBlock(k2, p_150175_3_, l2).isNormalCube() && (k2 != p_150175_5_ || l2 != p_150175_7_) && p_150175_3_ <= p_150175_6_)
            {
                i2 = this.func_150178_a(p_150175_1_, k2, p_150175_3_ - 1, l2, i2);
            }
        }

        if (i2 > i3)
        {
            i3 = i2 - 1;
        }
        else if (i3 > 0)
        {
            --i3;
        }
        else
        {
            i3 = 0;
        }

        if (l1 > i3 - 1)
        {
            i3 = l1;
        }

        if (k1 != i3)
        {
            p_150175_1_.setBlockMetadataWithNotify(p_150175_2_, p_150175_3_, p_150175_4_, i3, 2);
            this.field_150179_b.add(new ChunkPosition(p_150175_2_, p_150175_3_, p_150175_4_));
            this.field_150179_b.add(new ChunkPosition(p_150175_2_ - 1, p_150175_3_, p_150175_4_));
            this.field_150179_b.add(new ChunkPosition(p_150175_2_ + 1, p_150175_3_, p_150175_4_));
            this.field_150179_b.add(new ChunkPosition(p_150175_2_, p_150175_3_ - 1, p_150175_4_));
            this.field_150179_b.add(new ChunkPosition(p_150175_2_, p_150175_3_ + 1, p_150175_4_));
            this.field_150179_b.add(new ChunkPosition(p_150175_2_, p_150175_3_, p_150175_4_ - 1));
            this.field_150179_b.add(new ChunkPosition(p_150175_2_, p_150175_3_, p_150175_4_ + 1));
        }
    }

    private void func_150172_m(World p_150172_1_, int p_150172_2_, int p_150172_3_, int p_150172_4_)
    {
        if (p_150172_1_.getBlock(p_150172_2_, p_150172_3_, p_150172_4_) == this)
        {
            p_150172_1_.notifyBlocksOfNeighborChange(p_150172_2_, p_150172_3_, p_150172_4_, this);
            p_150172_1_.notifyBlocksOfNeighborChange(p_150172_2_ - 1, p_150172_3_, p_150172_4_, this);
            p_150172_1_.notifyBlocksOfNeighborChange(p_150172_2_ + 1, p_150172_3_, p_150172_4_, this);
            p_150172_1_.notifyBlocksOfNeighborChange(p_150172_2_, p_150172_3_, p_150172_4_ - 1, this);
            p_150172_1_.notifyBlocksOfNeighborChange(p_150172_2_, p_150172_3_, p_150172_4_ + 1, this);
            p_150172_1_.notifyBlocksOfNeighborChange(p_150172_2_, p_150172_3_ - 1, p_150172_4_, this);
            p_150172_1_.notifyBlocksOfNeighborChange(p_150172_2_, p_150172_3_ + 1, p_150172_4_, this);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
    {
        super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);

        if (!p_149726_1_.isRemote)
        {
            this.func_150177_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
            p_149726_1_.notifyBlocksOfNeighborChange(p_149726_2_, p_149726_3_ + 1, p_149726_4_, this);
            p_149726_1_.notifyBlocksOfNeighborChange(p_149726_2_, p_149726_3_ - 1, p_149726_4_, this);
            this.func_150172_m(p_149726_1_, p_149726_2_ - 1, p_149726_3_, p_149726_4_);
            this.func_150172_m(p_149726_1_, p_149726_2_ + 1, p_149726_3_, p_149726_4_);
            this.func_150172_m(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_ - 1);
            this.func_150172_m(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_ + 1);

            if (p_149726_1_.getBlock(p_149726_2_ - 1, p_149726_3_, p_149726_4_).isNormalCube())
            {
                this.func_150172_m(p_149726_1_, p_149726_2_ - 1, p_149726_3_ + 1, p_149726_4_);
            }
            else
            {
                this.func_150172_m(p_149726_1_, p_149726_2_ - 1, p_149726_3_ - 1, p_149726_4_);
            }

            if (p_149726_1_.getBlock(p_149726_2_ + 1, p_149726_3_, p_149726_4_).isNormalCube())
            {
                this.func_150172_m(p_149726_1_, p_149726_2_ + 1, p_149726_3_ + 1, p_149726_4_);
            }
            else
            {
                this.func_150172_m(p_149726_1_, p_149726_2_ + 1, p_149726_3_ - 1, p_149726_4_);
            }

            if (p_149726_1_.getBlock(p_149726_2_, p_149726_3_, p_149726_4_ - 1).isNormalCube())
            {
                this.func_150172_m(p_149726_1_, p_149726_2_, p_149726_3_ + 1, p_149726_4_ - 1);
            }
            else
            {
                this.func_150172_m(p_149726_1_, p_149726_2_, p_149726_3_ - 1, p_149726_4_ - 1);
            }

            if (p_149726_1_.getBlock(p_149726_2_, p_149726_3_, p_149726_4_ + 1).isNormalCube())
            {
                this.func_150172_m(p_149726_1_, p_149726_2_, p_149726_3_ + 1, p_149726_4_ + 1);
            }
            else
            {
                this.func_150172_m(p_149726_1_, p_149726_2_, p_149726_3_ - 1, p_149726_4_ + 1);
            }
        }
    }

    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);

        if (!p_149749_1_.isRemote)
        {
            p_149749_1_.notifyBlocksOfNeighborChange(p_149749_2_, p_149749_3_ + 1, p_149749_4_, this);
            p_149749_1_.notifyBlocksOfNeighborChange(p_149749_2_, p_149749_3_ - 1, p_149749_4_, this);
            p_149749_1_.notifyBlocksOfNeighborChange(p_149749_2_ + 1, p_149749_3_, p_149749_4_, this);
            p_149749_1_.notifyBlocksOfNeighborChange(p_149749_2_ - 1, p_149749_3_, p_149749_4_, this);
            p_149749_1_.notifyBlocksOfNeighborChange(p_149749_2_, p_149749_3_, p_149749_4_ + 1, this);
            p_149749_1_.notifyBlocksOfNeighborChange(p_149749_2_, p_149749_3_, p_149749_4_ - 1, this);
            this.func_150177_e(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_);
            this.func_150172_m(p_149749_1_, p_149749_2_ - 1, p_149749_3_, p_149749_4_);
            this.func_150172_m(p_149749_1_, p_149749_2_ + 1, p_149749_3_, p_149749_4_);
            this.func_150172_m(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_ - 1);
            this.func_150172_m(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_ + 1);

            if (p_149749_1_.getBlock(p_149749_2_ - 1, p_149749_3_, p_149749_4_).isNormalCube())
            {
                this.func_150172_m(p_149749_1_, p_149749_2_ - 1, p_149749_3_ + 1, p_149749_4_);
            }
            else
            {
                this.func_150172_m(p_149749_1_, p_149749_2_ - 1, p_149749_3_ - 1, p_149749_4_);
            }

            if (p_149749_1_.getBlock(p_149749_2_ + 1, p_149749_3_, p_149749_4_).isNormalCube())
            {
                this.func_150172_m(p_149749_1_, p_149749_2_ + 1, p_149749_3_ + 1, p_149749_4_);
            }
            else
            {
                this.func_150172_m(p_149749_1_, p_149749_2_ + 1, p_149749_3_ - 1, p_149749_4_);
            }

            if (p_149749_1_.getBlock(p_149749_2_, p_149749_3_, p_149749_4_ - 1).isNormalCube())
            {
                this.func_150172_m(p_149749_1_, p_149749_2_, p_149749_3_ + 1, p_149749_4_ - 1);
            }
            else
            {
                this.func_150172_m(p_149749_1_, p_149749_2_, p_149749_3_ - 1, p_149749_4_ - 1);
            }

            if (p_149749_1_.getBlock(p_149749_2_, p_149749_3_, p_149749_4_ + 1).isNormalCube())
            {
                this.func_150172_m(p_149749_1_, p_149749_2_, p_149749_3_ + 1, p_149749_4_ + 1);
            }
            else
            {
                this.func_150172_m(p_149749_1_, p_149749_2_, p_149749_3_ - 1, p_149749_4_ + 1);
            }
        }
    }

    private int func_150178_a(World p_150178_1_, int p_150178_2_, int p_150178_3_, int p_150178_4_, int p_150178_5_)
    {
        if (p_150178_1_.getBlock(p_150178_2_, p_150178_3_, p_150178_4_) != this)
        {
            return p_150178_5_;
        }
        else
        {
            int i1 = p_150178_1_.getBlockMetadata(p_150178_2_, p_150178_3_, p_150178_4_);
            return i1 > p_150178_5_ ? i1 : p_150178_5_;
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        if (!p_149695_1_.isRemote)
        {
            boolean flag = this.canPlaceBlockAt(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);

            if (flag)
            {
                this.func_150177_e(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
            }
            else
            {
                this.dropBlockAsItem(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, 0, 0);
                p_149695_1_.setBlockToAir(p_149695_2_, p_149695_3_, p_149695_4_);
            }

            super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
        }
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
//        return Items.redstone;
        return Item.getItemFromBlock(this);
    }

    public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_)
    {
        return !this.field_150181_a ? 0 : this.isProvidingWeakPower(p_149748_1_, p_149748_2_, p_149748_3_, p_149748_4_, p_149748_5_);
    }

    public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_)
    {
        if (!this.field_150181_a)
        {
            return 0;
        }
        else
        {
            int i1 = p_149709_1_.getBlockMetadata(p_149709_2_, p_149709_3_, p_149709_4_);

            if (i1 == 0)
            {
                return 0;
            }
            else if (p_149709_5_ == 1)
            {
                return i1;
            }
            else
            {
                boolean flag = func_150176_g(p_149709_1_, p_149709_2_ - 1, p_149709_3_, p_149709_4_, 1) || !p_149709_1_.getBlock(p_149709_2_ - 1, p_149709_3_, p_149709_4_).isNormalCube() && func_150176_g(p_149709_1_, p_149709_2_ - 1, p_149709_3_ - 1, p_149709_4_, -1);
                boolean flag1 = func_150176_g(p_149709_1_, p_149709_2_ + 1, p_149709_3_, p_149709_4_, 3) || !p_149709_1_.getBlock(p_149709_2_ + 1, p_149709_3_, p_149709_4_).isNormalCube() && func_150176_g(p_149709_1_, p_149709_2_ + 1, p_149709_3_ - 1, p_149709_4_, -1);
                boolean flag2 = func_150176_g(p_149709_1_, p_149709_2_, p_149709_3_, p_149709_4_ - 1, 2) || !p_149709_1_.getBlock(p_149709_2_, p_149709_3_, p_149709_4_ - 1).isNormalCube() && func_150176_g(p_149709_1_, p_149709_2_, p_149709_3_ - 1, p_149709_4_ - 1, -1);
                boolean flag3 = func_150176_g(p_149709_1_, p_149709_2_, p_149709_3_, p_149709_4_ + 1, 0) || !p_149709_1_.getBlock(p_149709_2_, p_149709_3_, p_149709_4_ + 1).isNormalCube() && func_150176_g(p_149709_1_, p_149709_2_, p_149709_3_ - 1, p_149709_4_ + 1, -1);

                if (!p_149709_1_.getBlock(p_149709_2_, p_149709_3_ + 1, p_149709_4_).isNormalCube())
                {
                    if (p_149709_1_.getBlock(p_149709_2_ - 1, p_149709_3_, p_149709_4_).isNormalCube() && func_150176_g(p_149709_1_, p_149709_2_ - 1, p_149709_3_ + 1, p_149709_4_, -1))
                    {
                        flag = true;
                    }

                    if (p_149709_1_.getBlock(p_149709_2_ + 1, p_149709_3_, p_149709_4_).isNormalCube() && func_150176_g(p_149709_1_, p_149709_2_ + 1, p_149709_3_ + 1, p_149709_4_, -1))
                    {
                        flag1 = true;
                    }

                    if (p_149709_1_.getBlock(p_149709_2_, p_149709_3_, p_149709_4_ - 1).isNormalCube() && func_150176_g(p_149709_1_, p_149709_2_, p_149709_3_ + 1, p_149709_4_ - 1, -1))
                    {
                        flag2 = true;
                    }

                    if (p_149709_1_.getBlock(p_149709_2_, p_149709_3_, p_149709_4_ + 1).isNormalCube() && func_150176_g(p_149709_1_, p_149709_2_, p_149709_3_ + 1, p_149709_4_ + 1, -1))
                    {
                        flag3 = true;
                    }
                }

                return !flag2 && !flag1 && !flag && !flag3 && p_149709_5_ >= 2 && p_149709_5_ <= 5 ? i1 : (p_149709_5_ == 2 && flag2 && !flag && !flag1 ? i1 : (p_149709_5_ == 3 && flag3 && !flag && !flag1 ? i1 : (p_149709_5_ == 4 && flag && !flag2 && !flag3 ? i1 : (p_149709_5_ == 5 && flag1 && !flag2 && !flag3 ? i1 : 0))));
            }
        }
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
//        return this.field_150181_a;
        return true;
    }
    /**
     * Determine if this block can make a redstone connection on the side provided,
     * Useful to control which sides are inputs and outputs for redstone wires.
     *
     * Side:
     *  -1: UP
     *   0: NORTH
     *   1: EAST
     *   2: SOUTH
     *   3: WEST
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @param side The side that is trying to make the connection
     * @return True to make the connection
     */
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
    {
        /**
         * Can this block provide power. Only wire currently seems to have this change based on its state.
         */
        return canProvidePower();
    }

    /**
     * Returns true if redstone wire can connect to the specified block. Params: World, X, Y, Z, side (not a normal
     * notch-side, this can be 0, 1, 2, 3 or -1)
     */
    public static boolean isPowerProviderOrWire(IBlockAccess p_150174_0_, int p_150174_1_, int p_150174_2_, int p_150174_3_, int p_150174_4_)
    {
        Block block = p_150174_0_.getBlock(p_150174_1_, p_150174_2_, p_150174_3_);

        if (block == Blocks.redstone_wire)
        {
            return true;
        }
        else if (!Blocks.unpowered_repeater.func_149907_e(block))
        {
            return block.canConnectRedstone(p_150174_0_, p_150174_1_, p_150174_2_, p_150174_3_, p_150174_4_);
        }
        else
        {
            int i1 = p_150174_0_.getBlockMetadata(p_150174_1_, p_150174_2_, p_150174_3_);
            return p_150174_4_ == (i1 & 3) || p_150174_4_ == Direction.rotateOpposite[i1 & 3];
        }
    }

    public static boolean func_150176_g(IBlockAccess p_150176_0_, int p_150176_1_, int p_150176_2_, int p_150176_3_, int p_150176_4_)
    {
        if (isPowerProviderOrWire(p_150176_0_, p_150176_1_, p_150176_2_, p_150176_3_, p_150176_4_))
        {
            return true;
        }
        else if (p_150176_0_.getBlock(p_150176_1_, p_150176_2_, p_150176_3_) == Blocks.powered_repeater)
        {
            int i1 = p_150176_0_.getBlockMetadata(p_150176_1_, p_150176_2_, p_150176_3_);
            return p_150176_4_ == (i1 & 3);
        }
        else
        {
            return false;
        }
    }

//    /**
//     * Gets an item for the block being called on. Args: world, x, y, z
//     */
//    @SideOnly(Side.CLIENT)
//    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
//    {
//        return Items.redstone;
//    }
        public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
        {
            return true;
        }

    @SideOnly(Side.CLIENT)
        public Block getBlock(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
        {
            return getBlockFromName("redstoneWireBlock");
        }



    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("relearning_to_mod:PressureSensor");
//        this.field_150182_M = p_149651_1_.registerIcon(this.getTextureName() + "_" + "cross");
//        this.field_150183_N = p_149651_1_.registerIcon(this.getTextureName() + "_" + "line");
//        this.field_150184_O = p_149651_1_.registerIcon(this.getTextureName() + "_" + "cross_overlay");
//        this.field_150180_P = p_149651_1_.registerIcon(this.getTextureName() + "_" + "line_overlay");
//        this.blockIcon = this.field_150182_M;
    }

//    @SideOnly(Side.CLIENT)
//    public static IIcon getRedstoneWireIcon(String p_150173_0_)
//    {
//        return p_150173_0_.equals("cross") ? Blocks.redstone_wire.field_150182_M : (p_150173_0_.equals("line") ? Blocks.redstone_wire.field_150183_N : (p_150173_0_.equals("cross_overlay") ? Blocks.redstone_wire.field_150184_O : (p_150173_0_.equals("line_overlay") ? Blocks.redstone_wire.field_150180_P : null)));
//    }
}
