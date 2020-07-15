package mushroommantoad.mmpmod.entities.spectral.soul;

import java.util.List;

import mushroommantoad.mmpmod.entities.spectral.ISpectralEntity;
import mushroommantoad.mmpmod.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class SpectralSoulEntity extends CreatureEntity implements ISpectralEntity
{	
	private static final DataParameter<String> RENDER = EntityDataManager.createKey(SpectralSoulEntity.class, DataSerializers.STRING);
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(ModItems.vimion_shard, ModItems.necrion_shard);
	
	LivingEntity intEnt;
	@OnlyIn(Dist.CLIENT)
	EntityModel<LivingEntity> intMod;
	
	public SpectralSoulEntity(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super(type, worldIn);
	}
	
	// USE TO DEBUG. this.world is the World object; test for isRemote or !isRemote.
	@Override
	public void tick() 
	{
		super.tick();
	}
	
	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) 
	{
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getItem() == ModItems.vimion_shard || stack.getItem() == ModItems.necrion_shard)
		{
			if(!this.world.isRemote)
			{
				stack.shrink(1);
				List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(this.getPosX() - 64, this.getPosY() - 64, this.getPosZ() - 64, this.getPosX() + 64, this.getPosY() + 64, this.getPosZ() + 64));
				
				for(LivingEntity e : entities)
				{
					if(e.isEntityUndead())
					{
						e.setRevengeTarget(this);
						if(e instanceof PhantomEntity)
						{
							PhantomEntity phantom = (PhantomEntity) e;
							phantom.setAttackTarget(this);
						}
					}
				}
			}
		}
		return super.processInteract(player, hand);
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) 
	{
		List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(this.getPosX() - 64, this.getPosY() - 64, this.getPosZ() - 64, this.getPosX() + 64, this.getPosY() + 64, this.getPosZ() + 64));
		
		for(LivingEntity e : entities)
		{
			if(e.isEntityUndead())
			{
				e.setRevengeTarget(this);
				if(e instanceof PhantomEntity)
				{
					PhantomEntity phantom = (PhantomEntity) e;
					phantom.setAttackTarget(this);
				}
			}
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	// Handles internal data and AI
	@Override
	public void registerGoals() 
	{
	      this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.goalSelector.addGoal(1, new TemptGoal(this, 1.1D, TEMPTATION_ITEMS, false));
	      this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
	      this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
	      this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0.5D);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}
	
	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) 
	{
		return false;
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) 
	{
		return false;
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
	// Handles internal data and AI
	
	// Handles Size
	@Override
	public void recalculateSize() 
	{
		double d0 = this.getPosX();
		double d1 = this.getPosY();
		double d2 = this.getPosZ();
		super.recalculateSize();
		this.setPosition(d0, d1, d2);
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) 
	{
		if(intEnt != null)
		return intEnt.getSize(poseIn);
		else return super.getSize(poseIn);
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getEyeHeight(Pose p_213307_1_)
	{
		if(intEnt != null)
		return intEnt.getEyeHeight();
		else return super.getEyeHeight();
	}
	// Handles Size
	
	
	/**
	 * Gets the internal string used to identify the Entity that the Spectral Soul represents
	 * @return String
	 */
	public String getRender()
	{
		return this.dataManager.get(RENDER);
	}
	
	public void setRender(String s) 
	{
		this.dataManager.set(RENDER, s);
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();
		this.dataManager.register(RENDER, "minecraft:skeleton");
	}
	
	/**
	 * Call to get the Entity that the Spectral Soul represents
	 * @return LivingEntity
	 */
	public LivingEntity getInternalEntity()
	{
		if(intEnt == null)
		{
			intEnt = gIE();
			recalculateSize();
		}
		return intEnt;
	}
	
	/**
	 * Call to get the Model of the Entity that the Spectral Soul represents
	 * @return EntityModel<LivingEntity>
	 */
	@OnlyIn(Dist.CLIENT)
	public EntityModel<LivingEntity> getInternalModel()
	{
		if(intMod == null)
		{
			intMod = gIM();
		}
		return intMod;
	}
	
	/**
	 * Returns the model. Very resource intensive; Avoid using if getInternalModel() is a valid alternative.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	@OnlyIn(Dist.CLIENT)
	public EntityModel<LivingEntity> gIM()
	{
		for (EntityRenderer<?> renderer : Minecraft.getInstance().getRenderManager().renderers.values()) 
		{
			if(renderer == Minecraft.getInstance().getRenderManager().getRenderer(getInternalEntity()) && renderer instanceof LivingRenderer)
			{
				EntityModel<LivingEntity> m = (EntityModel<LivingEntity>) ((LivingRenderer<?, ?>)renderer).getEntityModel();
				return m;
			}
		}
		return new CreeperModel();
	}
	
	/**
	 * Returns the entity. Very resource intensive; Avoid using if getInternalEntity() is a valid alternative.
	 */
	public LivingEntity gIE()
	{
		String s = this.getRender();
		for(EntityType<?> t : ForgeRegistries.ENTITIES)
		{
			Entity e = t.create(world);
			if(e instanceof LivingEntity && s.equalsIgnoreCase(t.getRegistryName().toString()))
			{
				return (LivingEntity) e;
			}
		}
		return new CreeperEntity(EntityType.CREEPER, this.world);
	}
}
