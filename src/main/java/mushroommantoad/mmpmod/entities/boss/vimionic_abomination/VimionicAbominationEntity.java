package mushroommantoad.mmpmod.entities.boss.vimionic_abomination;

import mushroommantoad.mmpmod.entities.boss.goals.SummonAbsorptionSpireAtGoal;
import mushroommantoad.mmpmod.entities.boss.goals.SummonSpectralSpriteAtGoal;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModItems;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VimionicAbominationEntity extends CreatureEntity 
{
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(new TranslationTextComponent("bossbar.vimion.vimionic_abomination.name").applyTextStyle(TextFormatting.GREEN), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS)).setCreateFog(true);
	private static final DataParameter<Boolean> SUMMONING_ENTITY = EntityDataManager.createKey(VimionicAbominationEntity.class, DataSerializers.BOOLEAN);
	private int spireCooldown = 50;
	private int spriteCooldown = 100;
	
	public VimionicAbominationEntity(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super(ModEntities.VIMIONIC_ABOMINATION, worldIn);
		this.experienceValue = 50;
	}
	
	@Override
	public void registerGoals() 
	{
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new SummonAbsorptionSpireAtGoal(this));
		this.goalSelector.addGoal(2, new SummonSpectralSpriteAtGoal(this));
	    this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 64.0F));
	    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) 
	{
		this.spireCooldown = compound.getInt("spireCooldown");
		this.spriteCooldown = compound.getInt("spriteCooldown");
		super.readAdditional(compound);
	}

	@Override
	public void writeAdditional(CompoundNBT compound) 
	{
		compound.putInt("spireCooldown", this.spireCooldown);
		compound.putInt("spriteCooldown", this.spriteCooldown);
		super.writeAdditional(compound);
	}
	
	@Override
	public void tick() 
	{
		if (this.world instanceof ServerWorld) 
		{
			CompoundNBT nbt = this.getPersistentData();
			if(nbt.contains("spireCooldown"))
			{
				this.spireCooldown--;
				if(this.spireCooldown < 0)
				{
					this.spireCooldown = 93;
				}
				nbt.putInt("spireCooldown", this.spireCooldown);
			}
			else
			{
				nbt.putInt("spireCooldown", this.spireCooldown);
			}
			if(nbt.contains("spriteCooldown"))
			{
				this.spriteCooldown--;
				if(this.spriteCooldown < 0)
				{
					this.spriteCooldown = (int) (this.getHealth()) + 1;
				}
				nbt.putInt("spriteCooldown", this.spriteCooldown);
			}
			else
			{
				nbt.putInt("spriteCooldown", this.spriteCooldown);
			}
		}
		super.tick();
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean isSummoningEntity() 
	{
		return this.dataManager.get(SUMMONING_ENTITY);
	}

	public void setSummoningEntity(boolean bool) 
	{
		this.dataManager.set(SUMMONING_ENTITY, bool);
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();
		this.dataManager.register(SUMMONING_ENTITY, false);
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return ModSoundEvents.vimionic_abomination_say;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSoundEvents.vimionic_abomination_hurt;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSoundEvents.vimionic_abomination_death;
	}
	
	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) 
	{
		super.dropSpecialItems(source, looting, recentlyHitIn);
		ItemEntity itementity0 = this.entityDropItem(ModItems.vimioplasm);
		if (itementity0 != null) {itementity0.setNoDespawn();}
		if(Math.random() > 0.75)
		{
			ItemEntity itementity1 = this.entityDropItem(ModItems.vimioplasm);
			if (itementity1 != null) {itementity1.setNoDespawn();}
		}
		if(Math.random() > 0.75)
		{
			ItemEntity itementity2 = this.entityDropItem(ModItems.vimioplasm);
			if (itementity2 != null) {itementity2.setNoDespawn();}
		}
		if(Math.random() > 0.75)
		{
			ItemEntity itementity3 = this.entityDropItem(ModItems.vimioplasm);
			if (itementity3 != null) {itementity3.setNoDespawn();}
		}
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) 
	{
		return false;
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) 
	{
		return 0.9F * sizeIn.height;
	}
	
	@Override
	public void addTrackingPlayer(ServerPlayerEntity player)
	{
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) 
	{

		Entity entity = source.getImmediateSource();
		if (entity instanceof AbstractArrowEntity) 
		{
			return false;
		}
		else return super.attackEntityFrom(source, amount);
	}
	
	@Override
	protected void updateAITasks() 
	{
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		super.updateAITasks();
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) 
	{
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}
	
	@Override
	public boolean isNonBoss() 
	{
		return false;
	}
	
	public int getSpireCooldown()
	{
		return this.spireCooldown;
	}
	
	public int getSpriteCooldown()
	{
		return this.spriteCooldown;
	}
}
