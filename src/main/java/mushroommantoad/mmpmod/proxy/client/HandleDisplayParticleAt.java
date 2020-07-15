package mushroommantoad.mmpmod.proxy.client;

import java.util.Random;

import mushroommantoad.mmpmod.init.ModBlocks;
import mushroommantoad.mmpmod.init.ModParticles;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import mushroommantoad.mmpmod.util.MushroomsUtil;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HandleDisplayParticleAt 
{
	@SuppressWarnings("resource")
	public static void display(int id, double sX, double sY, double sZ, int extra)
	{
		World worldIn = Minecraft.getInstance().world;
		
		switch(id)
		{
		case(0) : 
			for(double x = sX - 0.5; x <= sX + 0.5; x = x + Math.random() / 1.2)
				for(double y = sY; y <= sY + 3; y = y + Math.random() / 1.2)
					for(double z = sZ -0.5; z <= sZ + 0.5; z = z + Math.random() / 1.2)
					{
						worldIn.addParticle(new BlockParticleData(ParticleTypes.FALLING_DUST, Blocks.BLUE_SHULKER_BOX.getDefaultState()), x, y, z, 0.0D, 0.0D, 0.0D);
					}
		 break;
		case(1) :
			worldIn.addParticle(ParticleTypes.EXPLOSION, sX, sY, sZ, 0.0D, 0.0D, 0.0D);
		 break;
		case(2) :
			for(double x = sX - 0.5; x <= sX + 0.5; x = x + Math.random() / 1.2)
				for(double y = sY; y <= sY + 3; y = y + Math.random() / 1.2)
					for(double z = sZ -0.5; z <= sZ + 0.5; z = z + Math.random() / 1.2)
					{
						worldIn.addParticle(new BlockParticleData(ParticleTypes.FALLING_DUST, ModBlocks.expion_block.getDefaultState()), x, y, z, 0.0D, 0.0D, 0.0D);
					}
		 break;
		case(3) :
			worldIn.playSound(sX, sY, sZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 1, (float) 1.3, true);
			worldIn.addParticle(ParticleTypes.EXPLOSION_EMITTER, sX, sY, sZ, 0.0D, 0.0D, 0.0D);
		 break;
		case(4) :
			BasicParticleType type = ParticleTypes.FLAME;
			switch(extra)
			{
			case(1):type = ModParticles.BLUE_FLAME;	break;
			case(2):type = ModParticles.RED_FLAME; break;
			case(3):type = ModParticles.ORANGE_FLAME; break;
			case(4):type = ModParticles.WHITE_FLAME; break;
			case(5):type = ModParticles.BLACK_FLAME; break;
			case(6):type = ModParticles.PURPLE_FLAME; break;
			default:break;
			}
			for(int i = 0; i < 7; ++i) {
				worldIn.addParticle(type, getPosRand(sX, 3D), sY + 2.0 + (2 * Math.random() * MushroomsUtil.StaticMinusPlus()), getPosRand(sZ, 3D), 0, 0, 0);
			}
		 break;
		case(5):
 			Random rand = new Random();
			worldIn.playSound(sX + 0.5D, sY + 0.5D, sZ + 0.5D, ModSoundEvents.solarionic_abomination_ascend, SoundCategory.HOSTILE, 1.0F + rand.nextFloat(), 1.0f, false);
			break;
		case(6):
			// Maybe draw a line here or open a flame portal or sth?
 			Random rand2 = new Random();
			worldIn.playSound(sX + 0.5D, sY + 0.5D, sZ + 0.5D, ModSoundEvents.solarionic_abomination_solarblast, SoundCategory.HOSTILE, 1.0F + rand2.nextFloat(), 1.5f, false);
			default:break;
		}
	}
	
	public static double getPosRand(double base, double modif)
	{
		Random rand = new Random();
		return base + (double)1.0 * ((2.0D * rand.nextDouble() - 1.0D) * modif);
	}
}
