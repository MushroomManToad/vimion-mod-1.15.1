package mushroommantoad.mmpmod.items;

import mushroommantoad.mmpmod.entities.boss.IVimionicEntity;
import mushroommantoad.mmpmod.entities.spectral.ISpectralEntity;
import mushroommantoad.mmpmod.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class ItemSoulHarvester extends SwordItem
{
	public ItemSoulHarvester(IItemTier tier, int attackDamage, float attackSpeed, Properties builder) 
	{
		super(tier, attackDamage, attackSpeed, builder);
	}
	
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) 
	{
		World worldIn = attacker.getEntityWorld();
		if(!worldIn.isRemote)
		if(!target.isAlive() && !target.isEntityUndead() && !(target instanceof ISpectralEntity) && !(target instanceof IVimionicEntity))
		{
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
			ItemEntity drop = new ItemEntity(worldIn, target.getPosX(), target.getPosY(), target.getPosZ(), droppedStack);
			
			
			if(drop != null) {worldIn.addEntity(drop);}
		}
		return super.hitEntity(stack, target, attacker);
	}
}
