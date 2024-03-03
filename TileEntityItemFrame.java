package net.minecraft.src;

public class TileEntityItemFrame extends TileEntity {
    private ItemStack itemStack;
    private int rotation; // Add rotation field

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public int getRotation() { // Add method to retrieve rotation
        return rotation;
    }

    public void setRotation(int rotation) { // Add method to set rotation
        this.rotation = rotation;
    }

    // Modify this method to return the block texture based on the item held in the item frame
    public int getBlockTexture() {
        return itemStack != null ? itemStack.getItem().iconIndex : mod_retrorevisions.itemFrame.blockIndexInTexture;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        itemStack = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Item"));
        rotation = compound.getInteger("Rotation"); // Read rotation from NBT
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (itemStack != null) {
            NBTTagCompound itemCompound = new NBTTagCompound();
            itemStack.writeToNBT(itemCompound);
            compound.setTag("Item", itemCompound);
        }
        compound.setInteger("Rotation", rotation); // Write rotation to NBT
    }
}
