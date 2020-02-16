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
import mushroommantoad.mmpmod.entities.spectral.soul.SpectralSoulEntity;
import mushroommantoad.mmpmod.entities.spectral.sprite.SpectralSpriteEntity;
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
	public static EntityType<SpectralSheepEntity> SPECTRAL_SHEEP;
	public static EntityType<SpectralCowEntity> SPECTRAL_COW;
	public static EntityType<SpectralChickenEntity> SPECTRAL_CHICKEN;
	public static EntityType<SpectralPigEntity> SPECTRAL_PIG;
	public static EntityType<SpectralRabbitEntity> SPECTRAL_RABBIT;
	public static EntityType<SpectralSpriteEntity> SPECTRAL_SPRITE;
	public static EntityType<SpectralSoulEntity> SPECTRAL_SOUL;
	
	public static EntityType<VimionicAbominationEntity> VIMIONIC_ABOMINATION;
	public static EntityType<ExpionicAbominationEntity> EXPIONIC_ABOMINATION;
	
	public static EntityType<EntityAbsorptionSpire> ABSORPTION_SPIRE;
	
	public static Item spectral_sheep_egg;
	public static Item spectral_cow_egg;
	public static Item spectral_chicken_egg;
	public static Item spectral_pig_egg;
	public static Item spectral_rabbit_egg;
	
	@SuppressWarnings("unchecked")
	public static void registerAll(Register<EntityType<?>> event, Logger logger) 
	{
		event.getRegistry().registerAll
		(
			SPECTRAL_SHEEP = (EntityType<SpectralSheepEntity>) EntityType.Builder.create(SpectralSheepEntity::new, EntityClassification.CREATURE).size(0.9F, 1.3F).build(Main.modid + ":spectral_sheep").setRegistryName(location("spectral_sheep")),
			SPECTRAL_COW = (EntityType<SpectralCowEntity>) EntityType.Builder.create(SpectralCowEntity::new, EntityClassification.CREATURE).size(0.9F, 1.4F).build(Main.modid + ":spectral_cow").setRegistryName(location("spectral_cow")),
			SPECTRAL_CHICKEN = (EntityType<SpectralChickenEntity>) EntityType.Builder.create(SpectralChickenEntity::new, EntityClassification.CREATURE).size(0.4F, 0.7F).build(Main.modid + ":spectral_chicken").setRegistryName(location("spectral_chicken")),
			SPECTRAL_PIG = (EntityType<SpectralPigEntity>) EntityType.Builder.create(SpectralPigEntity::new, EntityClassification.CREATURE).size(0.9F, 0.9F).build(Main.modid + ":spectral_pig").setRegistryName(location("spectral_pig")),
			SPECTRAL_RABBIT = (EntityType<SpectralRabbitEntity>) EntityType.Builder.create(SpectralRabbitEntity::new, EntityClassification.CREATURE).size(0.4F, 0.5F).build(Main.modid + ":spectral_rabbit").setRegistryName(location("spectral_rabbit")),
			SPECTRAL_SPRITE = (EntityType<SpectralSpriteEntity>) EntityType.Builder.create(SpectralSpriteEntity::new, EntityClassification.AMBIENT).size(0.3F, 0.3F).build(Main.modid + ":spectral_sprite").setRegistryName(location("spectral_sprite")),
			SPECTRAL_SOUL = (EntityType<SpectralSoulEntity>) EntityType.Builder.create(SpectralSoulEntity::new, EntityClassification.AMBIENT).size(0.3F, 0.3F).build(Main.modid + ":spectral_soul").setRegistryName(location("spectral_soul")),
			VIMIONIC_ABOMINATION = (EntityType<VimionicAbominationEntity>) EntityType.Builder.create(VimionicAbominationEntity::new, EntityClassification.MONSTER).size(0.5f, 2.9f).build(Main.modid + ":vimionic_abomination").setRegistryName(location("vimionic_abomination")),
			EXPIONIC_ABOMINATION = (EntityType<ExpionicAbominationEntity>) EntityType.Builder.create(ExpionicAbominationEntity::new, EntityClassification.MONSTER).size(0.5f, 2.1f).build(Main.modid + ":expionic_abomination").setRegistryName(location("expionic_abomination")),
			ABSORPTION_SPIRE = (EntityType<EntityAbsorptionSpire>) EntityType.Builder.create(EntityAbsorptionSpire::new, EntityClassification.MISC).size(0.5f, 2.4f).setCustomClientFactory((spawnEntity,world) -> new EntityAbsorptionSpire(world)).setShouldReceiveVelocityUpdates(true).build(Main.modid + ":absorption_spire").setRegistryName(location("absorption_spire"))
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
