package mushroommantoad.mmpmod.items;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemNihilionAxe extends AxeItem
{
	public ItemNihilionAxe(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) 
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
			if(!nbt.getBoolean("active")) {nbt.putBoolean("active", true); nbt.putString("target", "");}
			else nbt.putBoolean("active", false);
		}
		else
		{
			nbt.putBoolean("active", true);
			nbt.putString("target", "");
		}
		stack.setTag(nbt);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
		if(!worldIn.isRemote)
		{
			
			CompoundNBT nbt = stack.getTag();
			if(nbt == null) nbt = new CompoundNBT();
			if(nbt.contains("active") && nbt.contains("target") && nbt.contains("xTarget") && nbt.contains("yTarget") && nbt.contains("zTarget"))
			{
				if(nbt.getBoolean("active") && nbt.getString("target") != "")
				{
					BlockPos.Mutable pos1 = new BlockPos.Mutable(0, 0, 0);
					
					ArrayList<Integer> xP = new ArrayList<>();
					ArrayList<Integer> yP = new ArrayList<>();
					ArrayList<Integer> zP = new ArrayList<>();
					
					int[] xT = nbt.getIntArray("xTarget");
					int[] yT = nbt.getIntArray("yTarget");
					int[] zT = nbt.getIntArray("zTarget");
					
					if(xT.length == yT.length && xT.length == zT.length)
					{
						for(int i = 0; i < xT.length; i++)
						{
							pos1.setPos(xT[i], yT[i], zT[i]);
							if(worldIn.getBlockState(pos1).getBlock().getTranslationKey() == nbt.getString("target"))
							{
								if(entityIn instanceof PlayerEntity)
								{
									PlayerEntity playerIn = (PlayerEntity) entityIn;
									if(playerIn.canHarvestBlock(worldIn.getBlockState(pos1)))
									{
										worldIn.destroyBlock(pos1, true);
									}
									else
									{
										worldIn.setBlockState(pos1, Blocks.AIR.getDefaultState(), 3);
									}
								}
								else
								{
									worldIn.setBlockState(pos1, Blocks.AIR.getDefaultState(), 3);
								}
								if(stack.getMaxDamage() - stack.getDamage() < 0)
								{
									if(entityIn instanceof LivingEntity)
									{
									LivingEntity LE = (LivingEntity) entityIn;
								      stack.damageItem(2, LE, (p_220039_0_) -> {
								          p_220039_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
								       });
									}
									else
									{
										stack.shrink(1);
									}
								}
								else
								{
									stack.attemptDamageItem(1, new Random(), null);
								}
								
								BlockPos[] tbps = {pos1.add(1, 0, 0), pos1.add(-1, 0, 0), pos1.add(0, 1, 0), pos1.add(0, -1, 0), pos1.add(0, 0, 1), pos1.add(0, 0, -1)};
								for(int j = 0; j < tbps.length; j++)
								{
									if(nbt.contains("oXT") && nbt.contains("oYT") && nbt.contains("oZT"))
									{
										if(worldIn.getBlockState(tbps[j]).getBlock().getTranslationKey() == nbt.getString("target"))
										{
											if(	(nbt.getInt("oXT") - tbps[j].getX() <= 16 && nbt.getInt("oXT") - tbps[j].getX() >= -16) && (nbt.getInt("oZT") - tbps[j].getZ() <= 16 && nbt.getInt("oZT") - tbps[j].getZ() >= -16))
											{
												xP.add(tbps[j].getX());
												yP.add(tbps[j].getY());
												zP.add(tbps[j].getZ());
											}
										}
									}
								}
							}
						}
					}
					
					nbt.putIntArray("xTarget", xP);
					nbt.putIntArray("yTarget", yP);
					nbt.putIntArray("zTarget", zP);
					if(xP.size() == 0) nbt.putString("target", "");
					stack.setTag(nbt);
				}
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) 
	{
		if(entityLiving instanceof PlayerEntity)
		{
			PlayerEntity playerIn = (PlayerEntity) entityLiving;
			if(playerIn.canHarvestBlock(state))
			{
				CompoundNBT nbt = stack.getTag();
				if(nbt == null) nbt = new CompoundNBT();
				if(nbt.contains("active"))
				{
					if(nbt.getBoolean("active"))
					{
						nbt.putString("target", state.getBlock().getTranslationKey());
						nbt.putInt("oXT", pos.getX());
						nbt.putInt("oYT", pos.getY());
						nbt.putInt("oZT", pos.getZ());
						
						ArrayList<Integer> xP = new ArrayList<>();
						ArrayList<Integer> yP = new ArrayList<>();
						ArrayList<Integer> zP = new ArrayList<>();
						
						BlockPos[] tbps = {pos.add(1, 0, 0), pos.add(-1, 0, 0), pos.add(0, 1, 0), pos.add(0, -1, 0), pos.add(0, 0, 1), pos.add(0, 0, -1)};
						for(int j = 0; j < tbps.length; j++)
						{
							if(worldIn.getBlockState(tbps[j]).getBlock().getTranslationKey() == nbt.getString("target"))
							{
								xP.add(tbps[j].getX());
								yP.add(tbps[j].getY());
								zP.add(tbps[j].getZ());
							}
						}
						
						nbt.putIntArray("xTarget", xP);
						nbt.putIntArray("yTarget", yP);
						nbt.putIntArray("zTarget", zP);
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
