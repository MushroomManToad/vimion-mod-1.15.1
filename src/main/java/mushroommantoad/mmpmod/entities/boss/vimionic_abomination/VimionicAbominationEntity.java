package mushroommantoad.mmpmod.entities.boss.vimionic_abomination;

import mushroommantoad.mmpmod.entities.boss.goals.SummonAbsorptionSpireAtGoal;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.ServerBossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class VimionicAbominationEntity extends CreatureEntity 
{
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(new TranslationTextComponent("bossbar.vimion.vimionic_abomination.name").applyTextStyle(TextFormatting.GREEN), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS)).setCreateFog(true);
	
	private int spireCooldown = 100;
	
	@SuppressWarnings("unchecked")
	public VimionicAbominationEntity(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super((EntityType<? extends CreatureEntity>) ModEntities.VIMIONIC_ABOMINATION, worldIn);
		this.experienceValue = 50;
	}
	
	@Override
	public void registerGoals() 
	{
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new SummonAbsorptionSpireAtGoal(this));
	    this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 64.0F));
	    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) 
	{
		this.spireCooldown = compound.getInt("spireCooldown");
		super.readAdditional(compound);
	}

	@Override
	public void writeAdditional(CompoundNBT compound) 
	{
		compound.putInt("spireCooldown", this.spireCooldown);
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
					this.spireCooldown = (int) this.getHealth();
				}
				nbt.putInt("spireCooldown", this.spireCooldown);
			}
			else
			{
				nbt.putInt("spireCooldown", this.spireCooldown);
			}
		}
		super.tick();
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
		ItemEntity itementity = this.entityDropItem(ModItems.vimioplasm);
		if (itementity != null) 
		{
			itementity.setNoDespawn();
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
}
