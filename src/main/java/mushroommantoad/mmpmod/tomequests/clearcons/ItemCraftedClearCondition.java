package mushroommantoad.mmpmod.tomequests.clearcons;

import net.minecraft.item.Item;

public class ItemCraftedClearCondition extends ClearCondition
{
	Item result;
	
	public ItemCraftedClearCondition(Item result)
	{
		this.result = result;
	}
	
	public Item getResult()
	{
		return this.result;
	}
}
