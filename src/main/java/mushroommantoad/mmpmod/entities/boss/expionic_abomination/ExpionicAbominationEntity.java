package mushroommantoad.mmpmod.entities.boss.expionic_abomination;

import mushroommantoad.mmpmod.entities.boss.goals.BurstTeleportGoal;
import mushroommantoad.mmpmod.entities.boss.goals.RemoteDisarmGoal;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModItems;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import mushroommantoad.mmpmod.network.SToCExpionicTeleportParticlePacket;
import mushroommantoad.mmpmod.network.VimionPacketHandler;
import mushroommantoad.mmpmod.util.VTranslate;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.PacketDistributor;

public class ExpionicAbominationEntity extends CreatureEntity
{
	private static final DataParameter<Boolean> IS_MOVING = EntityDataManager.createKey(ExpionicAbominationEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_TPCHANNELLING = EntityDataManager.createKey(ExpionicAbominationEntity.class, DataSerializers.BOOLEAN);
	
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(new TranslationTextComponent("bossbar.vimion.expionic_abomination.name").applyTextStyle(TextFormatting.BLUE), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS)).setCreateFog(true);
	
	private int tpCooldown = 150;
	private int disarmCooldown = 307;
	private int levitateCooldown = 333;
	
	DamageSource expionicTeleport = new DamageSource("expionicTeleport").setDamageIsAbsolute();
	
	public ExpionicAbominationEntity(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super( ModEntities.EXPIONIC_ABOMINATION, worldIn);
		this.experienceValue = 50;
	}
	
