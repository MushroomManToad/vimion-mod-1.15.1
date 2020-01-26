package mushroommantoad.mmpmod.entities.boss.goals;

import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.VimionicAbominationEntity;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire.EntityAbsorptionSpire;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import mushroommantoad.mmpmod.util.MushroomsMathUtil;
import mushroommantoad.mmpmod.util.VTranslate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

public class SummonAbsorptionSpireAtGoal extends Goal
{
	VimionicAbominationEntity summoner;
	
	public SummonAbsorptionSpireAtGoal(VimionicAbominationEntity summoner)
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
			if(this.summoner.getSpireCooldown() <= 10)
			{
				summoner.setSummoningEntity(true);
			}
			else if(summoner.isSummoningEntity())
			{
				summoner.setSummoningEntity(false);
			}
			if(this.summoner.getSpireCooldown() == 1)
			{
				World worldIn = this.summoner.world;
				EntityAbsorptionSpire newSpire = new EntityAbsorptionSpire(worldIn, this.summoner);
				LivingEntity entity = summoner.getAttackTarget();
				newSpire.setPosition(VTranslate.getEntityX(entity) + Math.random() * MushroomsMathUtil.StaticMinusPlus() * 2, VTranslate.getEntityY(entity) + 0.7, VTranslate.getEntityZ(entity) + Math.random() * 2 * MushroomsMathUtil.StaticMinusPlus());
				worldIn.addEntity(newSpire);
				summoner.playSound(ModSoundEvents.absorption_pillar_summon, 1, 0);
			}
		}
	}
}
