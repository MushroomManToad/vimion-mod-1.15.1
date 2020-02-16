package mushroommantoad.mmpmod.entities.spectral.soul;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.world.World;

public class SpectralSoulEntity extends CreatureEntity
{
	public SpectralSoulEntity(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super(type, worldIn);
	}
	
	public LivingEntity getInternalEntity()
	{
		return new ZombieEntity(world);
	}
}
