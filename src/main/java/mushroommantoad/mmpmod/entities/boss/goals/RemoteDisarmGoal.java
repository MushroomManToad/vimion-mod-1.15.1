package mushroommantoad.mmpmod.entities.boss.goals;

import java.util.ArrayList;
import java.util.Random;

import mushroommantoad.mmpmod.entities.boss.expionic_abomination.ExpionicAbominationEntity;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import mushroommantoad.mmpmod.util.VTranslate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;

public class RemoteDisarmGoal extends Goal
{
	ExpionicAbominationEntity summoner;
	
	public RemoteDisarmGoal(ExpionicAbominationEntity summoner)
	{
		this.summoner = summoner;
	}
	
	@Override
	public boolean shouldExecute() 
	{
		LivingEntity entity = this.summoner.getAttackTarget();
		if(entity != null)
		{
			return true;
		}
		else return false;
	}
	
	
	@Override
	public void tick() 
	{
		if(!this.summoner.world.isRemote)
		{
			if(this.summoner.getDisarmCooldown() == 1 && summoner.getHealth() < 2 * (summoner.getMaxHealth() / 3) && this.summoner.getAttackTarget() instanceof PlayerEntity)
			{
				PlayerEntity playerTarget = (PlayerEntity) this.summoner.getAttackTarget();
				
				ArrayList<ItemStack> hotbar = new ArrayList<>();
				
				for(int i = 0; i <= 8; i++)	{hotbar.add(playerTarget.inventory.getStackInSlot(i));}
				hotbar.add(playerTarget.getItemStackFromSlot(EquipmentSlotType.OFFHAND));
				Random rand = new Random();
				for(int i = 0; i < hotbar.size(); i++)
				{
					int randomIndexToSwap = rand.nextInt(hotbar.size());
					ItemStack temp = hotbar.get(randomIndexToSwap);
					hotbar.set(randomIndexToSwap, hotbar.get(i));
					hotbar.set(i, temp);
				}					
				for(int i = 0; i <= 8; i++)	{playerTarget.inventory.setInventorySlotContents(i, hotbar.get(i));}
				playerTarget.setItemStackToSlot(EquipmentSlotType.OFFHAND, hotbar.get(9));
			}
			
			if((this.summoner.getDisarmCooldown() == 1 || this.summoner.getDisarmCooldown() == 161) && summoner.getHealth() < 2 * (summoner.getMaxHealth() / 3))
			{
				LivingEntity target = this.summoner.getAttackTarget();
				if(!target.getActivePotionEffects().isEmpty() ||this.summoner.getDisarmCooldown() == 1)
				{
					this.summoner.world.playSound((PlayerEntity)null, VTranslate.getEntityX(target), VTranslate.getEntityY(target), VTranslate.getEntityZ(target), ModSoundEvents.expionic_abomination_teleport, SoundCategory.HOSTILE, 1.0F, -1.0F);
				}
				if(target.isPotionActive(Effects.LEVITATION))
				{
					int duration = target.getActivePotionEffect(Effects.LEVITATION).getDuration();
					int amplifier = target.getActivePotionEffect(Effects.LEVITATION).getAmplifier();
					target.clearActivePotions();
					target.addPotionEffect(new EffectInstance(Effects.LEVITATION, duration, amplifier));
				}
				else
				{
					target.clearActivePotions();
				}
			}
		}
	}
}
