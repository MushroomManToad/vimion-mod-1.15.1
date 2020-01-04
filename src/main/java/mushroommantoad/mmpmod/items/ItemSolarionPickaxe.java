package mushroommantoad.mmpmod.items;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import mushroommantoad.mmpmod.util.VTranslate;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemSolarionPickaxe extends PickaxeItem
{
	public ItemSolarionPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) 
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
			if(!nbt.getBoolean("active")) nbt.putBoolean("active", true);
			else
			{
				nbt.putBoolean("active", false);
			}
		}
		else
		{
			nbt.putBoolean("active", false);
		}
		if(nbt.contains("charge"))
		{
			if(nbt.getInt("charge") <= 0)
			{
				nbt.putBoolean("active", false);
			}
		}
		else
		{
			nbt.putBoolean("active", false);
			nbt.putInt("charge", 0);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) 
	{
		return slotChanged || !oldStack.getItem().equals(newStack.getItem());
	}

	@Override
	public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) 
	{
		return shouldCauseReequipAnimation(oldStack, newStack, false);
	}
	
	@Override
	public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack)
    {
        return oldStack.getItem().equals(newStack.getItem());
    }
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
		
		CompoundNBT nbt = stack.getTag();
		if(nbt == null) nbt = new CompoundNBT();
		
		if(worldIn.isRemote)
		{
			if(nbt.getBoolean("active") && (nbt.getInt("tick") == 19 || nbt.getInt("tick") == 9))
			{
				int r = 0;
				if(nbt.getInt("charge") >= 1599) r = 8;
				else r = 1 + (int) (nbt.getInt("charge") / 200);
				BlockPos playerPos = entityIn.getPosition().add(0, 1, 0);
				if(getClosestOre(getOreBlocks(worldIn, entityIn.getPosition(), r), playerPos) != null)
				{
					BlockPos pos = getClosestOre(getOreBlocks(worldIn, entityIn.getPosition(), r), playerPos);
					double x = VTranslate.getEntityX(entityIn);
					double y = VTranslate.getEntityY(entityIn) + 1;
					double z = VTranslate.getEntityZ(entityIn);
					double x2 = pos.getX() + 0.5;
					double y2 = pos.getY() + 0.5;
					double z2 = pos.getZ() + 0.5;
					double xInterval = (x2 - x) / 16;
					double yInterval = (y2 - y) / 16;
					double zInterval = (z2 - z) / 16;
					for(int i = 0; i < 16; i++)
					{
						worldIn.addParticle(ParticleTypes.FLAME, x + (xInterval * i), y + (yInterval * i), z + (zInterval * i), 0, 0, 0);
					}
				}
			}
		}

		
		if(!worldIn.isRemote)
		{
			if(!nbt.contains("active")) nbt.putBoolean("active", false);
			if(!nbt.contains("charge")) nbt.putInt("charge", 0);
			if(!nbt.contains("tick")) {nbt.putInt("tick", 0); stack.setTag(nbt); /*System.out.println(1);*/}
			
			boolean active = nbt.getBoolean("active");
			int charge = nbt.getInt("charge");
			if(nbt.getBoolean("active"))
			{
				if(charge > 0)
				{
					charge--;
				}
				else
				{
					active = false;
				}
				if(entityIn instanceof PlayerEntity)
				{
					PlayerEntity playerIn = (PlayerEntity) entityIn;
					playerIn.sendStatusMessage(new StringTextComponent("Charge: " + TextFormatting.YELLOW + nbt.getInt("charge") + "/2500"), true);
				}
			}
			else
			{
				if(charge < 2500)
				{
					if(canSeeSky(worldIn, entityIn)) charge++;
				}
			}
			
			nbt.putBoolean("active", active);
			nbt.putInt("charge", charge);
			
			if(nbt.getInt("tick") >= 20)
			{
				nbt.putInt("tick", 0);
			}
			else {int tick = Integer.valueOf(nbt.getInt("tick") + 1); nbt.putInt("tick", tick);}
		}
		
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	public boolean canSeeSky(World worldIn, Entity entityIn)
	{
		BlockPos.Mutable pos1 = new BlockPos.Mutable(entityIn.getPosition().getX(), entityIn.getPosition().getY(), entityIn.getPosition().getZ());
		if(!worldIn.isRemote && !worldIn.isDaytime()) return false;
		for(int i = (int) entityIn.getPosition().getY(); i <= 256; i++)
		{
			if(!(worldIn.getBlockState(pos1).getBlock() instanceof AirBlock)) return false;
			pos1.setPos(pos1.getX(), pos1.getY() + 1, pos1.getZ());
		}
		return true;
	}
	
	public ArrayList<BlockPos> getOreBlocks(World worldIn, BlockPos pos, int r)
	{
		ArrayList<BlockPos> localOres = new ArrayList<>();
	    int startX = pos.getX();
	    int startY = pos.getY();
	    int startZ = pos.getZ();
	    Block b;
        BlockPos.Mutable pooledMutableBlockPos = new BlockPos.Mutable();
	    for(int X = -r; X <= r; X++ )
	    {
	        for(int Y = -r; Y <= r; Y++ )
	        {
	            for(int Z = -r; Z <= r; Z++ )
	            {
	                if(Math.sqrt((X * X) + (Y * Y) + (Z * Z)) <= r)
	                {
	                	pooledMutableBlockPos.setPos(startX + X, startY + Y, startZ + Z);
	                	b = worldIn.getBlockState(pooledMutableBlockPos).getBlock();
	                	if(
	                		b instanceof AirBlock || 
	                		b == Blocks.STONE || 
	                		b == Blocks.GRANITE || 
	                		b == Blocks.DIORITE || 
	                		b == Blocks.ANDESITE || 
	                		b == Blocks.DIRT ||
	                		b == Blocks.GRAVEL ||
	                		b == Blocks.SAND ||
	                		b == Blocks.SANDSTONE ||
	    	                b == Blocks.RED_SAND ||
	    	                b == Blocks.RED_SANDSTONE ||
	                		b == Blocks.BEDROCK ||
	                		b instanceof FlowerBlock ||
	                		b == Blocks.GRASS ||
	                		b == Blocks.TALL_GRASS ||
	                		b == Blocks.GRASS_BLOCK ||
	                		b == Blocks.GRASS_PATH ||
	                		b == Blocks.DEAD_BUSH ||
	                		b == Blocks.CACTUS ||
	                		b instanceof LogBlock ||
	                		b == Blocks.LILY_PAD ||
	                		b == Blocks.KELP_PLANT ||
	                		b == Blocks.SUGAR_CANE ||
	                		b == Blocks.KELP ||
	                		b == Blocks.SEAGRASS ||
	                		b == Blocks.TALL_SEAGRASS ||
	                		b == Blocks.NETHERRACK ||
	                		b == Blocks.END_STONE ||
	                		b instanceof LeavesBlock ||
	                		b instanceof FlowingFluidBlock) {} 
	                	else localOres.add(pooledMutableBlockPos.toImmutable());
	                }
	            }
	        }
	    }
	    return localOres;
	}
	
	public BlockPos getClosestOre(List<BlockPos> pos, BlockPos playerPos)
	{
		if(pos.size() <= 0) return null;
		else if(pos.size() == 1) return pos.get(0);
		else
		{
			BlockPos pos1 = pos.get(0);
			for(BlockPos pos2 : pos)
			{
				if(getDistance(pos2, playerPos) < getDistance(pos1, playerPos))
				{
					pos1 = pos2;
				}
			}
			return pos1;
		}
	}
	
	public double getDistance(BlockPos block, BlockPos player)
	{
		return Math.sqrt(Math.pow((block.getX() - player.getX()), 2) + Math.pow((block.getY() - player.getY()), 2) + Math.pow((block.getZ() - player.getZ()), 2));
	}
	
   @OnlyIn(Dist.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
   {
	   CompoundNBT nbt = stack.getTag();
	   if(nbt == null) nbt = new CompoundNBT();
	   if(nbt.contains("charge"))
	   {
		   tooltip.add(new StringTextComponent("Charge: " + nbt.getInt("charge") + "/2500").applyTextStyle(TextFormatting.YELLOW));
	   }
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
