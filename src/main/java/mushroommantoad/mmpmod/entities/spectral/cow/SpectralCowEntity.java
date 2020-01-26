package mushroommantoad.mmpmod.entities.spectral.cow;

import java.util.List;

import mushroommantoad.mmpmod.entities.spectral.ISpectralEntity;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import mushroommantoad.mmpmod.util.VTranslate;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class SpectralCowEntity extends CreatureEntity implements ISpectralEntity
{
	public SpectralCowEntity(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super(ModEntities.SPECTRAL_COW, worldIn);
	}
	
	@Override
	public void registerGoals() 
	{
	      this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
	      this.goalSelector.addGoal(2, new TemptGoal(this, 1.1D, Ingredient.fromItems(Items.WHEAT), false));
	      this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0F));
	      this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.2F);
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) 
	{
		return false;
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return ModSoundEvents.spectral_cow_say;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSoundEvents.spectral_cow_hurt;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSoundEvents.spectral_cow_death;
	}
	
	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) 
	{
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getItem() == Items.WHEAT)
		{
			if(!this.world.isRemote)
			{
				stack.shrink(1);
				List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(VTranslate.getEntityX(this) - 64, VTranslate.getEntityY(this) - 64, VTranslate.getEntityZ(this) - 64, VTranslate.getEntityX(this) + 64, VTranslate.getEntityY(this) + 64, VTranslate.getEntityZ(this) + 64));
				
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
		List<LivingEntity> entities = worldIn.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(VTranslate.getEntityX(this) - 64, VTranslate.getEntityY(this) - 64, VTranslate.getEntityZ(this) - 64, VTranslate.getEntityX(this) + 64, VTranslate.getEntityY(this) + 64, VTranslate.getEntityZ(this) + 64));
		
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
