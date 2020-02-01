package mushroommantoad.mmpmod.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemNecrioniteHoe extends HoeItem
{
	Block[] tillable = {Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.GRASS_PATH};
	
	public ItemNecrioniteHoe(IItemTier tier, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackSpeedIn, builder);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		World worldIn = context.getWorld();
		ItemStack item = context.getItem();
		CompoundNBT nbt = item.getTag();
		if(nbt == null)
		{
			nbt = new CompoundNBT();
			nbt.putBoolean("active", false);
		}
		if(!worldIn.isRemote && nbt.getBoolean("active"))
		{
			BlockPos pos = context.getPos();
			BlockState stateIn = worldIn.getBlockState(pos);
			Block block = stateIn.getBlock();
			if(block == tillable[0] || block == tillable[1] || block == tillable[2] || block == Blocks.COARSE_DIRT)
			{
				PlayerEntity playerIn = context.getPlayer();
				boolean flag = true;
				BlockPos.Mutable pos1 = new BlockPos.Mutable(pos);
				for(int i = 0; i < 15; i++)
				{
					if(flag)
					{
						pos1 = pos1.move(playerIn.getHorizontalFacing());
						boolean flag1 = false;
						for(Block b : tillable)
						{
							if(worldIn.getBlockState(pos1).getBlock() == b && worldIn.getBlockState(pos1.add(0, 1, 0)).isAir()) flag1 = true;
						}
						if(flag1)
						{
							worldIn.setBlockState(pos1, Blocks.FARMLAND.getDefaultState());
			                context.getItem().damageItem(1, playerIn, (p_220043_1_) -> {
			                	p_220043_1_.sendBreakAnimation(context.getHand());
			                });
			                worldIn.playSound(playerIn, pos1, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
						else if(worldIn.getBlockState(pos1).getBlock() == Blocks.COARSE_DIRT)
						{
							worldIn.setBlockState(pos1, Blocks.DIRT.getDefaultState());
			                context.getItem().damageItem(1, playerIn, (p_220043_1_) -> {
			                	p_220043_1_.sendBreakAnimation(context.getHand());
			                });
			                worldIn.playSound(playerIn, pos1, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
						else
						{
							flag = false;
						}
						if(item.getDamage() >= item.getMaxDamage()) flag = false;
					}
				}
			}
		}
		
		return super.onItemUse(context);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack stack = playerIn.getHeldItem(handIn);
		CompoundNBT nbt = stack.getTag(); 
		if(playerIn.isCrouching())
		{
			if(nbt == null) nbt = new CompoundNBT();
			if(nbt.contains("active"))
			{
				if(!nbt.getBoolean("active")) {nbt.putBoolean("active", true); nbt.putString("target", "");}
				else nbt.putBoolean("active", false);
			}
			else
			{
				nbt.putBoolean("active", true);
				nbt.putString("target", "");
			}
		}
		stack.setTag(nbt);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) 
	{
		CompoundNBT nbt = stack.getTag();
		if(nbt == null) nbt = new CompoundNBT();
		if(nbt.contains("active")) return nbt.getBoolean("active");
		else return false;
	}
}
