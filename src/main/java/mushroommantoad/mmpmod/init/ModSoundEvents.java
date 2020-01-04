package mushroommantoad.mmpmod.init;

import org.apache.logging.log4j.Logger;

import mushroommantoad.mmpmod.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;

public class ModSoundEvents 
{
	public static final SoundEvent spectral_sheep_say = new SoundEvent(location("spectral_sheep_say"));
	public static final SoundEvent spectral_sheep_hurt = new SoundEvent(location("spectral_sheep_hurt"));
	public static final SoundEvent spectral_sheep_death = new SoundEvent(location("spectral_sheep_death"));
	public static final SoundEvent spectral_cow_say = new SoundEvent(location("spectral_cow_say"));
	public static final SoundEvent spectral_cow_hurt = new SoundEvent(location("spectral_cow_hurt"));
	public static final SoundEvent spectral_cow_death = new SoundEvent(location("spectral_cow_death"));
	public static final SoundEvent spectral_pig_say = new SoundEvent(location("spectral_pig_say"));
	public static final SoundEvent spectral_pig_hurt = new SoundEvent(location("spectral_pig_hurt"));
	public static final SoundEvent spectral_pig_death = new SoundEvent(location("spectral_pig_death"));
	public static final SoundEvent spectral_rabbit_say = new SoundEvent(location("spectral_rabbit_say"));
	public static final SoundEvent spectral_rabbit_hurt = new SoundEvent(location("spectral_rabbit_hurt"));
	public static final SoundEvent spectral_rabbit_death = new SoundEvent(location("spectral_rabbit_death"));
	public static final SoundEvent spectral_chicken_say = new SoundEvent(location("spectral_chicken_say"));
	public static final SoundEvent spectral_chicken_hurt = new SoundEvent(location("spectral_chicken_hurt"));
	public static final SoundEvent spectral_chicken_death = new SoundEvent(location("spectral_chicken_death"));
	
	public static final SoundEvent vimionic_abomination_say = new SoundEvent(location("vimionic_abomination_say"));
	public static final SoundEvent vimionic_abomination_hurt = new SoundEvent(location("vimionic_abomination_hurt"));
	public static final SoundEvent vimionic_abomination_death = new SoundEvent(location("vimionic_abomination_death"));
	public static final SoundEvent expionic_abomination_say = new SoundEvent(location("expionic_abomination_say"));
	public static final SoundEvent expionic_abomination_hurt = new SoundEvent(location("expionic_abomination_hurt"));
	public static final SoundEvent expionic_abomination_death = new SoundEvent(location("expionic_abomination_death"));
	public static final SoundEvent absorption_pillar_summon = new SoundEvent(location("absorption_pillar_summon"));
	public static final SoundEvent expionic_abomination_teleport = new SoundEvent(location("expionic_abomination_teleport"));
	
	public static void registerAll(RegistryEvent.Register<SoundEvent> event, Logger logger)
	{
		event.getRegistry().registerAll
		(
				spectral_sheep_say.setRegistryName(location("spectral_sheep_say")),
				spectral_sheep_hurt.setRegistryName(location("spectral_sheep_hurt")),
				spectral_sheep_death.setRegistryName(location("spectral_sheep_death")),
				spectral_cow_say.setRegistryName(location("spectral_cow_say")),
				spectral_cow_hurt.setRegistryName(location("spectral_cow_hurt")),
				spectral_cow_death.setRegistryName(location("spectral_cow_death")),
				spectral_pig_say.setRegistryName(location("spectral_pig_say")),
				spectral_pig_hurt.setRegistryName(location("spectral_pig_hurt")),
				spectral_pig_death.setRegistryName(location("spectral_pig_death")),
				spectral_rabbit_say.setRegistryName(location("spectral_rabbit_say")),
				spectral_rabbit_hurt.setRegistryName(location("spectral_rabbit_hurt")),
				spectral_rabbit_death.setRegistryName(location("spectral_rabbit_death")),
				spectral_chicken_say.setRegistryName(location("spectral_chicken_say")),
				spectral_chicken_hurt.setRegistryName(location("spectral_chicken_hurt")),
				spectral_chicken_death.setRegistryName(location("spectral_chicken_death")),
				vimionic_abomination_say.setRegistryName(location("vimionic_abomination_say")),
				vimionic_abomination_hurt.setRegistryName(location("vimionic_abomination_hurt")),
				vimionic_abomination_death.setRegistryName(location("vimionic_abomination_death")),
				expionic_abomination_say.setRegistryName(location("expionic_abomination_say")),
				expionic_abomination_hurt.setRegistryName(location("expionic_abomination_hurt")),
				expionic_abomination_death.setRegistryName(location("expionic_abomination_death")),
				absorption_pillar_summon.setRegistryName(location("absorption_pillar_summon")),
				expionic_abomination_teleport.setRegistryName(location("expionic_abomination_teleport"))
		);
	}
	
	public static ResourceLocation location(String name)
	{
		return new ResourceLocation(Main.modid, name);
	}
}
