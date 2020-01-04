package mushroommantoad.mmpmod.blocks;

import mushroommantoad.mmpmod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.IPlantable;

public class VimionBushBlock extends Block implements IPlantable
{
	public VimionBushBlock(Properties properties) 
	{
		super(properties);
	}
	
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) 
	{
		Block block = state.getBlock();
		return block == ModBlocks.vimionic_grass_block || block == ModBlocks.necrionic_grass_block || block == ModBlocks.solarionic_grass_block || block == ModBlocks.nihilionic_grass_block || block == ModBlocks.expionic_grass_block;
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) 
	{
		return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) 
	{
		BlockPos blockpos = pos.down();
		return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}

	@Override
	public BlockRenderLayer getRenderLayer() 
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) 
	{
		return true;
	}

	@Override
	public BlockState getPlant(IBlockReader world, BlockPos pos) 
	{
		BlockState state = world.getBlockState(pos);
		if (state.getBlock() != this) return getDefaultState();
		return state;
	}
}
