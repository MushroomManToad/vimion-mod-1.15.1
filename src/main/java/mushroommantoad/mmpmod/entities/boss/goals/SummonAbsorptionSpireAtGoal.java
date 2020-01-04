package mushroommantoad.mmpmod.entities.boss.goals;

import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.VimionicAbominationEntity;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire.EntityAbsorptionSpire;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import mushroommantoad.mmpmod.util.MushroomsMathUtil;
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
		else return false;
	}
	
	
	@Override
	public void tick() 
	{
		if(!this.summoner.world.isRemote)
		{
			if(this.summoner.getSpireCooldown() == 1)
			{
				World worldIn = this.summoner.world;
				EntityAbsorptionSpire newSpire = new EntityAbsorptionSpire(worldIn, this.summoner);
				LivingEntity entity = summoner.getAttackTarget();
				newSpire.setPosition(entity.posX + Math.random() * MushroomsMathUtil.StaticMinusPlus() * 2, entity.posY + 0.7, entity.posZ + Math.random() * 2 * MushroomsMathUtil.StaticMinusPlus());
				worldIn.addEntity(newSpire);
				summoner.playSound(ModSoundEvents.absorption_pillar_summon, 1, 0);
			}
		}
	}
}
