package mushroommantoad.mmpmod.entities.boss.goals;

import mushroommantoad.mmpmod.entities.boss.expionic_abomination.ExpionicAbominationEntity;
import mushroommantoad.mmpmod.util.VTranslate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.DamageSource;

public class BurstTeleportGoal extends Goal
{
	ExpionicAbominationEntity summoner;
	
	DamageSource expionicTeleport = new DamageSource("expionicTeleport").setDamageIsAbsolute();
	
	public BurstTeleportGoal(ExpionicAbominationEntity summoner)
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
			if(this.summoner.getTPCooldown() == 11)
			{
				LivingEntity entity = this.summoner.getAttackTarget();
				if(summoner.teleportTo(VTranslate.getEntityX(entity), VTranslate.getEntityY(entity), VTranslate.getEntityZ(entity)))
				{
					entity.attackEntityFrom(expionicTeleport, 7);
				}
			}
			if(this.summoner.getTPCooldown() == 1)
			{
				LivingEntity entity = this.summoner.getAttackTarget();
				if(summoner.attemptRandomTP(16)) {entity.attackEntityFrom(expionicTeleport, 5);}
			}
		}
	}
}
