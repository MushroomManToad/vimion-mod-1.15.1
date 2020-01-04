package mushroommantoad.mmpmod.blocks;

import java.util.Random;

import mushroommantoad.mmpmod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

public class VimionOreBlock extends Block
{
	public VimionOreBlock(Properties properties) 
	{
		super(properties);
	}
	
	private int calcExp(Random rand)
	{
		if(this == ModBlocks.vimionite_ore)
			return MathHelper.nextInt(rand, 0, 2);
		else if(this == ModBlocks.necrionite_ore || this == ModBlocks.solarionite_ore || this == ModBlocks.nihilionite_ore || this == ModBlocks.expionite_ore)
			return MathHelper.nextInt(rand, 3, 7);
		else
			return 0;
	}

	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) 
	{
		return silktouch == 0 ? calcExp(RANDOM) : 0;
	}
}
