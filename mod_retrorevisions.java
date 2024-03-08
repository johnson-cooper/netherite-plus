package net.minecraft.src;
import java.util.Map;


public class mod_retrorevisions extends BaseMod
{

    public static final BlockContainer itemFrame = new BlockItemFrame(554);
    public static ClientProxy proxy = new ClientProxy(); // Initialize as common proxy by default

    public static final Block NetheriteBlock = (new BlockNetheriteBlock(552 /* ID of Block*/, 0).setHardness(5.0F/* How long it takes to break*/).setResistance(0.5F/* Resistance to explosion*/).setStepSound(Block.soundStoneFootstep/* Sound what it will make when you walk on it*/).setBlockName("Block of Netherite"));
    public static final Block SethosStone = (new BlockSethosStone(553 /* ID of Block*/, 0).setHardness(5.0F/* How long it takes to break*/).setResistance(0.5F/* Resistance to explosion*/).setStepSound(Block.soundStoneFootstep/* Sound what it will make when you walk on it*/).setBlockName("Sethos Stone"));
    public static final Item LuxItem = new ItemLuxItem(3000).setItemName("Lux Aeterna");
    public static final BlockContainer DaylightBlock = new BlockDaylightBlock(555);
    public static final BlockContainer NightlightBlock = new BlockNightlightBlock(556);
    public static final Block ChimneyBlock = (new BlockChimneyBlock(557 /* ID of Block*/, 0).setHardness(3.0F/* How long it takes to break*/).setResistance(0.5F/* Resistance to explosion*/).setStepSound(Block.soundStoneFootstep/* Sound what it will make when you walk on it*/).setBlockName("Chimney"));
    public static final Item ItemIronBoat = new ItemIronBoatItem(3001).setItemName("Iron Boat");
    public static final Block Redstonestairs = (new RedstoneStairs(559 /* ID of Block*/).setHardness(3.0F/* How long it takes to break*/).setResistance(0.5F/* Resistance to explosion*/).setStepSound(Block.soundStoneFootstep/* Sound what it will make when you walk on it*/).setBlockName("Redstone Stairs"));




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
        ModLoader.addRecipe(new ItemStack(DaylightBlock), "XXX", "XYX", "XXX", 'X', Item.redstone, 'Y', Block.glowStone);


    }


    {

        /*NightLight Detector BLOCK*/
        NightlightBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/images/night_detector.png");/* /folderwhereimagewillbe/nameofblock.png */
        ModLoader.registerBlock(NightlightBlock);
        NightlightBlock.setBlockName("Night Detector");
        ModLoader.addName(NightlightBlock, "Night Detector"); /* In game name*/
        ModLoader.addRecipe(new ItemStack(NightlightBlock), "XXX", "XYX", "XXX", 'X', Item.redstone, 'Y', mod_retrorevisions.DaylightBlock);


    }

    {

        /*CHIMNEY BLOCK*/
        ChimneyBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/images/chimney_block.png");/* /folderwhereimagewillbe/nameofblock.png */
        ModLoader.registerBlock(ChimneyBlock);
        ModLoader.addName(ChimneyBlock, "Chimney"); /* In game name*/
        ModLoader.addRecipe(new ItemStack(ChimneyBlock), "XXX", "XYX", "XXX", 'X', Item.coal, 'Y', Item.redstone);


    }

    {
        /*IRON BOAT ENTITY*/
        ModLoader.registerEntityID(EntityIronBoat.class, "IronBoat", ModLoader.getUniqueEntityId());
        // Register boat textures


    }

    /*IRON BOAT BLOCK*/

    {

        ItemIronBoat.iconIndex = ModLoader.addOverride("/gui/items.png" , "/images/iron_boat_item.png");
        ModLoader.addName(ItemIronBoat, "Iron Boat");
        ModLoader.addRecipe(new ItemStack(ItemIronBoat), "XXX", "XYX", "XXX", 'X', Item.ingotIron, 'Y', Item.boat);


    }

    {

        /*REDSTONE STAIRS BLOCK*/

        ModLoader.registerBlock(Redstonestairs);
        ModLoader.addName(Redstonestairs, "Redstone Stairs"); /* In game name*/
        ModLoader.addRecipe(new ItemStack(Redstonestairs), "X  ", "XX ", "YYY", 'X', Item.ingotIron, 'Y', Item.redstone);


    }



    @Override
    public void addRenderer(Map var1)
    {
        var1.put(EntityIronBoat.class, new RenderIronBoat());
    }




    public String getVersion()
    {
        return "1.2.5";
    }




}
