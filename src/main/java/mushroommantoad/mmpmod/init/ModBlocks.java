package mushroommantoad.mmpmod.init;

import org.apache.logging.log4j.Logger;

import mushroommantoad.mmpmod.Main;
import mushroommantoad.mmpmod.blocks.VimionBerryBushBlock;
import mushroommantoad.mmpmod.blocks.VimionGrassBlock;
import mushroommantoad.mmpmod.blocks.VimionOreBlock;
import mushroommantoad.mmpmod.blocks.expion.crate.BlockExpionCrate;
import mushroommantoad.mmpmod.blocks.necrion.NecrioniteSummonerBlock;
import mushroommantoad.mmpmod.blocks.solarion.SolarionTorchBlock;
import mushroommantoad.mmpmod.blocks.solarion.SolarionWallTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;

public class ModBlocks 
{
	public static Block vimion_block;
	public static Block concealed_vimionite_ore;
	public static Block vimionite_ore;
	
	public static Block necrion_block;
	public static Block concealed_necrionite_ore;
	public static Block necrionite_ore;
	
	public static Block solarion_block;
	public static Block concealed_solarionite_ore;
	public static Block solarionite_ore;
	
	public static Block nihilion_block;
	public static Block concealed_nihilionite_ore;
	public static Block nihilionite_ore;
	
	public static Block expion_block;
	public static Block concealed_expionite_ore;
	public static Block expionite_ore;
	
	public static Block vimionic_grass_block;
	public static Block necrionic_grass_block;
	public static Block solarionic_grass_block;
	public static Block nihilionic_grass_block;
	public static Block expionic_grass_block;
	
	public static Block berries_of_life_bush;
	public static Block berries_of_death_bush;
	public static Block berries_of_the_sun_bush;
	public static Block berries_of_annihilation_bush;
	public static Block berries_of_the_universe_bush;
	
	public static Block blue_stellar_torch;
	public static Block red_stellar_torch;
	public static Block orange_stellar_torch;
	public static Block white_stellar_torch;
	public static Block black_stellar_torch;
	public static Block purple_stellar_torch;
	
	public static Block blue_stellar_wall_torch;
	public static Block red_stellar_wall_torch;
	public static Block orange_stellar_wall_torch;
	public static Block white_stellar_wall_torch;
	public static Block black_stellar_wall_torch;
	public static Block purple_stellar_wall_torch;
	
	public static Block necrionite_summoner;
	public static Block expionite_crate;
	
	public static void registerAll(RegistryEvent.Register<Block> event, Logger logger)
	{
		event.getRegistry().registerAll
		(
				vimion_block = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).lightValue(15).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("vimion_block")),
				concealed_vimionite_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0)).setRegistryName(location("concealed_vimionite_ore")),
				vimionite_ore = new VimionOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("vimionite_ore")),
				necrion_block = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).lightValue(15).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("necrion_block")),
				concealed_necrionite_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0)).setRegistryName(location("concealed_necrionite_ore")),
				necrionite_ore = new VimionOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("necrionite_ore")),
				solarion_block = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).lightValue(15).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("solarion_block")),
				concealed_solarionite_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0)).setRegistryName(location("concealed_solarionite_ore")),
				solarionite_ore = new VimionOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("solarionite_ore")),
				nihilion_block = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).lightValue(15).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("nihilion_block")),
				concealed_nihilionite_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0)).setRegistryName(location("concealed_nihilionite_ore")),
				nihilionite_ore = new VimionOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("nihilionite_ore")),
				expion_block = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).lightValue(15).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("expion_block")),
				concealed_expionite_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0)).setRegistryName(location("concealed_expionite_ore")),
				expionite_ore = new VimionOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("expionite_ore")),
				
				vimionic_grass_block = new VimionGrassBlock(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 0.5F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL).harvestLevel(-1)).setRegistryName(location("vimionic_grass_block")),
				necrionic_grass_block = new VimionGrassBlock(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 0.5F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL).harvestLevel(-1)).setRegistryName(location("necrionic_grass_block")),
				solarionic_grass_block = new VimionGrassBlock(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 0.5F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL).harvestLevel(-1)).setRegistryName(location("solarionic_grass_block")),
				nihilionic_grass_block = new VimionGrassBlock(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 0.5F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL).harvestLevel(-1)).setRegistryName(location("nihilionic_grass_block")),
				expionic_grass_block = new VimionGrassBlock(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 0.5F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL).harvestLevel(-1)).setRegistryName(location("expionic_grass_block")),
				
				berries_of_life_bush = new VimionBerryBushBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH)).setRegistryName(location("berries_of_life_bush")),
				berries_of_death_bush = new VimionBerryBushBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH)).setRegistryName(location("berries_of_death_bush")),
				berries_of_the_sun_bush = new VimionBerryBushBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH)).setRegistryName(location("berries_of_the_sun_bush")),
				berries_of_annihilation_bush = new VimionBerryBushBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH)).setRegistryName(location("berries_of_annihilation_bush")),
				berries_of_the_universe_bush = new VimionBerryBushBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH)).setRegistryName(location("berries_of_the_universe_bush")),
				
				blue_stellar_torch = new SolarionTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName(location("blue_stellar_torch")),
				red_stellar_torch = new SolarionTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName(location("red_stellar_torch")),
				orange_stellar_torch = new SolarionTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName(location("orange_stellar_torch")),
				white_stellar_torch = new SolarionTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(15).sound(SoundType.WOOD)).setRegistryName(location("white_stellar_torch")),
				black_stellar_torch = new SolarionTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(0).sound(SoundType.WOOD)).setRegistryName(location("black_stellar_torch")),
				purple_stellar_torch = new SolarionTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD)).setRegistryName(location("purple_stellar_torch")),
				
				blue_stellar_wall_torch = new SolarionWallTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD).lootFrom(blue_stellar_torch)).setRegistryName(location("blue_stellar_wall_torch")),
				red_stellar_wall_torch = new SolarionWallTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD).lootFrom(red_stellar_torch)).setRegistryName(location("red_stellar_wall_torch")),
				orange_stellar_wall_torch = new SolarionWallTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD).lootFrom(orange_stellar_torch)).setRegistryName(location("orange_stellar_wall_torch")),
				white_stellar_wall_torch = new SolarionWallTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(15).sound(SoundType.WOOD).lootFrom(white_stellar_torch)).setRegistryName(location("white_stellar_wall_torch")),
				black_stellar_wall_torch = new SolarionWallTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(0).sound(SoundType.WOOD).lootFrom(black_stellar_torch)).setRegistryName(location("black_stellar_wall_torch")),
				purple_stellar_wall_torch = new SolarionWallTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(14).sound(SoundType.WOOD).lootFrom(purple_stellar_torch)).setRegistryName(location("purple_stellar_wall_torch")),
				
				necrionite_summoner = new NecrioniteSummonerBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("necrionite_summoner")),
				expionite_crate = new BlockExpionCrate(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 15f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2)).setRegistryName(location("expionite_crate"))
		);
		
		logger.info("Blocks Registered");
	}
	
	private static ResourceLocation location(String name)
	{
		return new ResourceLocation(Main.modid, name);
	}
}
