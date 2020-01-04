package mushroommantoad.mmpmod.items;

import mushroommantoad.mmpmod.blocks.VimionGrassBlock;
import mushroommantoad.mmpmod.init.ModBlocks;
import mushroommantoad.mmpmod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemVimionShard extends Item
{
	public ItemVimionShard(Properties properties) 
	{
		super(properties);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		BlockPos pos = context.getPos();
		World worldIn = context.getWorld();
		ItemStack stack = context.getItem();
		BlockState state = worldIn.getBlockState(pos);
		if(state.getBlock() instanceof VimionGrassBlock || state.getBlock() == Blocks.GRASS_BLOCK)
		{
			Block type = ModBlocks.vimionic_grass_block;
			if(stack.getItem() == ModItems.necrion_shard) type = ModBlocks.necrionic_grass_block;
			if(stack.getItem() == ModItems.solarion_shard) type = ModBlocks.solarionic_grass_block;
			if(stack.getItem() == ModItems.nihilion_shard) type = ModBlocks.nihilionic_grass_block;
			if(stack.getItem() == ModItems.expion_shard) type = ModBlocks.expionic_grass_block;
			if(state.getBlock() != type)
			{
				if(!worldIn.isRemote)
				{
					stack.shrink(1);
					worldIn.setBlockState(pos, type.getDefaultState());
				}
				if(worldIn.isRemote)
				{
					worldIn.playSound(context.getPlayer(), context.getPos(), SoundEvents.BLOCK_GRASS_FALL, SoundCategory.BLOCKS, 1, -1);
				}
			}
		}
		return super.onItemUse(context);
	}
}
