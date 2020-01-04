package mushroommantoad.mmpmod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemEnergizedGemstone extends Item
{
	public ItemEnergizedGemstone(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) 
	{
		return true;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) 
	{
		return 0;
	}
}
