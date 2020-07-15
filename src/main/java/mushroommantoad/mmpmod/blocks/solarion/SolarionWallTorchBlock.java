package mushroommantoad.mmpmod.blocks.solarion;

import java.util.Random;

import mushroommantoad.mmpmod.init.ModBlocks;
import mushroommantoad.mmpmod.init.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SolarionWallTorchBlock extends WallTorchBlock
{
	public SolarionWallTorchBlock(Properties properties) {
		super(properties);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		Direction direction = stateIn.get(HORIZONTAL_FACING);
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d3 = 0.22D;
		double d4 = 0.27D;
		Direction direction1 = direction.getOpposite();
		worldIn.addParticle(ParticleTypes.SMOKE, d0 + d4 * (double)direction1.getXOffset(), d1 + d3, d2 + d4 * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		BasicParticleType type = ModParticles.BLUE_FLAME;
		if(stateIn.getBlock() == ModBlocks.red_stellar_wall_torch) type = ModParticles.RED_FLAME;
		if(stateIn.getBlock() == ModBlocks.orange_stellar_wall_torch) type = ModParticles.ORANGE_FLAME;
		if(stateIn.getBlock() == ModBlocks.white_stellar_wall_torch) type = ModParticles.WHITE_FLAME;
		if(stateIn.getBlock() == ModBlocks.black_stellar_wall_torch) type = ModParticles.BLACK_FLAME;
		if(stateIn.getBlock() == ModBlocks.purple_stellar_wall_torch) type = ModParticles.PURPLE_FLAME;
		worldIn.addParticle(type, d0 + d4 * (double)direction1.getXOffset(), d1 + d3, d2 + d4 * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
	}
}
