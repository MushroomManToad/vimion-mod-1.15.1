package mushroommantoad.mmpmod.blocks;

import java.util.Random;

import mushroommantoad.mmpmod.init.ModBlocks;
import mushroommantoad.mmpmod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class VimionBerryBushBlock extends VimionBushBlock implements IGrowable
{
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
	private static final VoxelShape SMALL_BERRY_HITBOX = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape FULL_BERRY_HITBOX = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	
	public VimionBerryBushBlock(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
	}
	
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) 
	{
		if(state.getBlock() == ModBlocks.berries_of_life_bush) return new ItemStack(ModItems.berries_of_life);
		else if(state.getBlock() == ModBlocks.berries_of_death_bush) return new ItemStack(ModItems.berries_of_death);
		else if(state.getBlock() == ModBlocks.berries_of_the_sun_bush) return new ItemStack(ModItems.berries_of_the_sun);
		else if(state.getBlock() == ModBlocks.berries_of_annihilation_bush) return new ItemStack(ModItems.berries_of_annihilation);
		else if(state.getBlock() == ModBlocks.berries_of_the_universe_bush) return new ItemStack(ModItems.berries_of_the_universe);
		else return new ItemStack(ModItems.berries_of_life);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		if (state.get(AGE) == 0) 
		{
			return SMALL_BERRY_HITBOX;
		} 
		else 
		{
			return state.get(AGE) < 3 ? FULL_BERRY_HITBOX : super.getShape(state, worldIn, pos, context);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) 
	{
		super.tick(state, worldIn, pos, random);
		int i = state.get(AGE);
		if (i < 3 && random.nextInt(5) == 0 && worldIn.getLightSubtracted(pos.up(), 0) >= 9) 
		{
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
		}
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) 
	{
		if (entityIn instanceof LivingEntity && entityIn.getType() != EntityType.FOX) 
		{
			if(!(state.getBlock() == ModBlocks.berries_of_the_universe_bush)) entityIn.setMotionMultiplier(state, new Vec3d((double)0.8F, 0.75D, (double)0.8F));
			if(state.getBlock() == ModBlocks.berries_of_the_universe_bush && entityIn instanceof PlayerEntity) entityIn.setMotionMultiplier(state, new Vec3d((double)0.01F, 0.01D, (double)0.01F));
			if (!worldIn.isRemote && state.get(AGE) > 0 && (entityIn.lastTickPosX != entityIn.posX || entityIn.lastTickPosZ != entityIn.posZ)) 
			{
				double d0 = Math.abs(entityIn.posX - entityIn.lastTickPosX);
				double d1 = Math.abs(entityIn.posZ - entityIn.lastTickPosZ);
				if (d0 >= (double)0.003F || d1 >= (double)0.003F) 
				{
					if(state.getBlock() == ModBlocks.berries_of_life_bush && ((LivingEntity) entityIn).isEntityUndead()) ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.WITHER, 40));
					if(state.getBlock() == ModBlocks.berries_of_death_bush && !((LivingEntity) entityIn).isEntityUndead()) ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.WITHER, 40));
					if(state.getBlock() == ModBlocks.berries_of_the_sun_bush) entityIn.setFire(2);
					if(state.getBlock() == ModBlocks.berries_of_annihilation_bush) entityIn.attackEntityFrom(DamageSource.SWEET_BERRY_BUSH, 4.0F);
					if(state.getBlock() == ModBlocks.berries_of_the_universe_bush) entityIn.setMotionMultiplier(state, new Vec3d((double)0.01F, 0.05D, (double)0.01F));
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) 
	{
		int i = state.get(AGE);
		boolean flag = i == 3;
		if (!flag && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) 
		{
			return false;
		} 
		else if (i > 1) 
		{
			int j = 1 + worldIn.rand.nextInt(2);
			if(Math.random() < 0.25)
			{
				if(state.getBlock() == ModBlocks.berries_of_life_bush) spawnAsEntity(worldIn, pos, new ItemStack(ModItems.berries_of_life, 1));
				if(state.getBlock() == ModBlocks.berries_of_death_bush) spawnAsEntity(worldIn, pos, new ItemStack(ModItems.berries_of_death, 1));
				if(state.getBlock() == ModBlocks.berries_of_the_sun_bush) spawnAsEntity(worldIn, pos, new ItemStack(ModItems.berries_of_the_sun, 1));
				if(state.getBlock() == ModBlocks.berries_of_annihilation_bush) spawnAsEntity(worldIn, pos, new ItemStack(ModItems.berries_of_annihilation, 1));
				if(state.getBlock() == ModBlocks.berries_of_the_universe_bush) spawnAsEntity(worldIn, pos, new ItemStack(ModItems.berries_of_the_universe, 1));
			}
			spawnAsEntity(worldIn, pos, new ItemStack(Items.SWEET_BERRIES, j + (flag ? 1 : 0)));
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(1)), 2);
			return true;
		} 
		else 
		{
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override	   
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(AGE);
	}
		   
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) 
	{
		return state.get(AGE) < 3;
	}

	@Override		   
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		return true;
	}
		   
	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		int i = Math.min(3, state.get(AGE) + 1);
		worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i)), 2);
	}
}
