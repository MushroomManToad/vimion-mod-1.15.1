package mushroommantoad.mmpmod.items;

import mushroommantoad.mmpmod.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;

public class ItemSoulHarvester extends SwordItem
{
	public ItemSoulHarvester(IItemTier tier, int attackDamage, float attackSpeed, Properties builder) 
	{
		super(tier, attackDamage, attackSpeed, builder);
	}
	
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) 
	{
		if(!attacker.getEntityWorld().isRemote)
		if(!target.isAlive() && !target.isEntityUndead())
		{
			System.out.println(target.getEntityString());
			ItemStack droppedStack = new ItemStack(ModItems.soul, 1);
			CompoundNBT nbt = droppedStack.getTag();
			if(nbt == null) nbt = new CompoundNBT();
			nbt.putString("entityIn", target.getEntityString());
			droppedStack.setTag(nbt);
			if(droppedStack.getItem() instanceof ItemSoul)
			{
				ItemSoul soul = (ItemSoul) droppedStack.getItem();
				soul.updateName(droppedStack);
			}
			ItemEntity drop = new ItemEntity(attacker.getEntityWorld(), target.getPosX(), target.getPosY(), target.getPosZ(), droppedStack);
			
			
			if(drop != null) {attacker.getEntityWorld().addEntity(drop);}
		}
		return super.hitEntity(stack, target, attacker);
	}
}
