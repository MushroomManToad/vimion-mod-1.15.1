package mushroommantoad.mmpmod.util.registry;

import mushroommantoad.mmpmod.Main;
import mushroommantoad.mmpmod.particles.flame.BlackFlameParticle;
import mushroommantoad.mmpmod.particles.flame.BlueFlameParticle;
import mushroommantoad.mmpmod.particles.flame.OrangeFlameParticle;
import mushroommantoad.mmpmod.particles.flame.PurpleFlameParticle;
import mushroommantoad.mmpmod.particles.flame.RedFlameParticle;
import mushroommantoad.mmpmod.particles.flame.WhiteFlameParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


// Deferred Registry because I *cannot* work out the wrong way to do this. Modernization begins here.

@EventBusSubscriber(modid = Main.modid, bus = Bus.MOD)
public class ParticleRegistry 
{
	public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Main.modid);
	
	public static final RegistryObject<BasicParticleType> BLUE_FLAME = PARTICLES.register("blue_flame", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> RED_FLAME = PARTICLES.register("red_flame", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> ORANGE_FLAME = PARTICLES.register("orange_flame", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> WHITE_FLAME = PARTICLES.register("white_flame", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> BLACK_FLAME = PARTICLES.register("black_flame", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> PURPLE_FLAME = PARTICLES.register("purple_flame", () -> new BasicParticleType(true));
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void registerParticles(ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particles.registerFactory(BLUE_FLAME.get(), BlueFlameParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(RED_FLAME.get(), RedFlameParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(ORANGE_FLAME.get(), OrangeFlameParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(WHITE_FLAME.get(), WhiteFlameParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(BLACK_FLAME.get(), BlackFlameParticle.Factory::new);
		Minecraft.getInstance().particles.registerFactory(PURPLE_FLAME.get(), PurpleFlameParticle.Factory::new);
	}
}
