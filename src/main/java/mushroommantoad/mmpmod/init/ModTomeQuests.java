package mushroommantoad.mmpmod.init;

import java.util.ArrayList;

import mushroommantoad.mmpmod.Main;
import mushroommantoad.mmpmod.tomequests.TomeQuest;
import mushroommantoad.mmpmod.tomequests.VTIDs;
import mushroommantoad.mmpmod.tomequests.clearcons.EmptyClearCondition;
import mushroommantoad.mmpmod.tomequests.clearcons.ItemCraftedClearCondition;
import mushroommantoad.mmpmod.tomequests.clearcons.ItemPickupClearCondition;
import mushroommantoad.mmpmod.tomequests.clearcons.ItemUsedOnBlockClearCondition;
import net.minecraft.util.ResourceLocation;

public class ModTomeQuests 
{
	public static ArrayList<TomeQuest> TOMEQUESTS = new ArrayList<>();
	
	public static void registerAll()
	{
		TOMEQUESTS.add(new TomeQuest(VTIDs.VIMIONIC_TOME, "vimion", 0, 10, 69, new EmptyClearCondition(), new ResourceLocation(Main.modid + ":textures/item/vimionic_tome.png"), VTIDs.NONE));
		TOMEQUESTS.add(new TomeQuest(VTIDs.VIMION_GEMSTONE, "vimion", 0, 40, 69, new ItemCraftedClearCondition(ModItems.vimion_gemstone), new ResourceLocation(Main.modid + ":textures/gui/icon/vimion_gemstone.png"), VTIDs.VIMIONIC_TOME));
		TOMEQUESTS.add(new TomeQuest(VTIDs.CRAFT_ADVANCED_GEOLOGIC_PHASER, "vimion", 0, 70, 97, new ItemCraftedClearCondition(ModItems.advanced_geologic_phaser), new ResourceLocation(Main.modid + ":textures/gui/icon/advanced_geologic_phaser.png"), VTIDs.VIMION_GEMSTONE));
		TOMEQUESTS.add(new TomeQuest(VTIDs.CRAFT_VIMIONIC_DAGGER, "vimion", 0, 70, 41, new ItemCraftedClearCondition(ModItems.vimionite_dagger), new ResourceLocation(Main.modid + ":textures/item/vimionite_dagger.png"), VTIDs.VIMION_GEMSTONE));
		TOMEQUESTS.add(new TomeQuest(VTIDs.OBTAIN_MOB_SPIRIT, "vimion", 0, 138, 37, new ItemPickupClearCondition(ModItems.chicken_spirit, ModItems.cow_spirit, ModItems.pig_spirit, ModItems.rabbit_spirit, ModItems.sheep_spirit), new ResourceLocation(Main.modid + ":textures/gui/icon/vimion_spirit.png"), VTIDs.CRAFT_VIMIONIC_DAGGER));
		TOMEQUESTS.add(new TomeQuest(VTIDs.HARVEST_A_BERRY, "vimion", 0, 138, 93, new ItemPickupClearCondition(ModItems.berries_of_life, ModItems.berries_of_death, ModItems.berries_of_the_sun, ModItems.berries_of_annihilation, ModItems.berries_of_the_universe), new ResourceLocation(Main.modid + ":textures/item/berries_of_life.png"), VTIDs.CRAFT_ADVANCED_GEOLOGIC_PHASER));
		
		TOMEQUESTS.add(new TomeQuest(VTIDs.NECRION_GEMSTONE, "necrion", 0, 10, 68, new ItemCraftedClearCondition(ModItems.necrion_gemstone), new ResourceLocation(Main.modid + ":textures/gui/icon/necrion_gemstone.png"), VTIDs.CRAFT_ADVANCED_GEOLOGIC_PHASER));
		TOMEQUESTS.add(new TomeQuest(VTIDs.CRAFT_NECRIONITE_SUMMONER, "necrion", 0, 40, 96, new ItemCraftedClearCondition(ModItems.necrionite_summoner), new ResourceLocation(Main.modid + ":textures/gui/icon/necrionite_summoner.png"), VTIDs.NECRION_GEMSTONE, VTIDs.OBTAIN_MOB_SPIRIT));
		TOMEQUESTS.add(new TomeQuest(VTIDs.CRAFT_NECRION_HOE, "necrion", 0, 40, 40, new ItemCraftedClearCondition(ModItems.necrionite_hoe), new ResourceLocation(Main.modid + ":textures/gui/icon/necrionite_hoe.png"), VTIDs.NECRION_GEMSTONE));
		TOMEQUESTS.add(new TomeQuest(VTIDs.RESURRECT_A_FARM_ANIMAL, "necrion", 0, 70, 96, new ItemUsedOnBlockClearCondition(ModBlocks.necrionite_summoner.getDefaultState(), ModItems.chicken_spirit, ModItems.cow_spirit, ModItems.pig_spirit, ModItems.rabbit_spirit, ModItems.sheep_spirit), new ResourceLocation(Main.modid + ":textures/gui/icon/spectral_sheep.png"), VTIDs.CRAFT_NECRIONITE_SUMMONER));
	
		TOMEQUESTS.add(new TomeQuest(VTIDs.SOLARION_GEMSTONE, "solarion", 0, 10, 67, new ItemCraftedClearCondition(ModItems.solarion_gemstone), new ResourceLocation(Main.modid + ":textures/gui/icon/solarion_gemstone.png"), VTIDs.CRAFT_ADVANCED_GEOLOGIC_PHASER));
		TOMEQUESTS.add(new TomeQuest(VTIDs.CRAFT_SOLARION_PICKAXE, "solarion", 0, 39, 40, new ItemCraftedClearCondition(ModItems.solarionite_pickaxe), new ResourceLocation(Main.modid + ":textures/gui/icon/solarionite_pickaxe.png"), VTIDs.SOLARION_GEMSTONE));
		
		TOMEQUESTS.add(new TomeQuest(VTIDs.NIHILION_GEMSTONE, "nihilion", 0, 10, 66, new ItemCraftedClearCondition(ModItems.nihilion_gemstone), new ResourceLocation(Main.modid + ":textures/gui/icon/nihilion_gemstone.png"), VTIDs.CRAFT_ADVANCED_GEOLOGIC_PHASER));
		TOMEQUESTS.add(new TomeQuest(VTIDs.CRAFT_NIHILION_AXE, "nihilion", 0, 40, 38, new ItemCraftedClearCondition(ModItems.nihilionite_axe), new ResourceLocation(Main.modid + ":textures/gui/icon/nihilionite_axe.png"), VTIDs.NIHILION_GEMSTONE));
		
		TOMEQUESTS.add(new TomeQuest(VTIDs.EXPION_GEMSTONE, "expion", 0, 10, 65, new ItemCraftedClearCondition(ModItems.expion_gemstone), new ResourceLocation(Main.modid + ":textures/gui/icon/expion_gemstone.png"), VTIDs.CRAFT_ADVANCED_GEOLOGIC_PHASER));
		TOMEQUESTS.add(new TomeQuest(VTIDs.CRAFT_EXPION_SHOVEL, "expion", 0, 40, 37, new ItemCraftedClearCondition(ModItems.expionite_spade), new ResourceLocation(Main.modid + ":textures/gui/icon/expionite_spade.png"), VTIDs.EXPION_GEMSTONE));
	}
}
