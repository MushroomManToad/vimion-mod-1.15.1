package mushroommantoad.mmpmod.world;

import mushroommantoad.mmpmod.init.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration 
{
	public static void setupOreGeneration()
	{
		for(Biome biome : ForgeRegistries.BIOMES)
		{
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ModBlocks.concealed_vimionite_ore.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(16, 5, 0, 16)));
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ModBlocks.concealed_necrionite_ore.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 5, 0, 16)));
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ModBlocks.concealed_solarionite_ore.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 5, 0, 16)));
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ModBlocks.concealed_nihilionite_ore.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 5, 0, 16)));
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ModBlocks.concealed_expionite_ore.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 5, 0, 16)));
		}
	}
}
