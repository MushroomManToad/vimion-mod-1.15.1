package mushroommantoad.mmpmod.items;

import java.util.Random;

import mushroommantoad.mmpmod.init.ModBlocks;
import mushroommantoad.mmpmod.util.MushroomsUtil;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

public class ItemGeologicPhaser extends Item
{
	public ItemGeologicPhaser(Properties properties) 
	{
		super(properties);
	}
	
	@SuppressWarnings("resource")
	public ActionResultType onItemUse(ItemUseContext context)
	{
		if(context.getWorld().isRemote)
		{
			if(context.getWorld().getBlockState(context.getPos()).getBlock() == ModBlocks.concealed_vimionite_ore)
			{
				for(int i = 0; i < 100; i++)
				{
					double d2 = Math.random() * MushroomsUtil.StaticMinusPlus();
					double d3 = Math.random() * MushroomsUtil.StaticMinusPlus();
					double d4 = Math.random() * MushroomsUtil.StaticMinusPlus();
					context.getWorld().addParticle(ParticleTypes.ENCHANTED_HIT, (context.getPos().getX() + 0.5), (context.getPos().getY() + 0.5), (context.getPos().getZ() + 0.5), d2, d3, d4);
				}
				context.getWorld().playSound(context.getPlayer(), context.getPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1, -1);
				context.getWorld().playSound(context.getPlayer(), context.getPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1, -1);
			}
		}
		if(!context.getWorld().isRemote)
		{
			if(context.getWorld().getBlockState(context.getPos()).getBlock() == ModBlocks.concealed_vimionite_ore)
			{
				context.getWorld().setBlockState(context.getPos(), ModBlocks.vimionite_ore.getDefaultState());
				context.getItem().attemptDamageItem(1, new Random(), null);
				
				if(context.getItem().getMaxDamage() - context.getItem().getDamage() < 0)
				{
					context.getItem().damageItem(2, context.getPlayer(), (p_220039_0_) -> {
				          p_220039_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
				       });
				      
					context.getItem().shrink(1);
				}
				
				return ActionResultType.SUCCESS;
			}
			else 
			{
				if(new Random().nextInt(10) < 1) 
				{
					context.getItem().attemptDamageItem(1, new Random(), null);
					if(context.getItem().getMaxDamage() - context.getItem().getDamage() < 0)
					{
						context.getItem().damageItem(2, context.getPlayer(), (p_220039_0_) -> {
					          p_220039_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
					       });
					      
						context.getItem().shrink(1);
					}
				}
				return ActionResultType.SUCCESS;
			}
		}
		else
		{
			return ActionResultType.PASS;
		}
	}
	
	@Override
	public boolean isDamageable() 
	{
		return true;
	}
}
