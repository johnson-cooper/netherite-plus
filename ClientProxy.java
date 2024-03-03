package net.minecraft.src;


public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
        // Register your renderers here
        ModLoader.registerTileEntity(TileEntityItemFrame.class, "ItemFrame", new ItemFrameRenderer());
    }
}