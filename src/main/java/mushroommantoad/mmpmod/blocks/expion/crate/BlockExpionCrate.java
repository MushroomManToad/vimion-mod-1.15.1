package mushroommantoad.mmpmod.blocks.expion.crate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockExpionCrate extends Block
{

	public BlockExpionCrate(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) 
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new TileEntityExpionCrate();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
	{
		if(placer != null)
		{
			worldIn.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(placer)), 2);
		}
	}
	
	public static Direction getFacingFromEntity(LivingEntity entityIn)
	{
		Direction dir = Direction.UP;
		if(entityIn.rotationPitch > 60) dir = Direction.UP;
		else if(entityIn.rotationPitch < -60) dir = Direction.DOWN;
		else dir = entityIn.getHorizontalFacing();
		return dir;
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		builder.add(BlockStateProperties.FACING);
	}
}
