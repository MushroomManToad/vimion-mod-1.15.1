package mushroommantoad.mmpmod.entities.boss.goals;

import mushroommantoad.mmpmod.entities.boss.expionic_abomination.ExpionicAbominationEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

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
			if(this.summoner.getDisarmCooldown() == 1)
			{
				if(this.summoner.getAttackTarget() instanceof PlayerEntity)
				{
					PlayerEntity playerTarget = (PlayerEntity) this.summoner.getAttackTarget();
					playerTarget.getCooldownTracker().setCooldown(playerTarget.getHeldItemMainhand().getItem(), 300);
					playerTarget.clearActivePotions();
				}
			}
		}
	}
}
