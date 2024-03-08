package net.minecraft.src;

import java.util.Random;

public class RedstoneStairs extends BlockStairs {

    private static final Random random = new Random();

    public RedstoneStairs(int id) {
        super(id, Block.blockSteel);
        // Set useNeighborBrightness to true for all faces

        this.setLightOpacity(1);
    }


    // This method returns the ID of the item dropped when the block is destroyed
    public int idDropped(int i, Random random, int j) {
        return mod_retrorevisions.Redstonestairs.blockID;
    }

    // This method returns the quantity of items dropped when the block is destroyed
    public int quantityDropped(Random random) {
        return 1;
    }


    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID) {
        if (!world.isRemote) { // Ensure it's not on the client side
            Block neighborBlock = Block.blocksList[neighborBlockID]; // Get the neighbor block
            if (neighborBlock != null && (neighborBlock instanceof BlockRedstoneWire || neighborBlock instanceof BlockRedstoneTorch || neighborBlock instanceof BlockLever)) { // Check if the neighbor block is a redstone wire, redstone torch, or lever
                if (!world.isBlockIndirectlyGettingPowered(x, y, z)) { // Check if not powered
                    if (world.getBlockId(x, y, z) == this.blockID) { // Check if the block that triggered the event is the Redstone Stairs block itself
                        int newTextureIndex = random.nextInt(8); // Generate a random texture index
                        world.setBlockMetadataWithNotify(x, y, z, newTextureIndex); // Update metadata and notify clients
                    }
                }
            }
        }
    }
}


