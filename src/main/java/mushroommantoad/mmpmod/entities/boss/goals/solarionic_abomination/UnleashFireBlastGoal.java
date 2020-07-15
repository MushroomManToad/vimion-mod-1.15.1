package mushroommantoad.mmpmod.entities.boss.goals.solarionic_abomination;

import mushroommantoad.mmpmod.entities.boss.solarionic_abomination.SolarionicAbominationEntity;
import mushroommantoad.mmpmod.entities.boss.solarionic_abomination.fire_blast.FireBlastEntity;
import net.minecraft.command.arguments.EntityAnchorArgument.Type;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class UnleashFireBlastGoal extends Goal
{
	SolarionicAbominationEntity summoner;
	
	public UnleashFireBlastGoal(SolarionicAbominationEntity summoner)
	{
		this.summoner = summoner;
	}
	
	@Override
	public boolean shouldExecute() 
	{
		LivingEntity entity = this.summoner.getAttackTarget();
		if(entity != null && summoner.getHealth() < 2 * (summoner.getMaxHealth() / 3) && !summoner.isLunarionic())
		{
			return true;
		}
		return false;
	}
	
	
	@Override
	public void tick() 
	{
		if(!this.summoner.world.isRemote)
		{
			int i = this.summoner.getStellarType();
			LivingEntity target = this.summoner.getAttackTarget();
			World world = this.summoner.world;
			if (this.summoner.getFireBlastCooldown() < 20) {
				this.summoner.lookAt(Type.EYES, this.summoner.getAttackTarget().getEyePosition(1.0F));
				double velocity = 1.0D;
				Vec3d vec3d = this.summoner.getLook(1.0F);
				double d2 = target.getPosX() - (this.summoner.getPosX() + vec3d.x * velocity);
				double d3 = target.getPosYHeight(0.85D) - (0.85D + this.summoner.getPosYHeight(0.85D));
				double d4 = target.getPosZ() - (this.summoner.getPosZ() + vec3d.z * velocity);
				FireBlastEntity fireblast = new FireBlastEntity(world, this.summoner, d2, d3, d4, i);
				fireblast.setPosition(this.summoner.getPosX() + vec3d.x * 1.5D, this.summoner.getPosYHeight(0.5D) + 0.5D, this.summoner.getPosZ() + vec3d.z * 1.5D);
				world.addEntity(fireblast);
			}
		}
	}
}