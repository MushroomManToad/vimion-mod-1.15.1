package mushroommantoad.mmpmod.entities.boss.goals.solarionic_abomination;

import mushroommantoad.mmpmod.entities.boss.solarionic_abomination.SolarionicAbominationEntity;
import mushroommantoad.mmpmod.entities.boss.solarionic_abomination.solar_blast.SolarBlastEntity;
import mushroommantoad.mmpmod.network.SToCParticleAtPosPacket;
import mushroommantoad.mmpmod.network.VimionPacketHandler;
import mushroommantoad.mmpmod.util.MushroomsUtil;
import net.minecraft.block.AirBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.network.PacketDistributor;

public class SummonSolarBlastAtGoal extends Goal
{
	SolarionicAbominationEntity summoner;
	
	public SummonSolarBlastAtGoal(SolarionicAbominationEntity summoner)
	{
		this.summoner = summoner;
	}
	
	@Override
	public boolean shouldExecute() 
	{
		LivingEntity entity = this.summoner.getAttackTarget();
		if(entity != null && summoner.getSolarBlastCooldown() == 1 && !summoner.isLunarionic())
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
			if(this.summoner.getSolarBlastCooldown() == 1)
			{
				World worldIn = this.summoner.world;
				AxisAlignedBB aabb = new AxisAlignedBB(this.summoner.getPosX() + 2, this.summoner.getPosY() + 2, this.summoner.getPosZ() + 2, this.summoner.getPosX() - 2, this.summoner.getPosY() - 2, this.summoner.getPosZ() - 2);
				for(ServerPlayerEntity playerIn : worldIn.getEntitiesWithinAABB(ServerPlayerEntity.class, aabb.grow(30))) 
				{
					VimionPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> playerIn), new SToCParticleAtPosPacket(this.summoner.getPosX(), this.summoner.getPosY(), this.summoner.getPosZ(), 6));
				}
				SolarBlastEntity newSolarBlast = new SolarBlastEntity(worldIn, this.summoner.getPosition().getY() + 2, getFireBlastTier(), this.summoner.getStellarType());
				LivingEntity entity = summoner.getAttackTarget();
				double x = entity.getPosX() + Math.random() * MushroomsUtil.StaticMinusPlus() * 2;
				double z = entity.getPosZ() + Math.random() * 2 * MushroomsUtil.StaticMinusPlus();
				newSolarBlast.setPosition(x, getTopBlock(x, z) + 16, z);
				worldIn.addEntity(newSolarBlast);
				//summoner.playSound(ModSoundEvents.absorption_pillar_summon, 1, 0);
			}
		}
	}
	
	public int getTopBlock(double x, double z)
	{
		if(this.summoner.world.getDimension().getType() == DimensionType.OVERWORLD)
		{
			BlockPos.Mutable pos = new BlockPos.Mutable(0, 0, 0);
			for(int i = 255; i > 0; i--)
			{
				for(int a = -1; a < 2; a++)
				{
					for(int b = -1; b < 2; b++)
					{
						pos.setX((int) x + a);
						pos.setY(i);
						pos.setZ((int) z + b);
						if(!(summoner.world.getBlockState(pos).getBlock() instanceof AirBlock))
						{
							return i;
						}
					}
				}
			}
		}
		else
		{
			return this.summoner.getAttackTarget().getPosition().getY() + 16;
		}
		return 1;
	}
	
	public int getFireBlastTier()
	{
		if(summoner.getHealth() < (summoner.getMaxHealth() / 3)) return 2;
		if(summoner.getHealth() < 2 * (summoner.getMaxHealth() / 3)) return 1;
		return 0;
	}
}