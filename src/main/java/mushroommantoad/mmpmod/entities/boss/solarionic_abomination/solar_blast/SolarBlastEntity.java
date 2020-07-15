package mushroommantoad.mmpmod.entities.boss.solarionic_abomination.solar_blast;

import java.util.ArrayList;

import mushroommantoad.mmpmod.entities.boss.solarionic_abomination.fire_blast.FireBlastEntity;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModParticles;
import mushroommantoad.mmpmod.network.SToCParticleAtPosPacket;
import mushroommantoad.mmpmod.network.VimionPacketHandler;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.PacketDistributor;

public class SolarBlastEntity extends Entity
{
	private int minY = 50;
	private int tier = 0;
	
	ArrayList<BlockPos> breaktargets = new ArrayList<>();
	
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
	
	DamageSource solarBlast = new DamageSource("solarBlast");

	public SolarBlastEntity(EntityType<? extends Entity> entityTypeIn, World worldIn) 
	{
		super((EntityType<? extends Entity>) ModEntities.SOLAR_BLAST, worldIn);
		lockMinYNBT();
		lockTierNBT();
	}
	
	public SolarBlastEntity(World worldIn) 
	{
		super((EntityType<? extends Entity>) ModEntities.SOLAR_BLAST, worldIn);
		lockMinYNBT();
		lockTierNBT();
	}
	
	public SolarBlastEntity(World worldIn, int minY, int tier, int color) 
	{
		super((EntityType<? extends Entity>) ModEntities.SOLAR_BLAST, worldIn);
		this.minY = minY;
		this.tier = tier;
		lockMinYNBT();
		lockTierNBT();
		setStellarType(color);
	}
	
	@Override
	protected void registerData() 
	{
		// I guess I can't implement super here /shrug
		this.dataManager.register(STELLAR_TYPE, 0);
	}
	
	public int getStellarType() { return this.dataManager.get(STELLAR_TYPE); }
	public void setStellarType(int value) {this.dataManager.set(STELLAR_TYPE, value);}
	
	@OnlyIn(Dist.CLIENT)
	public boolean isInRangeToRenderDist(double distance) {
		double d0 = this.getBoundingBox().getAverageEdgeLength() * 4.0D;
		if (Double.isNaN(d0)) 
		{
			d0 = 4.0D;
		}
		d0 = d0 * 64.0D;
		return distance < d0 * d0;
	}

	@Override
	public void readAdditional(CompoundNBT compound) 
	{
		this.minY = compound.getInt("minY");
	}

	@Override
	public void writeAdditional(CompoundNBT compound) 
	{
		compound.putInt("minY", this.minY);
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		intTick();
	}
	
