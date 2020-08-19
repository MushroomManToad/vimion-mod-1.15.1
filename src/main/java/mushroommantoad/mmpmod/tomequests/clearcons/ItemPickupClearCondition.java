package mushroommantoad.mmpmod.tomequests.clearcons;

import net.minecraft.item.Item;

public class ItemPickupClearCondition extends ClearCondition
{
	Item[] items;
	
	public ItemPickupClearCondition(Item...items)
	{
		this.items = items;
	}
	
	public Item[] getItems()
	{
		return this.items;
	}
}
