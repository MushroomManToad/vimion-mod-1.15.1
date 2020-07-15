package mushroommantoad.mmpmod.entities.boss.goals.solarionic_abomination;

import javax.annotation.Nullable;

import mushroommantoad.mmpmod.entities.boss.solarionic_abomination.SolarionicAbominationEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.util.math.Vec3d;

public class SolarionicAbominationWalkGoal extends RandomWalkingGoal {
	protected final float probability;
	SolarionicAbominationEntity summoner;	
	
	public SolarionicAbominationWalkGoal(SolarionicAbominationEntity creature, double speedIn) {
		this(creature, speedIn, 0.001F);
		this.summoner = creature;
	}

	public SolarionicAbominationWalkGoal(SolarionicAbominationEntity creature, double speedIn, float probabilityIn) {
		super(creature, speedIn);
		this.probability = probabilityIn;
		this.summoner = creature;
	}
	
	public boolean shouldExecute() {
		if(this.summoner.isLunarionic()) {
			return false;
		}
		if (this.creature.isBeingRidden()) {
			return false;
		} else {
			if (!this.mustUpdate) {
				if (this.creature.getIdleTime() >= 100) {
					return false;
		        }

		        if (this.creature.getRNG().nextInt(this.executionChance) != 0) {
		        	return false;
		        }
		    }

			Vec3d vec3d = this.getPosition();
			if (vec3d == null) {
				return false;
			} else {
				this.x = vec3d.x;
				this.y = vec3d.y;
				this.z = vec3d.z;
				this.mustUpdate = false;
				return true;
			}
		}
	}

	@Nullable
	protected Vec3d getPosition() {
		if (this.creature.isInWaterOrBubbleColumn()) {
			Vec3d vec3d = RandomPositionGenerator.getLandPos(this.creature, 15, 7);
			return vec3d == null ? super.getPosition() : vec3d;
		} else {
			return this.creature.getRNG().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.creature, 10, 7) : super.getPosition();
		}
	}
}