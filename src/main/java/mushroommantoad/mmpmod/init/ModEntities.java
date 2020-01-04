package mushroommantoad.mmpmod.init;

import org.apache.logging.log4j.Logger;

import mushroommantoad.mmpmod.Main;
import mushroommantoad.mmpmod.entities.boss.expionic_abomination.ExpionicAbominationEntity;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.VimionicAbominationEntity;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire.EntityAbsorptionSpire;
import mushroommantoad.mmpmod.entities.spectral.chicken.SpectralChickenEntity;
import mushroommantoad.mmpmod.entities.spectral.cow.SpectralCowEntity;
import mushroommantoad.mmpmod.entities.spectral.pig.SpectralPigEntity;
import mushroommantoad.mmpmod.entities.spectral.rabbit.SpectralRabbitEntity;
import mushroommantoad.mmpmod.entities.spectral.sheep.SpectralSheepEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;

public class ModEntities 
{
	public static EntityType<?> SPECTRAL_SHEEP = EntityType.Builder.create(SpectralSheepEntity::new, EntityClassification.CREATURE).size(0.9F, 1.3F).build(Main.modid + ":spectral_sheep").setRegistryName(location("spectral_sheep"));
	public static EntityType<?> SPECTRAL_COW = EntityType.Builder.create(SpectralCowEntity::new, EntityClassification.CREATURE).size(0.9F, 1.4F).build(Main.modid + ":spectral_cow").setRegistryName(location("spectral_cow"));
	public static EntityType<?> SPECTRAL_CHICKEN = EntityType.Builder.create(SpectralChickenEntity::new, EntityClassification.CREATURE).size(0.4F, 0.7F).build(Main.modid + ":spectral_chicken").setRegistryName(location("spectral_chicken"));
	public static EntityType<?> SPECTRAL_PIG = EntityType.Builder.create(SpectralPigEntity::new, EntityClassification.CREATURE).size(0.9F, 0.9F).build(Main.modid + ":spectral_pig").setRegistryName(location("spectral_pig"));
	public static EntityType<?> SPECTRAL_RABBIT = EntityType.Builder.create(SpectralRabbitEntity::new, EntityClassification.CREATURE).size(0.4F, 0.5F).build(Main.modid + ":spectral_rabbit").setRegistryName(location("spectral_rabbit"));
	
	public static EntityType<?> VIMIONIC_ABOMINATION = EntityType.Builder.create(VimionicAbominationEntity::new, EntityClassification.MONSTER).size(0.5f, 2.9f).build(Main.modid + ":vimionic_abomination").setRegistryName(location("vimionic_abomination"));
	public static EntityType<?> EXPIONIC_ABOMINATION = EntityType.Builder.create(ExpionicAbominationEntity::new, EntityClassification.MONSTER).size(0.5f, 2.1f).build(Main.modid + ":expionic_abomination").setRegistryName(location("expionic_abomination"));
	
	public static EntityType<?> ABSORPTION_SPIRE = EntityType.Builder.create(EntityAbsorptionSpire::new, EntityClassification.MISC).size(0.5f, 2.4f).setCustomClientFactory((spawnEntity,world) -> new EntityAbsorptionSpire(world)).setShouldReceiveVelocityUpdates(true).build(Main.modid + ":absorption_spire").setRegistryName(location("absorption_spire"));
	
	public static Item spectral_sheep_egg;
	public static Item spectral_cow_egg;
	public static Item spectral_chicken_egg;
	public static Item spectral_pig_egg;
	public static Item spectral_rabbit_egg;
	
	public static void registerAll(Register<EntityType<?>> event, Logger logger) 
	{
		event.getRegistry().registerAll
		(
			SPECTRAL_SHEEP,
			SPECTRAL_COW,
			SPECTRAL_CHICKEN,
			SPECTRAL_PIG,
			SPECTRAL_RABBIT,
			VIMIONIC_ABOMINATION,
			EXPIONIC_ABOMINATION,
			ABSORPTION_SPIRE
		);
		
		registerEntityWorldSpawns();
	}
	
	
	public static void registerEntityWorldSpawns()
	{
		//registerEntityWorldSpawn(SPECTRAL_SHEEP, Biomes.PLAINS, Biomes.BAMBOO_JUNGLE);
	}
	
	public static void registerEntityWorldSpawn(EntityType<?> entity, Biome...biomes)
	{
		for(Biome biome : biomes)
		{
			if(biome != null)
			{
				biome.getSpawns(entity.getClassification()).add(new SpawnListEntry(entity, 1, 1, 1));
			}
		}
	}
	
	public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
				spectral_sheep_egg = registerEntitySpawnEgg(SPECTRAL_SHEEP, 0x94ffd8, 0x94ffef, "spectral_sheep_spawn_egg"),
				spectral_cow_egg = registerEntitySpawnEgg(SPECTRAL_COW, 0x94ffd8, 0x94ffef, "spectral_cow_spawn_egg"),
				spectral_chicken_egg = registerEntitySpawnEgg(SPECTRAL_CHICKEN, 0x94ffd8, 0x94ffef, "spectral_chicken_spawn_egg"),
				spectral_pig_egg = registerEntitySpawnEgg(SPECTRAL_PIG, 0x94ffd8, 0x94ffef, "spectral_pig_spawn_egg"),
				spectral_rabbit_egg = registerEntitySpawnEgg(SPECTRAL_RABBIT, 0x94ffd8, 0x94ffef, "spectral_rabbit_spawn_egg")
		);
	}
	
	public static Item registerEntitySpawnEgg(EntityType<?> type, int primaryColor, int secondaryColor, String name)
	{
		SpawnEggItem item = new SpawnEggItem(type, primaryColor, secondaryColor, new Item.Properties().group(ItemGroup.MISC));
		item.setRegistryName(ModItems.location(name));
		return item;
	}
	
	public static ResourceLocation location(String name)
	{
		return new ResourceLocation(Main.modid, name);
	}
}
