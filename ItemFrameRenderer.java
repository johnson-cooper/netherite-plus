package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class ItemFrameRenderer extends TileEntitySpecialRenderer {
    private static final int ITEM_TEXTURE_ID = ModLoader.getMinecraftInstance().renderEngine.getTexture("/gui/items.png");
    private static final int BLOCK_TEXTURE_ID = ModLoader.getMinecraftInstance().renderEngine.getTexture("/terrain.png");

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        if (!(tileEntity instanceof TileEntityItemFrame)) {
            return; // Ensure the tile entity is an instance of TileEntityItemFrame
        }

        TileEntityItemFrame itemFrame = (TileEntityItemFrame) tileEntity;
        ItemStack itemStack = itemFrame.getItemStack();

        if (itemStack != null) {
            GL11.glPushMatrix();
            GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);

            // Get the side the item frame is placed on
            int sidePlacedOn = ((BlockItemFrame) Block.blocksList[itemFrame.getBlockType().blockID]).getSidePlacedOn(tileEntity.worldObj, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);

            // Determine rotation angle based on the side placed on
            float rotationAngle = 0.0F;
            switch (sidePlacedOn) {
                case 2: // North
                    rotationAngle = 0.0F;
                    break;
                case 3: // South
                    rotationAngle = 180.0F;
                    break;
                case 4: // West
                    rotationAngle = 90.0F;
                    break;
                case 5: // East
                    rotationAngle = -90.0F;
                    break;
            }

            // Rotate the item based on the calculated angle
            GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);

            // Rotate the tessellator so it faces upright
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);

            // Translate the item to the side of the block
            GL11.glTranslatef(-0.25F, -0.25F, -0.3F); // Adjust this value as needed

            // Scale down for miniature rendering
            GL11.glScaled(0.5, 0.5, 0.5); // Adjust scale as needed

            // Get the item's texture index
            int iconIndex = itemStack.getItem().getIconIndex(itemStack);
            int textureID = itemStack.getItem() instanceof ItemBlock ? BLOCK_TEXTURE_ID : ITEM_TEXTURE_ID;

            // Bind the texture of the item
            ModLoader.getMinecraftInstance().renderEngine.bindTexture(textureID);

            // Render the item
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(0, 0, 0, (iconIndex % 16 + 1) / 16.0F, (iconIndex / 16 + 1) / 16.0F); // Bottom-right
            tessellator.addVertexWithUV(0, 0, 1, (iconIndex % 16 + 1) / 16.0F, iconIndex / 16 / 16.0F); // Bottom-left
            tessellator.addVertexWithUV(1, 0, 1, iconIndex % 16 / 16.0F, iconIndex / 16 / 16.0F); // Top-left
            tessellator.addVertexWithUV(1, 0, 0, iconIndex % 16 / 16.0F, (iconIndex / 16 + 1) / 16.0F); // Top-right
            tessellator.draw();

            GL11.glPopMatrix();
        }
    }
}
