package mushroommantoad.mmpmod.items;

import java.util.Random;

import mushroommantoad.mmpmod.init.ModBlocks;
import mushroommantoad.mmpmod.util.MushroomsMathUtil;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAdvancedGeologicPhaser extends Item
{
	public ItemAdvancedGeologicPhaser(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context)
	{
		World w = context.getWorld();
		BlockPos p = context.getPos();
		Block b = w.getBlockState(p).getBlock();
		ItemStack gp = context.getItem();
		if(w.isRemote)
		{
			if(b == ModBlocks.concealed_vimionite_ore || b == ModBlocks.concealed_necrionite_ore || b == ModBlocks.concealed_solarionite_ore || b == ModBlocks.concealed_nihilionite_ore || b == ModBlocks.concealed_expionite_ore)
			{
				for(int i = 0; i < 100; i++)
				{
					double d2 = Math.random() * MushroomsMathUtil.StaticMinusPlus();
					double d3 = Math.random() * MushroomsMathUtil.StaticMinusPlus();
					double d4 = Math.random() * MushroomsMathUtil.StaticMinusPlus();
					w.addParticle(ParticleTypes.ENCHANTED_HIT, (p.getX() + 0.5), (p.getY() + 0.5), (p.getZ() + 0.5), d2, d3, d4);
				}
				w.playSound(context.getPlayer(), p, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1, -1);
				w.playSound(context.getPlayer(), p, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1, -1);
			}
		}
		if(!w.isRemote)
		{
			if(b == ModBlocks.concealed_vimionite_ore || b == ModBlocks.concealed_necrionite_ore || b == ModBlocks.concealed_solarionite_ore || b == ModBlocks.concealed_nihilionite_ore || b == ModBlocks.concealed_expionite_ore)
			{
				if(b == ModBlocks.concealed_vimionite_ore) w.setBlockState(p, ModBlocks.vimionite_ore.getDefaultState());
				if(b == ModBlocks.concealed_necrionite_ore) w.setBlockState(p, ModBlocks.necrionite_ore.getDefaultState());
				if(b == ModBlocks.concealed_solarionite_ore) w.setBlockState(p, ModBlocks.solarionite_ore.getDefaultState());
				if(b == ModBlocks.concealed_nihilionite_ore) w.setBlockState(p, ModBlocks.nihilionite_ore.getDefaultState());
				if(b == ModBlocks.concealed_expionite_ore) w.setBlockState(p, ModBlocks.expionite_ore.getDefaultState());
				
				gp.attemptDamageItem(1, new Random(), null);
				if((gp.getMaxDamage() - gp.getDamage()) < 0)
				{
					gp.damageItem(2, context.getPlayer(), (p_220039_0_) -> {
				          p_220039_0_.sendBreakAnimation(context.getHand());
				       });
				      
					gp.shrink(1);
				}
				
				return ActionResultType.SUCCESS;
			}
			else 
			{
				if(new Random().nextInt(10) < 1) gp.attemptDamageItem(1, new Random(), null);
				if(gp.getMaxDamage() - gp.getDamage() < 0)
				{
					gp.damageItem(2, context.getPlayer(), (p_220039_0_) -> {
				          p_220039_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
				       });
				      
					gp.shrink(1);
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
