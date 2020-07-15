package mushroommantoad.mmpmod.entities.boss.solarionic_abomination.fire_blast;

import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

// So anyway, this is basically a Dragon Fireball with special functions
public class FireBlastEntity extends DamagingProjectileEntity 
{	
	/**<pre>{@code Stellar Type Controls color. 
	 * 0 = Solarionic
	 * 1 = Blue
	 * 2 = Red
	 * 3 = Orange
	 * 4 = White
	 * 5 = Black
	 * 6 = Purple 
	 * }</pre>
	 */
	private static final DataParameter<Integer> STELLAR_TYPE = EntityDataManager.createKey(FireBlastEntity.class, DataSerializers.VARINT);
	
	@SuppressWarnings("unchecked")
	public FireBlastEntity(EntityType<? extends Entity> type, World worldIn) {
		super((EntityType<? extends DamagingProjectileEntity>) type, worldIn);
	}
	public FireBlastEntity(World worldIn) {
		super(ModEntities.FIRE_BLAST, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	public FireBlastEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ, int color) {
		super(ModEntities.FIRE_BLAST, x, y, z, accelX, accelY, accelZ, worldIn);
		setStellarType(color);
	}

	public FireBlastEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ, int color) {
		super(ModEntities.FIRE_BLAST, shooter, accelX, accelY, accelZ, worldIn);
		setStellarType(color);
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();
		this.dataManager.register(STELLAR_TYPE, 0);
	}
	
	public int getStellarType() { return this.dataManager.get(STELLAR_TYPE); }
	public void setStellarType(int value) {this.dataManager.set(STELLAR_TYPE, value);}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(!this.world.isRemote)
		{
			if(this.ticksExisted > 60)
			{
				this.remove();
			}
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		super.onImpact(result);
		if(result.getType() == RayTraceResult.Type.ENTITY)
		{
			if (!this.world.isRemote)
			{
				EntityRayTraceResult entityresult = (EntityRayTraceResult)result;
				Entity entityIn = entityresult.getEntity();
				if(entityIn instanceof LivingEntity && entityIn != this.shootingEntity)
				{
					LivingEntity livingIn = (LivingEntity)entityIn;
					if(livingIn.getFireTimer() > 0) livingIn.setFire(3);
					else livingIn.setFire(1);
					this.remove();
				}
			}
		}
		if(result.getType() == RayTraceResult.Type.BLOCK)
		{
			if(!this.world.isRemote) 
			{
				this.remove();
			}
		}
	}

	public boolean canBeCollidedWith() {return true;}
	public boolean attackEntityFrom(DamageSource source, float amount) {return false;}
	
	@Override
	protected IParticleData getParticle() 
	{
		switch(getStellarType())
		{
			case(0): return ParticleTypes.FLAME;
			case(1): return ModParticles.BLUE_FLAME;
			case(2): return ModParticles.RED_FLAME;
			case(3): return ModParticles.ORANGE_FLAME;
			case(4): return ModParticles.WHITE_FLAME;
			case(5): return ModParticles.BLACK_FLAME;
			case(6): return ModParticles.PURPLE_FLAME;
			default: return ParticleTypes.FLAME;
		}
	}
	protected boolean isFireballFiery() {return false;}
	
	@Override
	public IPacket<?> createSpawnPacket() {return NetworkHooks.getEntitySpawningPacket(this);}
}