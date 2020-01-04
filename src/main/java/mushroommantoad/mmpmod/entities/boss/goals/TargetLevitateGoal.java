package mushroommantoad.mmpmod.entities.boss.goals;

import mushroommantoad.mmpmod.entities.boss.expionic_abomination.ExpionicAbominationEntity;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire.EntityAbsorptionSpire;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import mushroommantoad.mmpmod.util.MushroomsMathUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

public class TargetLevitateGoal extends Goal
{
	ExpionicAbominationEntity summoner;
	
	public TargetLevitateGoal(ExpionicAbominationEntity summoner)
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
			if(this.summoner.getTPCooldown() == 1)
			{
				// WHAT HAPPENS GOES HERE
			}
		}
	}
}
