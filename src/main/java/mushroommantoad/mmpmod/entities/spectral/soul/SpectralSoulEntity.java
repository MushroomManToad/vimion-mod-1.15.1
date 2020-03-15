package mushroommantoad.mmpmod.entities.spectral.soul;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class SpectralSoulEntity extends CreatureEntity
{	
	private static final DataParameter<String> RENDER = EntityDataManager.createKey(SpectralSoulEntity.class, DataSerializers.STRING);
	
	public SpectralSoulEntity(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super(type, worldIn);
	}
	
	public String getRender()
	{
		return this.dataManager.get(RENDER);
	}

	public void setRender(String s) 
	{
		this.dataManager.set(RENDER, s);
		System.out.println(RENDER);
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();
		this.dataManager.register(RENDER, "minecraft:skeleton");
	}
	
	@Override
	public void tick() 
	{
		super.tick();
	}
	
	public EntityModel<LivingEntity> model()
	{
		return null;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) 
	{
		this.setRender(compound.getString("intEntID"));
		super.readAdditional(compound);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) 
	{
		compound.putString("intEntID", getRender());
		super.writeAdditional(compound);
	}
}
