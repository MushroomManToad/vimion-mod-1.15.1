package mushroommantoad.mmpmod.entities.boss.goals.solarionic_abomination;

import mushroommantoad.mmpmod.entities.boss.solarionic_abomination.SolarionicAbominationEntity;
import mushroommantoad.mmpmod.network.SToCParticleAtPosPacket;
import mushroommantoad.mmpmod.network.VimionPacketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class SunRiseGoal extends Goal
{
	SolarionicAbominationEntity summoner;
	
	public SunRiseGoal(SolarionicAbominationEntity summoner)
	{
		this.summoner = summoner;
	}
	
	@Override
	public boolean shouldExecute() 
	{
		LivingEntity entity = this.summoner.getAttackTarget();
		if(entity != null && summoner.getHealth() <= (summoner.getMaxHealth() / 3) && !summoner.isLunarionic())
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void tick() 
	{
		// Not running on the client??
		if(!this.summoner.world.isRemote)
		{
			//LivingEntity target = this.summoner.getAttackTarget();
			World world = this.summoner.world;
			if(this.summoner.getSunriseBlastCooldown() == 70)
			{
				AxisAlignedBB aabb = new AxisAlignedBB(this.summoner.getPosX() + 2, this.summoner.getPosY() + 2, this.summoner.getPosZ() + 2, this.summoner.getPosX() - 2, this.summoner.getPosY() - 2, this.summoner.getPosZ() - 2);
				for(ServerPlayerEntity playerIn : world.getEntitiesWithinAABB(ServerPlayerEntity.class, aabb.grow(30))) 
				{
					VimionPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> playerIn), new SToCParticleAtPosPacket(this.summoner.getPosX(), this.summoner.getPosY(), this.summoner.getPosZ(), 5));
				}
			}
			if (this.summoner.getSunriseBlastCooldown() < 70 && this.summoner.getSunriseBlastCooldown() > 10) {
				AxisAlignedBB aabb = new AxisAlignedBB(this.summoner.getPosX() + 2, this.summoner.getPosY() + 2, this.summoner.getPosZ() + 2, this.summoner.getPosX() - 2, this.summoner.getPosY() - 2, this.summoner.getPosZ() - 2);
				for(ServerPlayerEntity playerIn : world.getEntitiesWithinAABB(ServerPlayerEntity.class, aabb.grow(30))) 
				{
					VimionPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> playerIn), new SToCParticleAtPosPacket(this.summoner.getPosX(), this.summoner.getPosY(), this.summoner.getPosZ(), 4, this.summoner.getStellarType()));
				}
				
				this.summoner.setMotion(this.summoner.getMotion().x, 0.1, this.summoner.getMotion().z);
			}
			if(this.summoner.getSunriseBlastCooldown() == 10)
			{
				this.summoner.setInvulnerable(true);
				world.createExplosion((Entity)null, this.summoner.getPosX(), this.summoner.getPosY() + 2.0f, this.summoner.getPosZ(), (float)8, world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) ? true : false, (world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE));
				this.summoner.setInvulnerable(false);
			}
		}
	}
}
