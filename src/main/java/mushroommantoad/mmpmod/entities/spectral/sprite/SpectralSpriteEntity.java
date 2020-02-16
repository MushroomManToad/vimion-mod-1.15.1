package mushroommantoad.mmpmod.entities.spectral.sprite;

import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.network.SToCParticleAtPosPacket;
import mushroommantoad.mmpmod.network.VimionPacketHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class SpectralSpriteEntity extends AmbientEntity 
{
	private BlockPos spawnPosition;
	
	DamageSource spriteBurst = new DamageSource("spriteBurst");
	
	public SpectralSpriteEntity(EntityType<? extends AmbientEntity> type, World worldIn) 
	{
		super(ModEntities.SPECTRAL_SPRITE, worldIn);
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
		if(this.ticksExisted >= 200)
		{
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			AxisAlignedBB aabb = new AxisAlignedBB(x + 5, y + 5, z + 5, x - 5, y - 5, z - 5);
			for(LivingEntity entity : world.getEntitiesWithinAABB(LivingEntity.class, aabb))
			{
				entity.attackEntityFrom(spriteBurst, 2);
			}
			AxisAlignedBB aabb2 = new AxisAlignedBB(x + 32, y + 32, z + 32, x - 32, y - 32, z - 32);
			for(ServerPlayerEntity playerIn : this.world.getEntitiesWithinAABB(ServerPlayerEntity.class, aabb2)) 
			{
				VimionPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> playerIn), new SToCParticleAtPosPacket(x, y, z, 1));
			}
			this.remove();
		}
	}
	
	@Override
	protected void updateAITasks() 
	{
		super.updateAITasks();

        if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
            this.spawnPosition = null;
         }

         if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.withinDistance(this.getPositionVec(), 2.0D)) {
            this.spawnPosition = new BlockPos(this.getPosX() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7), this.getPosY() + (double)this.rand.nextInt(6) - 2.0D, this.getPosZ() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7));
         }
		
        double d0 = (double)this.spawnPosition.getX() + 0.5D - this.getPosX();
        double d1 = (double)this.spawnPosition.getY() + 0.1D - this.getPosY();
        double d2 = (double)this.spawnPosition.getZ() + 0.5D - this.getPosZ();
        Vec3d vec3d = this.getMotion();
        Vec3d vec3d1 = vec3d.add((Math.signum(d0) * 0.5D - vec3d.x) * (double)0.01F, (Math.signum(d1) * (double)0.7F - vec3d.y) * (double)0.09F, (Math.signum(d2) * 0.5D - vec3d.z) * (double)0.01F);
        this.setMotion(vec3d1);
        float f = (float)(MathHelper.atan2(vec3d1.z, vec3d1.x) * (double)(180F / (float)Math.PI)) - 90.0F;
        float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
        this.moveForward = 0.5F;
        this.rotationYaw += f1;
	}
		
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
	}
	
	/* Yeah, below here is mostly just stolen bat code slapped together.
	   The things literally exist for only a few seconds. I'm sure it's fine.*/
	@Override
	protected void collideWithEntity(Entity entityIn) {}

	@Override
	protected void collideWithNearbyEntities() {}
	
	@Override
	public boolean canBePushed() {return false;}
	
	@Override
	protected boolean canTriggerWalking() {return false;}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {return false;}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}

	@Override
	public boolean doesEntityNotTriggerPressurePlate() {return true;}
}
