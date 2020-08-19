package mushroommantoad.mmpmod.tomequests.clearcons;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;

public class ItemUsedOnBlockClearCondition extends ClearCondition
{
	Item[] stacks;
	BlockState block;
	
	public ItemUsedOnBlockClearCondition(BlockState block, Item...stacks)
	{
		this.stacks = stacks;
		this.block = block;
	}
	
	public Item[] getItems()
	{
		return this.stacks;
	}
	
	public BlockState getBlockState()
	{
		return this.block;
	}
}
