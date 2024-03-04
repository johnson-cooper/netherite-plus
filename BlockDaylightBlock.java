package net.minecraft.src;

import java.util.Random;

public class BlockDaylightBlock extends BlockContainer {
    private boolean dayTime = false;

    protected BlockDaylightBlock(int id) {
        super(id, Material.circuits);
    }

    // This method returns the ID of the item dropped when the block is destroyed
    public int idDropped(int i, Random random, int j) {
        return mod_retrorevisions.DaylightBlock.blockID;
    }

    // This method returns the quantity of items dropped when the block is destroyed
    public int quantityDropped(Random random) {
        return 1;
    }

    // Allow redstone signals to pass through when the block is emitting light
    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public boolean isPoweringTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        if (!this.dayTime)  {
            return false;
        } else {
            // Logic for redstone powering here (adjust as needed)
            int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
            return var6 == 5 && par5 == 1 ? true : (var6 == 3 && par5 == 3 ? false : (var6 == 4 && par5 == 2 ? false : (var6 == 1 && par5 == 5 ? false : var6 != 2 || par5 != 4)));
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        // Check if it's daytime
        long time = world.getWorldTime() % 24000; // Get the time in ticks (one full day is 24000 ticks)
        boolean newDayTime = time >= 0 && time < 12000; // Daytime is from 0 to 11999 ticks

        if (newDayTime != dayTime) { // Only update if there's a change in daytime
            dayTime = newDayTime;

            // Notify neighboring blocks of the state change
            world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
            // Update redstone state
            world.notifyBlocksOfNeighborChange(x, y - 1, z, this.blockID); // Notify block below
            world.notifyBlocksOfNeighborChange(x, y + 1, z, this.blockID); // Notify block above
            world.notifyBlocksOfNeighborChange(x - 1, y, z, this.blockID); // Notify block to the west
            world.notifyBlocksOfNeighborChange(x + 1, y, z, this.blockID); // Notify block to the east
            world.notifyBlocksOfNeighborChange(x, y, z - 1, this.blockID); // Notify block to the north
            world.notifyBlocksOfNeighborChange(x, y, z + 1, this.blockID); // Notify block to the south
        }

        // Schedule the next update
        DaylightSensor(world, x, y, z);
    }
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        DaylightSensor(world, x, y, z);

    }

    private void DaylightSensor(World world, final int x, final int y, final int z) {
        world.scheduleBlockUpdate(x, y, z, this.blockID, 80); // 20 ticks = 1 second
    }



    // This method is necessary to specify the tile entity associated with this block
    @Override
    public TileEntity getBlockEntity() {
        return null; // We're not using a tile entity
    }
    // Make the block non-solid (not a normal cube)

    public boolean isBlockNormalCube() {
        return false;
    }
}

