package mushroommantoad.mmpmod.entities.boss.solarionic_abomination;

import java.util.Random;

import mushroommantoad.mmpmod.entities.boss.IVimionicEntity;
import mushroommantoad.mmpmod.entities.boss.goals.solarionic_abomination.SolarionicAbominationWalkGoal;
import mushroommantoad.mmpmod.entities.boss.goals.solarionic_abomination.SummonSolarBlastAtGoal;
import mushroommantoad.mmpmod.entities.boss.goals.solarionic_abomination.SunRiseGoal;
import mushroommantoad.mmpmod.entities.boss.goals.solarionic_abomination.UnleashFireBlastGoal;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModItems;
import mushroommantoad.mmpmod.init.ModParticles;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import mushroommantoad.mmpmod.util.MushroomsUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;

public class SolarionicAbominationEntity extends CreatureEntity implements IVimionicEntity
{
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(new TranslationTextComponent("bossbar.vimion.solarionic_abomination.name").applyTextStyle(TextFormatting.GOLD), BossInfo.Color.YELLOW, BossInfo.Overlay.PROGRESS)).setCreateFog(true);
	
	private static final DataParameter<Boolean> IS_LUNARIONIC = EntityDataManager.createKey(SolarionicAbominationEntity.class, DataSerializers.BOOLEAN);
	
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
	private static final DataParameter<Integer> STELLAR_TYPE = EntityDataManager.createKey(SolarionicAbominationEntity.class, DataSerializers.VARINT);
	
	private int solarBlastCooldown = 50;
	private int fireBlastCooldown = 149;
	private int sunriseBlastCooldown = 301;
	
	public SolarionicAbominationEntity(EntityType<? extends Entity> type, World worldIn) 
	{
		super(ModEntities.SOLARIONIC_ABOMINATION, worldIn);
		setStellarType(getStellarColor(this));
	}
	
	@Override
	public void registerGoals() 
	{
	      this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.goalSelector.addGoal(1, new SolarionicAbominationWalkGoal(this, 1.0D));
	      this.goalSelector.addGoal(2, new SummonSolarBlastAtGoal(this));
	      this.goalSelector.addGoal(3, new UnleashFireBlastGoal(this));
	      this.goalSelector.addGoal(4, new SunRiseGoal(this));
	      this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
	      this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		  this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
		  this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(250.0D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
	}
	
	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) 
	{
		return false;
	}
	
	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) 
	{
		return false;
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();
		this.dataManager.register(IS_LUNARIONIC, false);
		this.dataManager.register(STELLAR_TYPE, 0);
	}
	
	public boolean isLunarionic() { return this.dataManager.get(IS_LUNARIONIC); }
	public void updateIsLunarionic(boolean value) { this.dataManager.set(IS_LUNARIONIC, value);	}
	
	public int getStellarType() { return this.dataManager.get(STELLAR_TYPE); }
	public void setStellarType(int value) 
	{ 
		this.dataManager.set(STELLAR_TYPE, value);
		BossInfo.Color bc;
		TextFormatting tc;
		String bn = "bossbar.vimion.solarionic_abomination.name";
		switch(getStellarType())
		{
		case(0):
			bc = BossInfo.Color.YELLOW;
			tc = TextFormatting.YELLOW;
			break;
		case(1):
			bc = BossInfo.Color.BLUE;
			tc = TextFormatting.BLUE;
			bn = "bossbar.vimion.stellarionic_abomination.name";
			break;
		case(2):
			bc = BossInfo.Color.RED;
			tc = TextFormatting.DARK_RED;
			bn = "bossbar.vimion.stellarionic_abomination.name";
			break;
		case(3):
			bc = BossInfo.Color.RED;
			tc = TextFormatting.RED;
			bn = "bossbar.vimion.stellarionic_abomination.name";
			break;
		case(4):
			bc = BossInfo.Color.WHITE;
			tc = TextFormatting.WHITE;
			bn = "bossbar.vimion.stellarionic_abomination.name";
			break;
		case(5):
			bc = BossInfo.Color.WHITE;
			tc = TextFormatting.DARK_GRAY;
			bn = "bossbar.vimion.stellarionic_abomination.name";
			break;
		case(6):
			bc = BossInfo.Color.PURPLE;
			tc = TextFormatting.LIGHT_PURPLE;
			bn = "bossbar.vimion.stellarionic_abomination.name";
			break;
		default:
			bc = BossInfo.Color.YELLOW;
			tc = TextFormatting.YELLOW;
			break;
		};
		
		this.bossInfo.setColor(bc);
		this.bossInfo.setName(new TranslationTextComponent(bn).applyTextStyle(tc));
	}
	
	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) 
	{
		super.dropSpecialItems(source, looting, recentlyHitIn);
		Random random = new Random();
		ItemStack item = new ItemStack(ModItems.solarioplasm, random.nextInt(4) + 1);
		switch(getStellarType())
		{
		case(1):
			item = new ItemStack(ModItems.blue_stellarioplasm, random.nextInt(4) + 1);
		break;
		case(2):
			item = new ItemStack(ModItems.red_stellarioplasm, random.nextInt(4) + 1);
		break;
		case(3):
			item = new ItemStack(ModItems.orange_stellarioplasm, random.nextInt(4) + 1);
		break;
		case(4):
			item = new ItemStack(ModItems.white_stellarioplasm, random.nextInt(4) + 1);
		break;
		case(5):
			item = new ItemStack(ModItems.black_stellarioplasm, random.nextInt(4) + 1);
		break;
		case(6):
			item = new ItemStack(ModItems.purple_stellarioplasm, random.nextInt(4) + 1);
		break;
		}
		ItemEntity itementity = this.entityDropItem(item);
		itementity.setNoDespawn();
	}
	
	@Override
	public void tick()
	{
		if (this.world instanceof ServerWorld) 
		{
			solarBlastCooldown--;
			if(solarBlastCooldown == 0)
			{
				solarBlastCooldown = 50;
			}
			fireBlastCooldown--;
			if(fireBlastCooldown == 0)
			{
				fireBlastCooldown = 149;
			}
			sunriseBlastCooldown--;
			if(sunriseBlastCooldown == 0)
			{
				sunriseBlastCooldown = 301;
			}
			if(!this.isLunarionic() && this.world.isNightTime() && this.world.getDimension().getType() == DimensionType.OVERWORLD)
			{
				this.updateIsLunarionic(true);
				this.bossInfo.setColor(BossInfo.Color.WHITE);
				this.bossInfo.setName(new TranslationTextComponent("bossbar.vimion.lunarionic_abomination.name").applyTextStyle(TextFormatting.GRAY));
			}
			if(this.isLunarionic() && this.world.isDaytime() && this.world.getDimension().getType() == DimensionType.OVERWORLD)
			{
				this.updateIsLunarionic(false);
				this.bossInfo.setColor(BossInfo.Color.YELLOW);
				this.bossInfo.setName(new TranslationTextComponent("bossbar.vimion.solarionic_abomination.name").applyTextStyle(TextFormatting.YELLOW));
			}
			if(this.isLunarionic())
			{
				this.heal(1);
			}

		}
		else
		{
			
		}
		super.tick();
	}
	
	@Override
	public void livingTick() {
		if (this.world.isRemote && !this.isLunarionic()) {
	        if (this.rand.nextInt(12) == 0 && !this.isSilent()) {
	        	this.world.playSound(this.getPosX() + 0.5D, this.getPosY() + 0.5D, this.getPosZ() + 0.5D, ModSoundEvents.solarionic_abomination_burn, this.getSoundCategory(), 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
	        }
			BasicParticleType type = ParticleTypes.FLAME;
			int types = getStellarType();
			switch(types)
			{
			case(1):type = ModParticles.BLUE_FLAME; break;
			case(2):type = ModParticles.RED_FLAME; break;
			case(3):type = ModParticles.ORANGE_FLAME; break;
			case(4):type = ModParticles.WHITE_FLAME; break;
			case(5):type = ModParticles.BLACK_FLAME; break;
			case(6):type = ModParticles.PURPLE_FLAME; break;
			default:break;
			}
			for(int i = 0; i < 1; ++i) {
				this.world.addParticle(type, this.getPosXRandom(2.5D), this.getPosY() + 2.0 + (2 * Math.random() * MushroomsUtil.StaticMinusPlus()), this.getPosZRandom(2.5D), 0, 0, 0);
			}
		}
		      
		super.livingTick();
	}

	@Override
	protected SoundEvent getAmbientSound() 
	{
		return ModSoundEvents.solarionic_abomination_say;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSoundEvents.solarionic_abomination_hurt;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSoundEvents.solarionic_abomination_death;
	}
	
	@Override
	public void addTrackingPlayer(ServerPlayerEntity player)
	{
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}
	
	@Override
	protected void updateAITasks() 
	{
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		super.updateAITasks();
	}
	
	@Override
	public boolean isNonBoss() 
	{
		return false;
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) 
	{
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}
	
	public static int getStellarColor(SolarionicAbominationEntity entity)
	{
		if(entity.world.getDimension().getType() == DimensionType.OVERWORLD) return 0;
		if(entity.world.getDimension().getType() == DimensionType.THE_NETHER) return 2;
		if(entity.world.getDimension().getType() == DimensionType.THE_END) return 6;
		
		String name = entity.world.getDimension().toString();
		char[] charname = name.toCharArray();
		int i = 0;
		for(char c : charname)
		{
			switch(c)
			{
			case('a'):	i = i + 1; break;
			case('b'):	i = i + 2; break;
			case('c'):	i = i + 3; break;
			case('d'):	i = i + 4; break;
			case('e'):	i = i + 5; break;
			case('f'):	i = i + 6; break;
			case('g'):	i = i + 7; break;
			case('h'):	i = i + 8; break;
			case('i'):	i = i + 9; break;
			case('j'):	i = i + 10; break;
			case('k'):	i = i + 11; break;
			case('l'):	i = i + 12; break;
			case('m'):	i = i + 13; break;
			case('n'):	i = i + 14; break;
			case('o'):	i = i + 15; break;
			case('p'):	i = i + 16; break;
			case('q'):	i = i + 17; break;
			case('r'):	i = i + 18; break;
			case('s'):	i = i + 19; break;
			case('t'):	i = i + 20; break;
			case('u'):	i = i + 21; break;
			case('v'):	i = i + 22; break;
			case('w'):	i = i + 23; break;
			case('x'):	i = i + 24; break;
			case('y'):	i = i + 25; break;
			case('z'):	i = i + 26; break;
			case('1'):	i = i + 1; break;
			case('2'):	i = i + 2; break;
			case('3'):	i = i + 3; break;
			case('4'):	i = i + 4; break;
			case('5'):	i = i + 5; break;
			case('6'):	i = i + 6; break;
			case('7'):	i = i + 7; break;
			case('8'):	i = i + 8; break;
			case('9'):	i = i + 9; break;
			case('_'):	i = i + 27; break;
			default:;			
			}
		}
		int j = Math.floorMod(i, 5) + 1;
		return j;
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) 
	{
		return 0.5F * sizeIn.height;
	}	
	
	public int getSolarBlastCooldown()
	{
		return this.solarBlastCooldown;
	}
	
	public int getFireBlastCooldown()
	{
		return this.fireBlastCooldown;
	}
	
	public int getSunriseBlastCooldown()
	{
		return this.sunriseBlastCooldown;
	}
}
