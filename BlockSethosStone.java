package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BlockSethosStone extends Block
{
    public BlockSethosStone(int i, int j)
    {
        super(i, j, Material.glass);
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 15; // Maximum brightness
    }

    public int idDropped(int i, Random random, int j)
    {
        return mod_retrorevisions.SethosStone.blockID;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    // Override method to handle player proximity
    public void onBlockAdded(World world, int x, int y, int z)
    {
        schedulePotionEffectsTask(world, x, y, z);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
    {
        schedulePotionEffectsTask(world, x, y, z);
    }

    private void schedulePotionEffectsTask(World world, final int x, final int y, final int z) {
        // Schedule a task to apply potion effects periodically
        world.scheduleBlockUpdate(x, y, z, this.blockID, 20); // 20 ticks = 1 second
    }

    public void updateTick(World world, int x, int y, int z, Random random)
    {
        applyPotionEffects(world, x, y, z);
        schedulePotionEffectsTask(world, x, y, z); // Schedule the next update
    }

    private void applyPotionEffects(World world, int x, int y, int z)
    {
        // Check nearby players
        List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class,
                AxisAlignedBB.getBoundingBox(x - 50, y - 50, z - 50, x + 50, y + 50, z + 50));

        // Apply potion effects to nearby players
        for (EntityPlayer player : players) {
            // Check if the player is within 50 blocks
            if (player.getDistanceSq(x, y, z) <= 2500) { // 50 blocks squared
                // Apply jump boost and speed potion effects
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 200, 1)); // Jump boost for 10 seconds
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 200, 1)); // Speed boost for 10 seconds
            }
        }
    }
}
