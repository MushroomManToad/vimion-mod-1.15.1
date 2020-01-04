package mushroommantoad.mmpmod.entities.spectral.rabbit;

import java.util.List;

import mushroommantoad.mmpmod.entities.spectral.ISpectralEntity;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class SpectralRabbitEntity extends RabbitEntity implements ISpectralEntity
{
	@SuppressWarnings("unchecked")
	public SpectralRabbitEntity(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super((EntityType<? extends RabbitEntity>) ModEntities.SPECTRAL_RABBIT, worldIn);
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) 
	{
		return false;
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return ModSoundEvents.spectral_rabbit_say;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSoundEvents.spectral_rabbit_hurt;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSoundEvents.spectral_rabbit_death;
	}
	
	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) 
	{
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getItem() == Items.CARROT || stack.getItem() == Items.GOLDEN_CARROT || stack.getItem() == Items.DANDELION)
		{
			if(!this.world.isRemote)
			{
				stack.shrink(1);
				List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(this.posX - 64, this.posY - 64, this.posZ - 64, this.posX + 64, this.posY + 64, this.posZ + 64));
				
				for(LivingEntity e : entities)
				{
					if(e.isEntityUndead())
					{
						e.setRevengeTarget(this);
						if(e instanceof PhantomEntity)
						{
							PhantomEntity phantom = (PhantomEntity) e;
							phantom.setAttackTarget(this);
						}
					}
				}
			}
		}
		return super.processInteract(player, hand);
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) 
	{
		List<LivingEntity> entities = worldIn.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(this.posX - 64, this.posY - 64, this.posZ - 64, this.posX + 64, this.posY + 64, this.posZ + 64));
		
		for(LivingEntity e : entities)
		{
			if(e.isEntityUndead())
			{
				e.setRevengeTarget(this);
				if(e instanceof PhantomEntity)
				{
					PhantomEntity phantom = (PhantomEntity) e;
					phantom.setAttackTarget(this);
				}
			}
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) 
	{
		return 0.95F * sizeIn.height;
	}
}
