package mushroommantoad.mmpmod.entities.boss.goals;

import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.VimionicAbominationEntity;
import mushroommantoad.mmpmod.entities.spectral.sprite.SpectralSpriteEntity;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import mushroommantoad.mmpmod.util.VTranslate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

public class SummonSpectralSpriteAtGoal extends Goal
{
	VimionicAbominationEntity summoner;
	
	public SummonSpectralSpriteAtGoal(VimionicAbominationEntity summoner)
	{
		this.summoner = summoner;
	}
	
	@Override
	public boolean shouldExecute() 
	{
		LivingEntity entity = this.summoner.getAttackTarget();
		if(entity != null)
		{
			return true;
		}
		else
		{
			if(summoner.isSummoningEntity()) summoner.setSummoningEntity(false);
			return false;
		}
	}
	
	
	@Override
	public void tick() 
	{
		if(!this.summoner.world.isRemote)
		{
			if(this.summoner.getSpriteCooldown() <= 10)
			{
				summoner.setSummoningEntity(true);
			}
			else if(summoner.isSummoningEntity())
			{
				summoner.setSummoningEntity(false);
			}
			if(this.summoner.getSpriteCooldown() == 1)
			{
				World worldIn = this.summoner.world;
				SpectralSpriteEntity sprite1 = new SpectralSpriteEntity(ModEntities.SPECTRAL_SPRITE, worldIn);
				SpectralSpriteEntity sprite2 = new SpectralSpriteEntity(ModEntities.SPECTRAL_SPRITE, worldIn);
				LivingEntity entity = summoner.getAttackTarget();
				sprite1.setPosition(VTranslate.getEntityX(entity) + 2, VTranslate.getEntityY(entity) + 4.0, VTranslate.getEntityZ(entity));
				sprite1.setPosition(VTranslate.getEntityX(entity) - 2, VTranslate.getEntityY(entity) + 4.0, VTranslate.getEntityZ(entity));
				worldIn.addEntity(sprite1);
				worldIn.addEntity(sprite2);
				summoner.playSound(ModSoundEvents.absorption_pillar_summon, 1, 0);
			}
		}
	}
}