	public void intTick()
	{
		if (!this.hasNoGravity()) 
		{
			this.setMotion(this.getMotion().add(0.0D, -0.04D, 0.0D));
	    }

	    this.move(MoverType.SELF, this.getMotion());
	    this.setMotion(this.getMotion().scale(0.98D));
	    if (this.onGround) {
	    	this.setMotion(this.getMotion().mul(0.7D, -0.5D, 0.7D));
	    }
	    
		if (this.world.isRemote) {
			BasicParticleType type = ParticleTypes.FLAME;
			int types = getStellarType();
			switch(types)
			{
			case(1):type = ModParticles.BLUE_FLAME;	break;
			case(2):type = ModParticles.RED_FLAME; break;
			case(3):type = ModParticles.ORANGE_FLAME; break;
			case(4):type = ModParticles.WHITE_FLAME; break;
			case(5):type = ModParticles.BLACK_FLAME; break;
			case(6):type = ModParticles.PURPLE_FLAME; break;
			default:break;
			}
			for(int i = 0; i < 2; ++i) {
				this.world.addParticle(type, this.getPosXRandom(0.5D), this.getPosYRandom() - 0.25D, this.getPosZRandom(0.5D), 0D, 0, 0D);
				this.world.addParticle(ParticleTypes.SMOKE, this.getPosXRandom(0.5D), this.getPosYRandom() - 0.25D, this.getPosZRandom(0.5D), 0D, 0, 0D);
			}
		}
		    
	    if(!this.world.isRemote)
	    {
	    	World worldIn = this.world;
			if(worldIn.getGameRules().getBoolean(GameRules.MOB_GRIEFING))
			{
				breaktargets.clear();
				addBreakTargets(worldIn, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
		    	for(BlockPos pos : breaktargets)
		    	{
    				BlockState state = worldIn.getBlockState(pos);
			    	if(!(state.getBlock() instanceof AirBlock))
			    	{
			    		if(state.getBlockHardness(worldIn, pos) >= 0)
			    		{
			    			worldIn.destroyBlock(pos, true);
			    		}
			    	}
				}
		    	
		    	breaktargets.clear();
	    	}
	    	
	    	if(this.ticksExisted > 60 || this.getPosition().getY() < this.getMinY())
	    	{
	    		this.removeWithEffect();
	    	}
	    }
	}
	
	public void addBreakTargets(World worldIn, int x, int y, int z)
	{
		switch(tier)
		{
		case(0):
			for(int a = -1; a <= 1; a++)
				for(int c = -1; c <= 1; c++)
					breaktargets.add(new BlockPos(x + a, y - 1, z + c));
			break;
		case(1):
			breaktargets.add(new BlockPos(x, y - 2, z));
			for(int a = -1; a <= 1; a++)
			{
				for(int c = -1; c <= 1; c++)
				{
					breaktargets.add(new BlockPos(x + a, y - 1, z + c));
				}
			}
			break;
		case(2):
			for(int a = -1; a <= 1; a++)
			{
				for(int b = -2; b <= 0; b++)
				{
					for(int c = -1; c <= 1; c++)
					{
						breaktargets.add(new BlockPos(x + a, y + b, z + c));
					}
				}
			}
			break;
		default:break;
		}
	}
	
	public void removeWithEffect()
	{
		AxisAlignedBB aabb = new AxisAlignedBB(this.getPosX() + 2, this.getPosY() + 2, this.getPosZ() + 2, this.getPosX() - 2, this.getPosY() - 2, this.getPosZ() - 2);
		for(ServerPlayerEntity playerIn : this.world.getEntitiesWithinAABB(ServerPlayerEntity.class, aabb)) 
		{
			playerIn.attackEntityFrom(solarBlast, 2.0F + tier);
			playerIn.setFire(3);
		}

		for(ServerPlayerEntity playerIn : this.world.getEntitiesWithinAABB(ServerPlayerEntity.class, aabb.grow(30))) 
		{
			VimionPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> playerIn), new SToCParticleAtPosPacket(this.getPosX(), this.getPosY(), this.getPosZ(), 3));
		}
		this.remove();
	}
	
	protected float getGravityVelocity() 
	{
		return 0.03F;
	}
	
	public int getMinY()
	{
		CompoundNBT nbt = this.getPersistentData();
		if(nbt != null)
		{
			if(nbt.contains("minY"))
			{
				return nbt.getInt("minY");
			}
		}
		return 5;
	}
	
	public void lockMinYNBT()
	{
		CompoundNBT nbt = this.getPersistentData();
		if(nbt == null)
		{
			nbt = new CompoundNBT();
		}
		if(!nbt.contains("minY")) nbt.putInt("minY", this.minY);
	}
	
	public void lockTierNBT()
	{
		CompoundNBT nbt = this.getPersistentData();
		if(nbt == null)
		{
			nbt = new CompoundNBT();
		}
		if(!nbt.contains("tier")) nbt.putInt("tier", this.tier);
	}

	@Override
	public IPacket<?> createSpawnPacket() {return NetworkHooks.getEntitySpawningPacket(this);}

}