package net.minecraft.src;

import java.util.Random;

public class BlockNetheriteBlock extends Block
{
    public BlockNetheriteBlock(int i, int j)
    {
        super(i, j, Material.glass /* Material what is is of .rock is stone*/);
    }

    public int idDropped(int i, Random random, int j)
    {
        return mod_retrorevisions.NetheriteBlock.blockID; /* that what it will drop Item.diamond <-- it will drop diamond*/
    }

    public int quantityDropped(Random random)
    {
        return 1; /* How many it will drop*/
    }
}
