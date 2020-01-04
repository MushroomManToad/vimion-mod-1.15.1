package mushroommantoad.mmpmod.items;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraftforge.common.ToolType;

public class ItemExpioniteShovel extends ShovelItem
{
	
	public ItemExpioniteShovel(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) 
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack stack = playerIn.getHeldItem(handIn);
		CompoundNBT nbt = stack.getTag(); 
		if(nbt == null) nbt = new CompoundNBT();
		if(nbt.contains("active"))
		{
			if(!nbt.getBoolean("active")) {nbt.putBoolean("active", true);}
			else nbt.putBoolean("active", false);
		}
		else
		{
			nbt.putBoolean("active", true);
		}
		stack.setTag(nbt);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) 
	{
		if(entityLiving instanceof PlayerEntity && !worldIn.isRemote)
		{
			PlayerEntity playerIn = (PlayerEntity) entityLiving;
			if(playerIn.canHarvestBlock(state))
			{
				CompoundNBT nbt = stack.getTag();
				if(nbt == null) nbt = new CompoundNBT();
				if(nbt.contains("active"))
				{
					if(nbt.getBoolean("active") && state.getHarvestTool() == ToolType.SHOVEL)
					{
						LootContext.Builder builder = new LootContext.Builder((ServerWorld) worldIn).withParameter(LootParameters.POSITION, pos).withParameter(LootParameters.TOOL, stack);
						for(int i = 0; i < 63; i++)
						{
							for(ItemStack itemStack : state.getDrops(builder))
							{
								ItemEntity item = new ItemEntity(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
								worldIn.addEntity(item);
							}
							stack.attemptDamageItem(1, new Random(), null);
						}
					}
				}
				stack.setTag(nbt);
			}
		}
		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
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
