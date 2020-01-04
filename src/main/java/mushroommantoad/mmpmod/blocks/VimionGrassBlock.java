package mushroommantoad.mmpmod.blocks;

import mushroommantoad.mmpmod.init.ModBlocks;
import mushroommantoad.mmpmod.items.ItemVimionBerries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class VimionGrassBlock extends Block
{

	public VimionGrassBlock(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) 
	{
		return state.getBlock() == ModBlocks.necrionic_grass_block ? true : false;
	}
	

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) 
	{
		if(worldIn.isRemote && entityIn instanceof PlayerEntity)
		{
			BlockState state = worldIn.getBlockState(pos);
			if(state.getBlock() == ModBlocks.expionic_grass_block) 
			{
				PlayerEntity e = (PlayerEntity) entityIn;
				e.setMotionMultiplier(state, new Vec3d((double)0.01F, 0.01D, (double)0.01F));
			}
		}
		if(!worldIn.isRemote)
		{
			BlockState state = worldIn.getBlockState(pos);
			if(entityIn instanceof LivingEntity)
			{
				LivingEntity e = (LivingEntity) entityIn;
				if(state.getBlock() == ModBlocks.vimionic_grass_block)
				{

					if(e.isEntityUndead())
					{
						e.addPotionEffect(new EffectInstance(Effects.WITHER, 40, 4));
					}
					else
					{
						if(!e.isPotionActive(Effects.REGENERATION)) e.addPotionEffect(new EffectInstance(Effects.REGENERATION, 60, 1, true, false));
					}
				
				}
				else if(state.getBlock() == ModBlocks.necrionic_grass_block)
				{

					if(e.isEntityUndead())
					{
						e.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 4));
					}
					else
					{
						e.addPotionEffect(new EffectInstance(Effects.WITHER, 40, 4));
					}
				
				}
				else if(state.getBlock() == ModBlocks.solarionic_grass_block)
				{
					if(e.isEntityUndead())
					{
						e.setFire(15);
					}
					else
					{
						e.setFire(2);
					}
				}
				else if(state.getBlock() == ModBlocks.nihilionic_grass_block)
				{
					entityIn.attackEntityFrom(DamageSource.MAGIC, 1.0F);
				}
				else
				{
					e.setMotionMultiplier(state, new Vec3d((double)0.01F, 0.01D, (double)0.01F));
				}
			}
		}
		super.onEntityWalk(worldIn, pos, entityIn);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) 
	{
		ItemStack stack = player.getHeldItem(handIn);
		Direction dir = hit.getFace();
		if((stack.getItem() == Items.SWEET_BERRIES || stack.getItem() instanceof ItemVimionBerries) && dir == Direction.UP && worldIn.getBlockState(pos.add(0, 1, 0)).isAir())
		{
			stack.shrink(1);
			if(state.getBlock() == ModBlocks.vimionic_grass_block) worldIn.setBlockState(pos.add(0, 1, 0), ModBlocks.berries_of_life_bush.getDefaultState());
			if(state.getBlock() == ModBlocks.necrionic_grass_block) worldIn.setBlockState(pos.add(0, 1, 0), ModBlocks.berries_of_death_bush.getDefaultState());
			if(state.getBlock() == ModBlocks.solarionic_grass_block) worldIn.setBlockState(pos.add(0, 1, 0), ModBlocks.berries_of_the_sun_bush.getDefaultState());
			if(state.getBlock() == ModBlocks.nihilionic_grass_block) worldIn.setBlockState(pos.add(0, 1, 0), ModBlocks.berries_of_annihilation_bush.getDefaultState());
			if(state.getBlock() == ModBlocks.expionic_grass_block) worldIn.setBlockState(pos.add(0, 1, 0), ModBlocks.berries_of_the_universe_bush.getDefaultState());
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
}
