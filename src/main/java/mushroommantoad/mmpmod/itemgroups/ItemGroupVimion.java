package mushroommantoad.mmpmod.itemgroups;

import mushroommantoad.mmpmod.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupVimion extends ItemGroup
{

	public ItemGroupVimion() 
	{
		super("vimion");
	}

	@Override
	public ItemStack createIcon() 
	{
		return new ItemStack(ModItems.vimion_gemstone);
	}
	
}
