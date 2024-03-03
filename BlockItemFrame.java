package net.minecraft.src;

public class BlockItemFrame extends BlockContainer {

    protected BlockItemFrame(int id) {
        super(id, Material.circuits);
    }

    @Override
    public TileEntity getBlockEntity() {
        return new TileEntityItemFrame();
    }



    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isRemote) { // Check if it's not the client side
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity instanceof TileEntityItemFrame) {
                TileEntityItemFrame itemFrame = (TileEntityItemFrame) tileEntity;
                ItemStack heldItem = player.getCurrentEquippedItem();
                ItemStack itemFrameStack = itemFrame.getItemStack();

                if (heldItem != null) {
                    if (itemFrameStack == null) {
                        // If the item frame is empty, put the held item in the frame
                        itemFrame.setItemStack(heldItem.copy());
                        if (!player.capabilities.isCreativeMode) {
                            heldItem.stackSize--; // Decrease held item stack size
                            if (heldItem.stackSize <= 0) {
                                // Remove the item from the player's hand if the stack size is zero or negative
                                player.inventory.mainInventory[player.inventory.currentItem] = null;
                            }
                        }
                    }
                } else if (itemFrameStack != null) {
                    // If the player is not holding an item and there is an item in the frame, give it to the player
                    player.inventory.addItemStackToInventory(new ItemStack(itemFrameStack.itemID, 1, itemFrameStack.getItemDamage()));
                    itemFrame.setItemStack(null); // Remove the item from the frame
                }
                world.notifyBlockChange(x, y, z, blockID); // Notify neighboring blocks of the block update
            }
        }
        return true;
    }


    @Override
    public boolean renderAsNormalBlock() {
        return false; // Ensure the block does not render as a normal block
    }

    @Override
    public boolean isOpaqueCube() {
        return false; // Ensure the block is not considered an opaque cube
    }
    // Add this method to set the size and position of the item frame's texture


    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        // Get the side the block is placed on
        int sidePlacedOn = getSidePlacedOn(world, x, y, z);

        // Depending on the side, adjust the bounding box accordingly
        switch (sidePlacedOn) {
            case 2: // North
                setBlockBounds(0.2F, 0.2F, 0.8F, 0.8F, 0.8F, 1.0F);
                break;
            case 3: // South
                setBlockBounds(0.2F, 0.2F, 0.0F, 0.8F, 0.8F, 0.2F);
                break;
            case 4: // West
                setBlockBounds(0.8F, 0.2F, 0.2F, 1.0F, 0.8F, 0.8F);
                break;
            case 5: // East
                setBlockBounds(0.0F, 0.2F, 0.2F, 0.2F, 0.8F, 0.8F);
                break;
            default: // Default bounds if no suitable side found
                setBlockBounds(0.2F, 0.2F, 0.0F, 0.8F, 0.8F, 1.0F); // Flat and skinny
                break;





    }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        // Set the collision bounding box to ensure it can only be placed on the side of blocks
        return null;
    }


    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        // Check if the block can be placed at the specified location
        int side = getSidePlacedOn(world, x, y, z);
        if (side == -1) {
            return false; // Cannot place if there's no suitable side
        }

        // Check if there is another item frame adjacent to the position
        if (isItemFrameNearby(world, x, y, z)) {
            return false; // Cannot place if there's an item frame nearby
        }

        return true; // Return true if no issues found
    }

    private boolean isItemFrameNearby(World world, int x, int y, int z) {
        // Check all adjacent blocks for item frames
        return world.getBlockId(x - 1, y, z) == blockID ||
                world.getBlockId(x + 1, y, z) == blockID ||
                world.getBlockId(x, y, z - 1) == blockID ||
                world.getBlockId(x, y, z + 1) == blockID ||
                world.getBlockId(x, y-1, z) == blockID ||
                world.getBlockId(x, y+1, z) == blockID;
    }

    public int getSidePlacedOn(IBlockAccess world, int x, int y, int z) {
        // Check each direction except for top and bottom
        if (world.getBlockId(x - 1, y, z) != 0) {
            return 5; // Side facing towards the negative X direction
        } else if (world.getBlockId(x + 1, y, z) != 0) {
            return 4; // Side facing towards the positive X direction
        } else if (world.getBlockId(x, y, z - 1) != 0) {
            return 3; // Side facing towards the negative Z direction
        } else if (world.getBlockId(x, y, z + 1) != 0) {
            return 2; // Side facing towards the positive Z direction
        }

        return -1; // No suitable side found
    }



    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID) {
        // Check if the block directly behind the item frame is air (indicating it's destroyed)
        if (world.isAirBlock(x, y, z + 1)) {
            // Drop the item frame block as an item
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            // Destroy the item frame block
            world.setBlockWithNotify(x, y, z, 0);
        }
    }







}

