package mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire;

import java.util.UUID;

import javax.annotation.Nullable;

import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.network.SToCAbsorptionSpireParticlePacket;
import mushroommantoad.mmpmod.network.VimionPacketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.PacketDistributor;

public class EntityAbsorptionSpire extends Entity
{
	private int tickCooldown = 20;
	private int lifeTicks = 479;
	private LivingEntity caster;
	private UUID casterUuid;
	
	DamageSource spireDamage = new DamageSource("absorptionSpire").setDamageIsAbsolute().setDamageBypassesArmor();
	
	public EntityAbsorptionSpire(EntityType<? extends Entity> entityTypeIn, World worldIn) 
	{
		super((EntityType<? extends Entity>) ModEntities.ABSORPTION_SPIRE, worldIn);
	}
	
	public EntityAbsorptionSpire(World worldIn) 
	{
		super((EntityType<? extends Entity>) ModEntities.ABSORPTION_SPIRE, worldIn);
	}
	
	public EntityAbsorptionSpire(World worldIn, LivingEntity caster) 
	{
		super((EntityType<? extends Entity>) ModEntities.ABSORPTION_SPIRE, worldIn);
		setCaster(caster);
	}
	
	public void setCaster(@Nullable LivingEntity p_190549_1_) 
	{
		this.caster = p_190549_1_;
		this.casterUuid = p_190549_1_ == null ? null : p_190549_1_.getUniqueID();
	}

	@Nullable
	public LivingEntity getCaster() 
	{
		if (this.caster == null && this.casterUuid != null && this.world instanceof ServerWorld) 
		{
			Entity entity = ((ServerWorld)this.world).getEntityByUuid(this.casterUuid);
			if (entity instanceof LivingEntity) {
				this.caster = (LivingEntity)entity;
			}
		}
		return this.caster;
	}

	@Override
	protected void registerData() {}

	@Override
	protected void readAdditional(CompoundNBT compound) 
	{
		this.tickCooldown = compound.getInt("TickCooldown");
		if (compound.hasUniqueId("OwnerUUID")) 
		{
			this.casterUuid = compound.getUniqueId("OwnerUUID");
		}
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) 
	{
		compound.putInt("TickCooldown", this.tickCooldown);
		if (this.casterUuid != null) 
		{
			compound.putUniqueId("OwnerUUID", this.casterUuid);
		}
	}
	
	@Nullable
	public AxisAlignedBB getCollisionBox(Entity entityIn) { return entityIn.canBePushed() ? entityIn.getBoundingBox() : null; }

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox() { return this.getBoundingBox(); }

	@Override
	public boolean canBePushed() { return true; }
	
	public boolean isCreative(LivingEntity entity)
	{
		if(entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entity;
			if(player.isCreative()) return true;
			else return false;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public void tick() 
	{
		if (this.world instanceof ServerWorld && this.getCaster() != null) 
		{
			CompoundNBT nbt = this.getPersistentData();
			if(nbt.contains("TickCooldown"))
			{
				this.tickCooldown--;
				if(this.tickCooldown == 0)
				{
					AxisAlignedBB aabb = new AxisAlignedBB(this.getPosX() + 5, this.getPosY() + 5, this.getPosZ() + 5, this.getPosX() - 5, this.getPosY() - 5, this.getPosZ() - 5);
					for(LivingEntity livingentity : this.world.getEntitiesWithinAABB(LivingEntity.class, aabb)) 
					{
						if(livingentity != this.getCaster() && livingentity.isAlive() && !livingentity.isInvulnerable() && this.getCaster().isAlive() && !this.isCreative(livingentity))
						{
							if(livingentity.attackEntityFrom(spireDamage, 1) && this.getCaster().getHealth() < this.getCaster().getMaxHealth())
							{
								this.getCaster().setHealth(this.getCaster().getHealth() + 1);
							}
							for(ServerPlayerEntity playerIn : this.world.getEntitiesWithinAABB(ServerPlayerEntity.class, aabb.grow(27))) 
							{
								VimionPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> playerIn), new SToCAbsorptionSpireParticlePacket(this.getPosX(), this.getPosY(), this.getPosZ()));
							}
						}
					}
				}
				if(this.tickCooldown < 0)
				{
					this.tickCooldown = 19;
				}
				nbt.putInt("TickCooldown", this.tickCooldown);
			}
			else
			{
				nbt.putInt("TickCooldown", this.tickCooldown);
			}
			
			this.lifeTicks--;
			if(this.lifeTicks <= 0)
			{
				this.remove();
			}
		}
		super.tick();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) 
	{
		if (this.isInvulnerableTo(source)) 
		{
			return false;
		} 
		else if (source.getTrueSource() instanceof EnderDragonEntity) 
		{
			return false;
		} 
		else 
		{
			if (!this.removed && !this.world.isRemote) 
			{
				this.remove();
				AxisAlignedBB aabb = new AxisAlignedBB(this.getPosX() + 15, this.getPosY() + 15, this.getPosZ() + 15, this.getPosX() - 15, this.getPosY() - 15, this.getPosZ() - 15);
				for(LivingEntity livingentity : this.world.getEntitiesWithinAABB(LivingEntity.class, aabb)) 
				{
					if(livingentity instanceof PlayerEntity)
					{
						PlayerEntity player = (PlayerEntity) livingentity;
						world.playSound(player, this.getPosition(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.HOSTILE, 1, -1);
					}
				}
			}
			return true;
		}
	}

	@Override
	public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
