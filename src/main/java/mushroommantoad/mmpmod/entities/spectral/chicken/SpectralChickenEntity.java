package mushroommantoad.mmpmod.entities.spectral.chicken;

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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class SpectralChickenEntity extends CreatureEntity implements ISpectralEntity
{
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
	
	public float wingRotation;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;
	
	public SpectralChickenEntity(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super(ModEntities.SPECTRAL_CHICKEN, worldIn);
	}
	
	@Override
	public void registerGoals() 
	{
	      this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
	      this.goalSelector.addGoal(2, new TemptGoal(this, 1.1D, TEMPTATION_ITEMS, false));
	      this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0F));
	      this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) 
	{
		return false;
	}
	
	@Override
	public void livingTick() 
	{
		super.livingTick();
		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);
		if (!this.onGround && this.wingRotDelta < 1.0F) 
		{
			this.wingRotDelta = 1.0F;
		}

		this.wingRotDelta = (float)((double)this.wingRotDelta * 0.9D);
		Vec3d vec3d = this.getMotion();
		if (!this.onGround && vec3d.y < 0.0D) 
		{
			this.setMotion(vec3d.mul(1.0D, 0.6D, 1.0D));
		}

		this.wingRotation += this.wingRotDelta * 2.0F;
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return ModSoundEvents.spectral_chicken_say;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSoundEvents.spectral_chicken_hurt;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSoundEvents.spectral_chicken_death;
	}

	@Override
	public boolean func_225503_b_(float distance, float damageMultiplier) 
	{
		return false;
	}
	
	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) 
	{
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getItem() == Items.WHEAT_SEEDS || stack.getItem() == Items.PUMPKIN_SEEDS || stack.getItem() == Items.BEETROOT_SEEDS || stack.getItem() == Items.MELON_SEEDS)
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
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) 
	{
		return 0.95F * sizeIn.height;
	}
}
