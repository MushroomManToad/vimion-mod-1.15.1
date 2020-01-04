package mushroommantoad.mmpmod.entities.spectral.sheep;

import java.util.List;

import mushroommantoad.mmpmod.entities.spectral.ISpectralEntity;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.EatGrassGoal;
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
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpectralSheepEntity extends CreatureEntity implements ISpectralEntity
{
	private EatGrassGoal eatGrassGoal;
	private int sheepTimer;
	
	@SuppressWarnings("unchecked")
	public SpectralSheepEntity(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super((EntityType<? extends CreatureEntity>) ModEntities.SPECTRAL_SHEEP, worldIn);
	}
	
	@Override
	public void registerGoals() 
	{
	      this.eatGrassGoal = new EatGrassGoal(this);
	      this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
	      this.goalSelector.addGoal(2, new TemptGoal(this, 1.1D, Ingredient.fromItems(Items.WHEAT), false));
	      this.goalSelector.addGoal(3, this.eatGrassGoal);
	      this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
	      this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.23F);
	}
	
	@Override
	protected void updateAITasks() 
	{
		this.sheepTimer = this.eatGrassGoal.getEatingGrassTimer();
		super.updateAITasks();
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) 
	{
		return false;
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
	protected SoundEvent getAmbientSound() 
	{
		return ModSoundEvents.spectral_sheep_say;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSoundEvents.spectral_sheep_hurt;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSoundEvents.spectral_sheep_death;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) 
	{
		this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
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
	public void livingTick() 
	{
		if (this.world.isRemote) 
		{
			this.sheepTimer = Math.max(0, this.sheepTimer - 1);
		}
		super.livingTick();
	}
	
   @OnlyIn(Dist.CLIENT)
   public void handleStatusUpdate(byte id) 
   {
	   if (id == 10) 
	   {
		   this.sheepTimer = 40;
	   } 
	   else 
	   { 
		   super.handleStatusUpdate(id);
	   }
   }
	
   @OnlyIn(Dist.CLIENT)
   public float getHeadRotationPointY(float p_70894_1_) 
   {
	   if (this.sheepTimer <= 0) 
	   {
		   return 0.0F;
	   } 
	   else if (this.sheepTimer >= 4 && this.sheepTimer <= 36) 
	   {
		   return 1.0F;
	   } 
	   else 
	   {
		   return this.sheepTimer < 4 ? ((float)this.sheepTimer - p_70894_1_) / 4.0F : -((float)(this.sheepTimer - 40) - p_70894_1_) / 4.0F;
	   }
	}

	@OnlyIn(Dist.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_) 
	{
		if (this.sheepTimer > 4 && this.sheepTimer <= 36) 
		{
			float f = ((float)(this.sheepTimer - 4) - p_70890_1_) / 32.0F;
			return ((float)Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
		} 
		else 
		{
			return this.sheepTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * ((float)Math.PI / 180F);
		}
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) 
	{
		return 0.95F * sizeIn.height;
	}
}
