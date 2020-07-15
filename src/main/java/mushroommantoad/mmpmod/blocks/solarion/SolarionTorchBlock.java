package mushroommantoad.mmpmod.blocks.solarion;

import java.util.Random;

import mushroommantoad.mmpmod.init.ModBlocks;
import mushroommantoad.mmpmod.init.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SolarionTorchBlock extends TorchBlock
{
	public SolarionTorchBlock(Properties properties)
	{
		super(properties);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
		worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		BasicParticleType type = ModParticles.BLUE_FLAME;
		if(stateIn.getBlock() == ModBlocks.red_stellar_torch) type = ModParticles.RED_FLAME;
		if(stateIn.getBlock() == ModBlocks.orange_stellar_torch) type = ModParticles.ORANGE_FLAME;
		if(stateIn.getBlock() == ModBlocks.white_stellar_torch) type = ModParticles.WHITE_FLAME;
		if(stateIn.getBlock() == ModBlocks.black_stellar_torch) type = ModParticles.BLACK_FLAME;
		if(stateIn.getBlock() == ModBlocks.purple_stellar_torch) type = ModParticles.PURPLE_FLAME;
		worldIn.addParticle(type, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
}
