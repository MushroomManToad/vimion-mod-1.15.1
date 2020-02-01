package mushroommantoad.mmpmod.init;

import org.apache.logging.log4j.Logger;

import mushroommantoad.mmpmod.Main;
import mushroommantoad.mmpmod.items.ItemAdvancedGeologicPhaser;
import mushroommantoad.mmpmod.items.ItemEnergizedGemstone;
import mushroommantoad.mmpmod.items.ItemExpioniteShovel;
import mushroommantoad.mmpmod.items.ItemGeologicPhaser;
import mushroommantoad.mmpmod.items.ItemMemoryCrystal;
import mushroommantoad.mmpmod.items.ItemNecrioniteHoe;
import mushroommantoad.mmpmod.items.ItemNihilionAxe;
import mushroommantoad.mmpmod.items.ItemSolarionPickaxe;
import mushroommantoad.mmpmod.items.ItemSpirit;
import mushroommantoad.mmpmod.items.ItemVimionBerries;
import mushroommantoad.mmpmod.items.ItemVimionDagger;
import mushroommantoad.mmpmod.items.ItemVimionShard;
import mushroommantoad.mmpmod.items.ItemVimionicNote;
import mushroommantoad.mmpmod.items.ItemVimioniteTome;
import mushroommantoad.mmpmod.items.util.VimionFoods;
import mushroommantoad.mmpmod.lists.ItemTierList;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class ModItems 
{
	public static Item vimion_shard;
	public static Item vimion_gemstone;
	public static Item energized_vimion;
	public static Item necrion_shard;
	public static Item necrion_gemstone;
	public static Item energized_necrion;
	public static Item solarion_shard;
	public static Item solarion_gemstone;
	public static Item energized_solarion;
	public static Item nihilion_shard;
	public static Item nihilion_gemstone;
	public static Item energized_nihilion;
	public static Item expion_shard;
	public static Item expion_gemstone;
	public static Item energized_expion;
	
	public static Item vimioplasm;
	public static Item necrioplasm;
	public static Item solarioplasm;
	public static Item nihilioplasm;
	public static Item expioplasm;
	
	public static Item memory_crystal;
	
	public static Item vimionic_tome;
	public static Item vimionic_note;
	public static Item geologic_phaser;
	public static Item advanced_geologic_phaser;
	
	public static Item vimionite_dagger;
	public static Item necrionite_hoe;
	public static Item solarionite_pickaxe;
	public static Item nihilionite_axe;
	public static Item expionite_spade;
	
	public static Item vimion_block;
	public static Item concealed_vimionite_ore;
	public static Item vimionite_ore;
	public static Item necrion_block;
	public static Item concealed_necrionite_ore;
	public static Item necrionite_ore;
	public static Item solarion_block;
	public static Item concealed_solarionite_ore;
	public static Item solarionite_ore;
	public static Item nihilion_block;
	public static Item concealed_nihilionite_ore;
	public static Item nihilionite_ore;
	public static Item expion_block;
	public static Item concealed_expionite_ore;
	public static Item expionite_ore;
	
	public static Item berries_of_life;
	public static Item berries_of_death;
	public static Item berries_of_the_sun;
	public static Item berries_of_annihilation;
	public static Item berries_of_the_universe;
	
	public static Item berries_of_life_bush;
	public static Item berries_of_death_bush;
	public static Item berries_of_the_sun_bush;
	public static Item berries_of_annihilation_bush;
	public static Item berries_of_the_universe_bush;
	
	public static Item vimionic_grass_block;
	public static Item necrionic_grass_block;
	public static Item solarionic_grass_block;
	public static Item nihilionic_grass_block;
	public static Item expionic_grass_block;
	
	public static Item necrionite_summoner;
	public static Item expionite_crate;
	
	public static Item sheep_spirit;
	public static Item cow_spirit;
	public static Item pig_spirit;
	public static Item rabbit_spirit;
	public static Item chicken_spirit;
	
	public static void registerAll(RegistryEvent.Register<Item> event, Logger logger)
	{
		event.getRegistry().registerAll
		(
				vimion_shard = new ItemVimionShard(new Item.Properties().group(Main.vimion)).setRegistryName(location("vimion_shard")),
				vimion_gemstone = new Item(new Item.Properties().group(Main.vimion)).setRegistryName(location("vimion_gemstone")),
				energized_vimion = new ItemEnergizedGemstone(new Item.Properties().group(Main.vimion)).setRegistryName(location("energized_vimion")),
				necrion_shard = new ItemVimionShard(new Item.Properties().group(Main.vimion)).setRegistryName(location("necrion_shard")),
				necrion_gemstone = new Item(new Item.Properties().group(Main.vimion)).setRegistryName(location("necrion_gemstone")),
				energized_necrion = new ItemEnergizedGemstone(new Item.Properties().group(Main.vimion)).setRegistryName(location("energized_necrion")),
				solarion_shard = new ItemVimionShard(new Item.Properties().group(Main.vimion)).setRegistryName(location("solarion_shard")),
				solarion_gemstone = new Item(new Item.Properties().group(Main.vimion)).setRegistryName(location("solarion_gemstone")),
				energized_solarion = new ItemEnergizedGemstone(new Item.Properties().group(Main.vimion)).setRegistryName(location("energized_solarion")),
				nihilion_shard = new ItemVimionShard(new Item.Properties().group(Main.vimion)).setRegistryName(location("nihilion_shard")),
				nihilion_gemstone = new Item(new Item.Properties().group(Main.vimion)).setRegistryName(location("nihilion_gemstone")),
				energized_nihilion = new ItemEnergizedGemstone(new Item.Properties().group(Main.vimion)).setRegistryName(location("energized_nihilion")),
				expion_shard = new ItemVimionShard(new Item.Properties().group(Main.vimion)).setRegistryName(location("expion_shard")),
				expion_gemstone = new Item(new Item.Properties().group(Main.vimion)).setRegistryName(location("expion_gemstone")),
				energized_expion = new ItemEnergizedGemstone(new Item.Properties().group(Main.vimion)).setRegistryName(location("energized_expion")),
				
				vimioplasm = new Item(new Item.Properties().group(Main.vimion)).setRegistryName(location("vimioplasm")),
				necrioplasm = new Item(new Item.Properties().group(Main.vimion)).setRegistryName(location("necrioplasm")),
				solarioplasm = new Item(new Item.Properties().group(Main.vimion)).setRegistryName(location("solarioplasm")),
				nihilioplasm = new Item(new Item.Properties().group(Main.vimion)).setRegistryName(location("nihilioplasm")),
				expioplasm = new Item(new Item.Properties().group(Main.vimion)).setRegistryName(location("expioplasm")),
				
				memory_crystal = new ItemMemoryCrystal(new Item.Properties().group(Main.vimion)).setRegistryName(location("memory_crystal")),
				
				vimionic_tome = new ItemVimioniteTome(new Item.Properties().group(Main.vimion)).setRegistryName(location("vimionic_tome")),
				vimionic_note = new ItemVimionicNote(new Item.Properties().group(Main.vimion)).setRegistryName(location("vimionic_note")),
				geologic_phaser = new ItemGeologicPhaser(new Item.Properties().group(Main.vimion).maxDamage(250)).setRegistryName(location("geologic_phaser")),
				advanced_geologic_phaser = new ItemAdvancedGeologicPhaser(new Item.Properties().group(Main.vimion).maxDamage(1000)).setRegistryName(location("advanced_geologic_phaser")),
				
				sheep_spirit = new ItemSpirit(new Item.Properties().group(Main.vimion)).setRegistryName(location("sheep_spirit")),
				cow_spirit = new ItemSpirit(new Item.Properties().group(Main.vimion)).setRegistryName(location("cow_spirit")),
				pig_spirit = new ItemSpirit(new Item.Properties().group(Main.vimion)).setRegistryName(location("pig_spirit")),
				rabbit_spirit = new ItemSpirit(new Item.Properties().group(Main.vimion)).setRegistryName(location("rabbit_spirit")),
				chicken_spirit = new ItemSpirit(new Item.Properties().group(Main.vimion)).setRegistryName(location("chicken_spirit")),
				
				vimionite_dagger = new ItemVimionDagger(ItemTierList.vimionite, 0, -0.6f, new Item.Properties().group(Main.vimion)).setRegistryName(location("vimionite_dagger")),
				necrionite_hoe = new ItemNecrioniteHoe(ItemTierList.necrionite, 0, new Item.Properties().group(Main.vimion)).setRegistryName(location("necrionite_hoe")),
				solarionite_pickaxe = new ItemSolarionPickaxe(ItemTierList.solarionite, 0, -3.4f, new Item.Properties().group(Main.vimion)).setRegistryName(location("solarionite_pickaxe")),
				nihilionite_axe = new ItemNihilionAxe(ItemTierList.nihilionite, 0, -3.4f, new Item.Properties().group(Main.vimion)).setRegistryName(location("nihilionite_axe")),
				expionite_spade = new ItemExpioniteShovel(ItemTierList.expionite, 0, -3.4f, new Item.Properties().group(Main.vimion)).setRegistryName(location("expionite_spade")),
				
				berries_of_life = new ItemVimionBerries(new Item.Properties().group(Main.vimion).food(VimionFoods.VIMION_BERRY)).setRegistryName(location("berries_of_life")),
				berries_of_death = new ItemVimionBerries(new Item.Properties().group(Main.vimion).food(VimionFoods.NECRION_BERRY)).setRegistryName(location("berries_of_death")),
				berries_of_the_sun = new ItemVimionBerries(new Item.Properties().group(Main.vimion).food(VimionFoods.SOLARION_BERRY)).setRegistryName(location("berries_of_the_sun")),
				berries_of_annihilation = new ItemVimionBerries(new Item.Properties().group(Main.vimion).food(VimionFoods.NIHILION_BERRY)).setRegistryName(location("berries_of_annihilation")),
				berries_of_the_universe = new ItemVimionBerries(new Item.Properties().group(Main.vimion).food(VimionFoods.EXPION_BERRY)).setRegistryName(location("berries_of_the_universe")),
				
				berries_of_life_bush = new BlockItem(ModBlocks.berries_of_life_bush, new Item.Properties()).setRegistryName(location("berries_of_life_bush")),
				berries_of_death_bush = new BlockItem(ModBlocks.berries_of_death_bush, new Item.Properties()).setRegistryName(location("berries_of_death_bush")),
				berries_of_the_sun_bush = new BlockItem(ModBlocks.berries_of_the_sun_bush, new Item.Properties()).setRegistryName(location("berries_of_the_sun_bush")),
				berries_of_annihilation_bush = new BlockItem(ModBlocks.berries_of_annihilation_bush, new Item.Properties()).setRegistryName(location("berries_of_annihilation_bush")),
				berries_of_the_universe_bush = new BlockItem(ModBlocks.berries_of_the_universe_bush, new Item.Properties()).setRegistryName(location("berries_of_the_universe_bush")),
				
				vimionic_grass_block = new BlockItem(ModBlocks.vimionic_grass_block, new Item.Properties().group(Main.vimion)).setRegistryName(location("vimionic_grass_block")),
				necrionic_grass_block = new BlockItem(ModBlocks.necrionic_grass_block, new Item.Properties().group(Main.vimion)).setRegistryName(location("necrionic_grass_block")),
				solarionic_grass_block = new BlockItem(ModBlocks.solarionic_grass_block, new Item.Properties().group(Main.vimion)).setRegistryName(location("solarionic_grass_block")),
				nihilionic_grass_block = new BlockItem(ModBlocks.nihilionic_grass_block, new Item.Properties().group(Main.vimion)).setRegistryName(location("nihilionic_grass_block")),
				expionic_grass_block = new BlockItem(ModBlocks.expionic_grass_block, new Item.Properties().group(Main.vimion)).setRegistryName(location("expionic_grass_block")),
				
				expionite_crate = new BlockItem(ModBlocks.expionite_crate, new Item.Properties().group(Main.vimion)).setRegistryName(location("expionite_crate")),
				necrionite_summoner = new BlockItem(ModBlocks.necrionite_summoner, new Item.Properties().group(Main.vimion)).setRegistryName(location("necrionite_summoner")),
				
				vimion_block = new BlockItem(ModBlocks.vimion_block, new Item.Properties().group(Main.vimion)).setRegistryName(location("vimion_block")),
				concealed_vimionite_ore = new BlockItem(ModBlocks.concealed_vimionite_ore, new Item.Properties().group(Main.vimion)).setRegistryName(location("concealed_vimionite_ore")),
				vimionite_ore = new BlockItem(ModBlocks.vimionite_ore, new Item.Properties().group(Main.vimion)).setRegistryName(location("vimionite_ore")),
				necrion_block = new BlockItem(ModBlocks.necrion_block, new Item.Properties().group(Main.vimion)).setRegistryName(location("necrion_block")),
				concealed_necrionite_ore = new BlockItem(ModBlocks.concealed_necrionite_ore, new Item.Properties().group(Main.vimion)).setRegistryName(location("concealed_necrionite_ore")),
				necrionite_ore = new BlockItem(ModBlocks.necrionite_ore, new Item.Properties().group(Main.vimion)).setRegistryName(location("necrionite_ore")),
				solarion_block = new BlockItem(ModBlocks.solarion_block, new Item.Properties().group(Main.vimion)).setRegistryName(location("solarion_block")),
				concealed_solarionite_ore = new BlockItem(ModBlocks.concealed_solarionite_ore, new Item.Properties().group(Main.vimion)).setRegistryName(location("concealed_solarionite_ore")),
				solarionite_ore = new BlockItem(ModBlocks.solarionite_ore, new Item.Properties().group(Main.vimion)).setRegistryName(location("solarionite_ore")),
				nihilion_block = new BlockItem(ModBlocks.nihilion_block, new Item.Properties().group(Main.vimion)).setRegistryName(location("nihilion_block")),
				concealed_nihilionite_ore = new BlockItem(ModBlocks.concealed_nihilionite_ore, new Item.Properties().group(Main.vimion)).setRegistryName(location("concealed_nihilionite_ore")),
				nihilionite_ore = new BlockItem(ModBlocks.nihilionite_ore, new Item.Properties().group(Main.vimion)).setRegistryName(location("nihilionite_ore")),
				expion_block = new BlockItem(ModBlocks.expion_block, new Item.Properties().group(Main.vimion)).setRegistryName(location("expion_block")),
				concealed_expionite_ore = new BlockItem(ModBlocks.concealed_expionite_ore, new Item.Properties().group(Main.vimion)).setRegistryName(location("concealed_expionite_ore")),
				expionite_ore = new BlockItem(ModBlocks.expionite_ore, new Item.Properties().group(Main.vimion)).setRegistryName(location("expionite_ore"))
		);
		
		ModEntities.registerEntitySpawnEggs(event);
		
		logger.info("Items Registered");
	}
	
	public static ResourceLocation location(String name)
	{
		return new ResourceLocation(Main.modid, name);
	}
}
