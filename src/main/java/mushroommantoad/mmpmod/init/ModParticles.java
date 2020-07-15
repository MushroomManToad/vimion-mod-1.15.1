package mushroommantoad.mmpmod.init;

import mushroommantoad.mmpmod.util.registry.ParticleRegistry;
import net.minecraft.particles.BasicParticleType;

public class ModParticles 
{
	// These are just static instances of each particle for use in methods. Registered in util.registry.ParticleRegistry
	
	public static BasicParticleType BLUE_FLAME = ParticleRegistry.BLUE_FLAME.get();
	public static BasicParticleType RED_FLAME = ParticleRegistry.RED_FLAME.get();
	public static BasicParticleType ORANGE_FLAME = ParticleRegistry.ORANGE_FLAME.get();
	public static BasicParticleType WHITE_FLAME = ParticleRegistry.WHITE_FLAME.get();
	public static BasicParticleType BLACK_FLAME = ParticleRegistry.BLACK_FLAME.get();
	public static BasicParticleType PURPLE_FLAME = ParticleRegistry.PURPLE_FLAME.get();
}