	@Override
	public void registerGoals() 
	{
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new RemoteDisarmGoal(this));
		this.goalSelector.addGoal(2, new BurstTeleportGoal(this));
	    this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 64.0F));
	    this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
	    this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
	    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) 
	{
		this.tpCooldown = compound.getInt("tpCooldown");
		this.disarmCooldown = compound.getInt("disarmCooldown");
		this.levitateCooldown = compound.getInt("levitateCooldown");
		super.readAdditional(compound);
	}

	@Override
	public void writeAdditional(CompoundNBT compound) 
	{
		compound.putInt("tpCooldown", this.tpCooldown);
		compound.putInt("disarmCooldown", this.disarmCooldown);
		compound.putInt("levitateCooldown", this.levitateCooldown);
		super.writeAdditional(compound);
	}
	
	public int getTPCooldown(){return this.tpCooldown;}
	public int getDisarmCooldown(){return this.disarmCooldown;}
	public int getLevitateCooldown(){return this.levitateCooldown;}
	
	@Override
	protected void registerData() 
	{
		super.registerData();
		this.dataManager.register(IS_MOVING, false);
		this.dataManager.register(IS_TPCHANNELLING, false);
	}
	
	public boolean isMoving() {	return this.dataManager.get(IS_MOVING); }
	
	public boolean isTPChannelling() { return this.dataManager.get(IS_TPCHANNELLING); }
	
	public void updateIsMoving(boolean value) { this.dataManager.set(IS_MOVING, value);	}
	
	public void updateIsTPChannelling(boolean value) { this.dataManager.set(IS_TPCHANNELLING, value); }
	
	@Override
	public void tick()
	{
		if (this.world instanceof ServerWorld) 
		{
			CompoundNBT nbt = this.getPersistentData();
			if(nbt.contains("tpCooldown"))
			{
				this.tpCooldown--;
				if(this.tpCooldown < 0)
				{
					this.tpCooldown = 150;
				}
				nbt.putInt("tpCooldown", this.tpCooldown);
			}
			else
			{
				nbt.putInt("tpCooldown", this.tpCooldown);
			}
			
			if(nbt.contains("disarmCooldown"))
			{
				this.disarmCooldown--;
				if(this.disarmCooldown < 0)
				{
					this.disarmCooldown = 307;
				}
				nbt.putInt("disarmCooldown", this.disarmCooldown);
			}
			else
			{
				nbt.putInt("disarmCooldown", this.disarmCooldown);
			}
			
			if(nbt.contains("levitateCooldown"))
			{
				this.levitateCooldown--;
				if(this.levitateCooldown < 0)
				{
					this.levitateCooldown = 333;
				}
				nbt.putInt("levitateCooldown", this.levitateCooldown);
			}
			else
			{
				nbt.putInt("levitateCooldown", this.levitateCooldown);
			}
		}
		super.tick();
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
	      if (this.isInvulnerableTo(source)) {
	         return false;
	      } else if (!(source instanceof IndirectEntityDamageSource) && source != DamageSource.FIREWORKS) {
	         boolean flag = super.attackEntityFrom(source, amount);
	         if (source.isUnblockable() && this.rand.nextInt(10) != 0) {
	            this.attemptRandomTP(16);
	         }
	         
	         if(Math.random() > 0.66) 
	         {
	        	 if(this.attemptRandomTP(16))
	        	 {
	        		 if(source.getImmediateSource() instanceof PlayerEntity)
	        			 source.getImmediateSource().attackEntityFrom(expionicTeleport, 5); 
	        	 }
	         }
	         
	         return flag;
	      } else {
	            if (this.attemptRandomTP(16)) {
	               return true;
	            }
	         return false;
	      }
	   }
	
	public boolean attemptRandomTP(double distance)
	{
		for(int i = 0; i < 64; i++)
		{
			boolean s = teleportRandomly(distance);
			if(s) return s;
		}
		return false;
	}
	
	public boolean teleportRandomly(double distance) 
	{
		double d0 = VTranslate.getEntityX(this) + (this.rand.nextDouble() - 0.5D) * distance;
		double d1 = VTranslate.getEntityY(this);
		double d2 = VTranslate.getEntityZ(this) + (this.rand.nextDouble() - 0.5D) * distance;
		return this.teleportTo(d0, d1, d2);
	}

	public boolean teleportTo(double x, double y, double z) 
	{
		BlockPos.Mutable pos = new BlockPos.Mutable(x, y + 8, z);
		while(pos.getY() > y - 8 && !this.world.getBlockState(pos).getMaterial().blocksMovement()) 
		{
			pos.move(Direction.DOWN);
		}
		if (!this.world.getBlockState(pos).getMaterial().blocksMovement()) 
		{
			return false;
		} 
		else 
		{
			double[] coords = {VTranslate.getEntityX(this), VTranslate.getEntityY(this), VTranslate.getEntityZ(this)};
			boolean flag = this.attemptTeleport(pos.getX(), pos.getY() + 1, pos.getZ(), false);
			if (flag) 
			{
				AxisAlignedBB aabb = new AxisAlignedBB(VTranslate.getEntityX(this) + 32, VTranslate.getEntityY(this) + 32, VTranslate.getEntityZ(this) + 32, VTranslate.getEntityX(this) - 32, VTranslate.getEntityY(this) - 32, VTranslate.getEntityZ(this) - 32);
				for(ServerPlayerEntity playerIn : this.world.getEntitiesWithinAABB(ServerPlayerEntity.class, aabb)) 
				{
					VimionPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> playerIn), new SToCExpionicTeleportParticlePacket(coords[0], coords[1], coords[2], 0));
				}
				this.world.playSound((PlayerEntity)null, coords[0], coords[1], coords[2], ModSoundEvents.expionic_abomination_teleport, this.getSoundCategory(), 1.0F, 1.0F);
				this.playSound(ModSoundEvents.expionic_abomination_teleport, 1.0F, 1.0F);
			}
			return flag;
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return ModSoundEvents.expionic_abomination_say;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSoundEvents.expionic_abomination_hurt;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSoundEvents.expionic_abomination_death;
	}
	
	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) 
	{
		super.dropSpecialItems(source, looting, recentlyHitIn);
		ItemEntity itementity = this.entityDropItem(ModItems.expioplasm);
		if (itementity != null) {itementity.setNoDespawn(); }
		if(Math.random() > 0.75)
		{
			ItemEntity itementity1 = this.entityDropItem(ModItems.expioplasm);
			if (itementity1 != null) {itementity1.setNoDespawn();}
		}
		if(Math.random() > 0.75)
		{
			ItemEntity itementity2 = this.entityDropItem(ModItems.expioplasm);
			if (itementity2 != null) {itementity2.setNoDespawn();}
		}
		if(Math.random() > 0.75)
		{
			ItemEntity itementity3 = this.entityDropItem(ModItems.expioplasm);
			if (itementity3 != null) {itementity3.setNoDespawn();}
		}
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(250.0D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
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
	protected void updateAITasks() 
	{
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		super.updateAITasks();
	}
	
	@Override
	public boolean isNonBoss() 
	{
		return false;
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) 
	{
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}
}
