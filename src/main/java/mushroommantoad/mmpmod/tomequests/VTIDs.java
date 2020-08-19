package mushroommantoad.mmpmod.tomequests;

import mushroommantoad.mmpmod.Main;

public class VTIDs 
{
	public static String NONE = location("none");
	public static String VIMIONIC_TOME = location("getTome");
	public static String VIMION_GEMSTONE = location("vimionGemstone");
	public static String CRAFT_ADVANCED_GEOLOGIC_PHASER = location("advancedPhaser");
	public static String CRAFT_VIMIONIC_DAGGER = location("vimionDagger");
	public static String OBTAIN_MOB_SPIRIT = location("mobSpirit");
	public static String OBTAIN_ALL_MOB_SPIRITS = location("challengeMobSpirits");
	public static String HARVEST_A_BERRY = location("harvestBerry");
	
	public static String NECRION_GEMSTONE = location("necrionGemstone");
	public static String CRAFT_NECRION_HOE = location("necrionHoe");
	public static String CRAFT_NECRIONITE_SUMMONER = location("necrionSummoner");
	public static String RESURRECT_A_FARM_ANIMAL = location("resurrectAnimal");
	
	public static String SOLARION_GEMSTONE = location("solarionGemstone");
	public static String CRAFT_SOLARION_PICKAXE = location("solarionPickaxe");
	
	public static String NIHILION_GEMSTONE = location("nihilionGemstone");
	public static String CRAFT_NIHILION_AXE = location("nihilionAxe");
	
	public static String EXPION_GEMSTONE = location("expionGemstone");
	public static String CRAFT_EXPION_SHOVEL = location("expionShovel");
	
	public static String location(String name)
	{
		return "tomequest." + Main.modid + "." + name;
	}
}
