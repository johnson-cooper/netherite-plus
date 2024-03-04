package net.minecraft.src;


public class mod_retrorevisions extends BaseMod
{

    public static final BlockContainer itemFrame = new BlockItemFrame(554);
    public static ClientProxy proxy = new ClientProxy(); // Initialize as common proxy by default

    public static final Block NetheriteBlock = (new BlockNetheriteBlock(552 /* ID of Block*/, 0).setHardness(5.0F/* How long it takes to break*/).setResistance(0.5F/* Resistance to explosion*/).setStepSound(Block.soundStoneFootstep/* Sound what it will make when you walk on it*/).setBlockName("Block of Netherite"));
    public static final Block SethosStone = (new BlockSethosStone(553 /* ID of Block*/, 0).setHardness(5.0F/* How long it takes to break*/).setResistance(0.5F/* Resistance to explosion*/).setStepSound(Block.soundStoneFootstep/* Sound what it will make when you walk on it*/).setBlockName("Sethos Stone"));
    public static final Item LuxItem = new ItemLuxItem(3000).setItemName("Lux Aeterna");
    public static final BlockContainer DaylightBlock = new BlockDaylightBlock(555);
    public static final BlockContainer NightlightBlock = new BlockNightlightBlock(556);
    public void load()
    {

        /*NETHERITE BLOCK*/
        NetheriteBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/images/netherite_block.png");/* /folderwhereimagewillbe/nameofblock.png */
        ModLoader.registerBlock(NetheriteBlock);
        ModLoader.addName(NetheriteBlock, "Block of Netherite"); /* In game name*/
        ModLoader.addRecipe(new ItemStack(NetheriteBlock, 1), new Object[]
                {
                        "@@@", "@@@", "@@@", Character.valueOf('@'), Block.blockDiamond  /* Recipe "@@@", " # ", " # " that is pickaxe recipe*/
                });


    }

    {
        /*SETHOS STONE*/
        SethosStone.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/images/sethos_stone.png");/* /folderwhereimagewillbe/nameofblock.png */
        ModLoader.registerBlock(SethosStone);
        ModLoader.addName(SethosStone, "Beacon"); /* In game name*/
        ModLoader.addSmelting(mod_retrorevisions.LuxItem.shiftedIndex, new ItemStack(SethosStone, 1));
    }
    /*LUX AETERNA*/

    {

        LuxItem.iconIndex = ModLoader.addOverride("/gui/items.png" , "/images/lux_aeterna.png");
        ModLoader.addName(LuxItem, "Lux Aeterna");
        ModLoader.addRecipe(new ItemStack(LuxItem, 1), new Object[]
                {
                        "@@@", "@@@", "@@@", Character.valueOf('@'), mod_retrorevisions.NetheriteBlock /* Recipe "@@@", " # ", " # " that is pickaxe recipe*/
                });


    }
    { /*ITEM FRAME*/
        itemFrame.setBlockName("Item Frame");
        itemFrame.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/images/item_frame.png");/* /folderwhereimagewillbe/nameofblock.png */
        ModLoader.registerBlock(itemFrame);
        ModLoader.addName(itemFrame, "Item Frame");
        ModLoader.addRecipe(new ItemStack(itemFrame), "XXX", "XYX", "XXX", 'X', Item.stick, 'Y', Item.leather);

        proxy.registerRenderers();



    }

    {

        /*DAYLIGHT BLOCK*/
        DaylightBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/images/daylight_detector.png");/* /folderwhereimagewillbe/nameofblock.png */
        ModLoader.registerBlock(DaylightBlock);
        DaylightBlock.setBlockName("Daylight Detector");
        ModLoader.addName(DaylightBlock, "Daylight Detector"); /* In game name*/
        ModLoader.addRecipe(new ItemStack(DaylightBlock), "XXX", "XYX", "XXX", 'X', Item.redstone, 'Y', mod_retrorevisions.NetheriteBlock);


    }


    {

        /*NightLight Detector BLOCK*/
        NightlightBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/images/night_detector.png");/* /folderwhereimagewillbe/nameofblock.png */
        ModLoader.registerBlock(NightlightBlock);
        NightlightBlock.setBlockName("Night Detector");
        ModLoader.addName(NightlightBlock, "Night Detector"); /* In game name*/
        ModLoader.addRecipe(new ItemStack(NightlightBlock), "XXX", "XYX", "XXX", 'X', Item.redstone, 'Y', mod_retrorevisions.DaylightBlock);


    }
 


    public String getVersion()
    {
        return "1.2.5";
    }
}
