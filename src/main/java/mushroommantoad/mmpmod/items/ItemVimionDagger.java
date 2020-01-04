package mushroommantoad.mmpmod.items;

import java.util.Random;

import mushroommantoad.mmpmod.init.ModItems;
import mushroommantoad.mmpmod.util.VTranslate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class ItemVimionDagger extends SwordItem
{

	public ItemVimionDagger(IItemTier tier, int attackDamage, float attackSpeed, Properties builder) 
	{
		super(tier, attackDamage, attackSpeed, builder);
	}
	
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) 
	{
		if(!attacker.getEntityWorld().isRemote)
		if(!target.isAlive())
		{
			if(Math.random() < 0.1)
			{
				
				ItemEntity drop = null;
				if(target instanceof SheepEntity) drop = new ItemEntity(attacker.getEntityWorld(), VTranslate.getEntityX(target), VTranslate.getEntityY(target), VTranslate.getEntityZ(target), new ItemStack(ModItems.sheep_spirit, 1));
				if(target instanceof CowEntity) drop = new ItemEntity(attacker.getEntityWorld(), VTranslate.getEntityX(target), VTranslate.getEntityY(target), VTranslate.getEntityZ(target), new ItemStack(ModItems.cow_spirit, 1));
				if(target instanceof PigEntity) drop = new ItemEntity(attacker.getEntityWorld(), VTranslate.getEntityX(target), VTranslate.getEntityY(target), VTranslate.getEntityZ(target), new ItemStack(ModItems.pig_spirit, 1));
				if(target instanceof RabbitEntity) drop = new ItemEntity(attacker.getEntityWorld(), VTranslate.getEntityX(target), VTranslate.getEntityY(target), VTranslate.getEntityZ(target), new ItemStack(ModItems.rabbit_spirit, 1));
				if(target instanceof ChickenEntity) drop = new ItemEntity(attacker.getEntityWorld(), VTranslate.getEntityX(target), VTranslate.getEntityY(target), VTranslate.getEntityZ(target), new ItemStack(ModItems.chicken_spirit, 1));
				
				
				if(drop != null) 
				{
					attacker.getEntityWorld().addEntity(drop);
					stack.attemptDamageItem(25, new Random(), null);
					if(stack.getMaxDamage() - stack.getDamage() < 0)
					{
					      stack.damageItem(2, attacker, (func) -> {
					    	  func.sendBreakAnimation(EquipmentSlotType.MAINHAND);
					       });
					      
					      stack.shrink(1);
					}
				}
			}
		}
		return super.hitEntity(stack, target, attacker);
	}
}
