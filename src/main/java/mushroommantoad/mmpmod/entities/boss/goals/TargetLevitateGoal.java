package mushroommantoad.mmpmod.entities.boss.goals;

import mushroommantoad.mmpmod.entities.boss.expionic_abomination.ExpionicAbominationEntity;
import mushroommantoad.mmpmod.network.SToCParticleAtPosPacket;
import mushroommantoad.mmpmod.network.VimionPacketHandler;
import mushroommantoad.mmpmod.util.VTranslate;
import net.minecraft.command.arguments.EntityAnchorArgument.Type;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.network.PacketDistributor;

public class TargetLevitateGoal extends Goal
{
	ExpionicAbominationEntity summoner;
	DamageSource expionicTeleport = new DamageSource("expionicTeleport").setDamageIsAbsolute();
	
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
		if(!this.summoner.world.isRemote && this.summoner.getHealth() <= this.summoner.getMaxHealth() / 3)
		{
			LivingEntity target = this.summoner.getAttackTarget();
			if(this.summoner.getLevitateCooldown() == 31)
			{
				AxisAlignedBB aabb = new AxisAlignedBB(VTranslate.getEntityX(target) + 16, VTranslate.getEntityY(target) +16, VTranslate.getEntityZ(target) + 16, VTranslate.getEntityX(target) - 16, VTranslate.getEntityY(target) - 16, VTranslate.getEntityZ(target) - 16);
				for(LivingEntity livingentity : this.summoner.world.getEntitiesWithinAABB(LivingEntity.class, aabb))
				{
					if(!(livingentity instanceof ExpionicAbominationEntity)) livingentity.addPotionEffect(new EffectInstance(Effects.LEVITATION, 31, 4));
				}
			}
			if(this.summoner.getLevitateCooldown() < 31 && this.summoner.getLevitateCooldown() > 2)
			{
				target.lookAt(Type.EYES, this.summoner.getPositionVec());
			}
			if(this.summoner.getLevitateCooldown() < 1)
			{
				target.attackEntityFrom(expionicTeleport, 15);
				AxisAlignedBB aabb = new AxisAlignedBB(VTranslate.getEntityX(this.summoner) + 32, VTranslate.getEntityY(this.summoner) + 32, VTranslate.getEntityZ(this.summoner) + 32, VTranslate.getEntityX(this.summoner) - 32, VTranslate.getEntityY(this.summoner) - 32, VTranslate.getEntityZ(this.summoner) - 32);
				for(ServerPlayerEntity playerIn : this.summoner.world.getEntitiesWithinAABB(ServerPlayerEntity.class, aabb)) 
				{
					VimionPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> playerIn), new SToCParticleAtPosPacket(VTranslate.getEntityX(target), VTranslate.getEntityY(target), VTranslate.getEntityZ(target), 0));
				}
			}
		}
	}
}
