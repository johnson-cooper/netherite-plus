package net.minecraft.src;

import java.util.Random;

public class BlockChimneyBlock extends Block {
    public BlockChimneyBlock(int i, int j) {
        super(i, j, Material.glass);
        this.setTickRandomly(false); // Disable random ticking
    }

    // This method handles the scheduled update of the block
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        // Schedule the first update after 1 second
        world.scheduleBlockUpdate(x, y, z, this.blockID, 20);
    }

    // This method handles the continuous smoke emission when activated by redstone
    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        // Check if the block is powered by redstone
        if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
            double particleX = x + 0.5;
            double particleY = y + 1.0; // Particle position above the block
            double particleZ = z + 0.5;

            double motionX = 0.0;
            double motionY = 0.1; // Upward motion
            double motionZ = 0.0;

            // Spawn larger smoke particles
            world.spawnParticle("largesmoke", particleX, particleY, particleZ, motionX, motionY, motionZ);
        }

        // Schedule the next update after 1 second
        world.scheduleBlockUpdate(x, y, z, this.blockID, 20);
    }

    // This method determines the ID of the item dropped when the block is destroyed
    @Override
    public int idDropped(int i, Random random, int j) {
        return mod_retrorevisions.ChimneyBlock.blockID;
    }

    // This method determines how many items are dropped when the block is destroyed
    @Override
    public int quantityDropped(Random random) {
        return 1;
    }
}
