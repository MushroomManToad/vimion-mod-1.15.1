package mushroommantoad.mmpmod.items;

import java.util.List;
import java.util.Random;

import mushroommantoad.mmpmod.blocks.necrion.NecrioniteSummonerBlock;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModItems;
import mushroommantoad.mmpmod.util.MushroomsUtil;
import net.minecraft.block.AirBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSpirit extends Item
{
	public ItemSpirit(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		Random rand = new Random();
		
		if(worldIn.isRemote)
		{
			if(worldIn.getBlockState(pos).getBlock() instanceof NecrioniteSummonerBlock)
			{
				for(int i = 0; i < 100; i++)
				{
					worldIn.addParticle(ParticleTypes.DRAGON_BREATH, (pos.getX() + 0.5), (pos.getY() + 0.1 + (0.05 * i)), (pos.getZ() + 0.5), 0, 0, 0);
				}
				for(double x = -3; x < 3; x = x + 0.1)
				{
					for(double y = -3; y < 3; y = y + 0.1)
					{
						if(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) < 2)
						{
							worldIn.addParticle(ParticleTypes.SQUID_INK, (pos.getX() + x + 0.5), (pos.getY() + 5.1), (pos.getZ() + y + 0.5), 0, 0, 0);
						}
					}
				}
			}
		}
		
		if(!worldIn.isRemote)
		{
			if(worldIn.getBlockState(pos).getBlock() instanceof NecrioniteSummonerBlock)
			{
				BlockPos nextPos = this.computeNextPos(worldIn, pos, rand);
				
				LivingEntity entity = new CowEntity(EntityType.COW, worldIn);
				
				if(this == ModItems.chicken_spirit) entity = ModEntities.SPECTRAL_CHICKEN.create(worldIn);
				if(this == ModItems.cow_spirit) entity = ModEntities.SPECTRAL_COW.create(worldIn);
				if(this == ModItems.pig_spirit) entity = ModEntities.SPECTRAL_PIG.create(worldIn);
				if(this == ModItems.rabbit_spirit) entity = ModEntities.SPECTRAL_RABBIT.create(worldIn);
				if(this == ModItems.sheep_spirit) entity = ModEntities.SPECTRAL_SHEEP.create(worldIn);
				
				entity.setPosition(nextPos.getX(), nextPos.getY(), nextPos.getZ());
				worldIn.addEntity(entity);
				
				List<LivingEntity> entities = worldIn.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(entity.getPosX() - 64, entity.getPosY() - 64, entity.getPosZ() - 64, entity.getPosX() + 64, entity.getPosY() + 64, entity.getPosZ() + 64));
				
				for(LivingEntity e : entities)
				{
					if(e.isEntityUndead())
					{
						e.setRevengeTarget(entity);
						if(e instanceof PhantomEntity)
						{
							PhantomEntity phantom = (PhantomEntity) e;
							phantom.setAttackTarget(entity);
						}
					}
				}
				
				context.getItem().shrink(1);
				
				// Send packet to spawn particles with the summoner's pos and nextPos;
				worldIn.playSound(null, nextPos.getX(), nextPos.getY(), nextPos.getZ(), SoundEvents.BLOCK_BELL_RESONATE, SoundCategory.BLOCKS, 16, -1);
				worldIn.playSound(null, nextPos.getX(), nextPos.getY(), nextPos.getZ(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 16, 0);
				worldIn.playSound(null, nextPos.getX(), nextPos.getY(), nextPos.getZ(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 16, 0);
				worldIn.playSound(null, nextPos.getX(), nextPos.getY(), nextPos.getZ(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 16, 0);
				
				return ActionResultType.SUCCESS;
			}
		}
		
		return super.onItemUse(context);
	}
	
	public BlockPos computeNextPos(World worldIn, BlockPos pos, Random rand)
	{
		BlockPos testPos = new BlockPos(pos);
		for(int i = 0; i < 32; i++)
		{
			testPos = pos.add(rand.nextInt(5) * MushroomsUtil.StaticMinusPlus(), rand.nextInt(2) * MushroomsUtil.StaticMinusPlus(), rand.nextInt(5) * MushroomsUtil.StaticMinusPlus());
			if(!(worldIn.getBlockState(testPos).getBlock() instanceof AirBlock) && worldIn.getBlockState(testPos.add(0, 1, 0)).getBlock() instanceof AirBlock)
			{
				return testPos.add(0, 1, 0);
			}
			testPos = pos;
		}
		return pos.add(0, 1, 0);
	}
}
