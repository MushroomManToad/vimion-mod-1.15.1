package mushroommantoad.mmpmod.util;

import mushroommantoad.mmpmod.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VimionicTomeListener 
{
	@SubscribeEvent
	public void pickupItemAddToBookEvent(EntityItemPickupEvent event)
	{
		if(!event.getPlayer().getEntityWorld().isRemote)
		{
			Item i = event.getItem().getItem().getItem();
			
			//
			// CHAPTER: VIMION HANDLER
			//
			
			if(i == ModItems.vimion_gemstone) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_VIMION_GEMSTONE, -1, event.getPlayer());
			if(i == ModItems.chicken_spirit || i == ModItems.cow_spirit || i == ModItems.pig_spirit || i == ModItems.rabbit_spirit || i == ModItems.sheep_spirit) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_OBTAIN_MOB_SPIRIT, VTIDHandler.OBJECTIVE_CRAFT_VIMIONIC_DAGGER, event.getPlayer());
			
			//
			// CHAPTER: VIMION HANDLER
			//
			
			//
			// CHAPTER: NECRION HANDLER
			//
			
			if(i == ModItems.necrion_gemstone) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_NECRION_GEMSTONE, VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER, event.getPlayer());
			
			//
			// CHAPTER: NECRION HANDLER
			//
			
			//
			// CHAPTER: SOLARION HANDLER
			//
			
			if(i == ModItems.solarion_gemstone) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_SOLARION_GEMSTONE, VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER, event.getPlayer());
			
			//
			// CHAPTER: SOLARION HANDLER
			//
			
			//
			// CHAPTER: NIHILION HANDLER
			//
			
			if(i == ModItems.nihilion_gemstone) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_NIHILION_GEMSTONE, VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER, event.getPlayer());
			
			//
			// CHAPTER: NIHILION HANDLER
			//
			
			//
			// CHAPTER: EXPION HANDLER
			//
			
			if(i == ModItems.expion_gemstone) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_EXPION_GEMSTONE, VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER, event.getPlayer());
			
			//
			// CHAPTER: EXPION HANDLER
			//
		}
	}
	
	@SubscribeEvent
	public void craftItemAddToBookEvent(ItemCraftedEvent event)
	{
		if(!event.getPlayer().getEntityWorld().isRemote)
		{
			Item i = event.getCrafting().getItem();
			
			//
			// CHAPTER: VIMION HANDLER
			//
			
			if(i == ModItems.vimion_gemstone) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_VIMION_GEMSTONE, -1, event.getPlayer());
			if(i == ModItems.advanced_geologic_phaser) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER, VTIDHandler.OBJECTIVE_VIMION_GEMSTONE, event.getPlayer());
			if(i == ModItems.vimionite_dagger) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_CRAFT_VIMIONIC_DAGGER, VTIDHandler.OBJECTIVE_VIMION_GEMSTONE, event.getPlayer());
			
			//
			// CHAPTER: VIMION HANDLER
			//
			
			//
			// CHAPTER: NECRION HANDLER
			//
			
			if(i == ModItems.necrion_gemstone) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_NECRION_GEMSTONE, VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER, event.getPlayer());
			if(i == ModItems.necrionite_summoner) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_CRAFT_NECRIONITE_SUMMONER, VTIDHandler.OBJECTIVE_OBTAIN_MOB_SPIRIT, event.getPlayer());
			
			//
			// CHAPTER: NECRION HANDLER
			//
			
			//
			// CHAPTER: SOLARION HANDLER
			//
			
			if(i == ModItems.solarion_gemstone) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_SOLARION_GEMSTONE, VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER, event.getPlayer());
			if(i == ModItems.solarionite_pickaxe) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_CRAFT_SOLARIONITE_PICKAXE, VTIDHandler.OBJECTIVE_SOLARION_GEMSTONE, event.getPlayer());
			
			//
			// CHAPTER: SOLARION HANDLER
			//
			
			//
			// CHAPTER: NIHILION HANDLER
			//
			
			if(i == ModItems.nihilion_gemstone) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_NIHILION_GEMSTONE, VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER, event.getPlayer());
			if(i == ModItems.nihilionite_axe) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_CRAFT_NIHILIONITE_AXE, VTIDHandler.OBJECTIVE_NIHILION_GEMSTONE, event.getPlayer());
			
			//
			// CHAPTER: NIHILION HANDLER
			//
			
			//
			// CHAPTER: EXPION HANDLER
			//
			
			if(i == ModItems.expion_gemstone) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_EXPION_GEMSTONE, VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER, event.getPlayer());
			if(i == ModItems.expionite_spade) handleStandardTomeDataUpdate(VTIDHandler.OBJECTIVE_CRAFT_EXPION_SHOVEL, VTIDHandler.OBJECTIVE_EXPION_GEMSTONE, event.getPlayer());
			
			//
			// CHAPTER: EXPION HANDLER
			//
		}
	}
	
	public void handleStandardTomeDataUpdate(int objectiveID, int parentID, PlayerEntity playerIn)
	{
		CompoundNBT nbt = playerIn.getPersistentData();
		
		String tagID = "VimionAdvancements";
		if(objectiveID > 99 && objectiveID < 200) tagID = "NecrionAdvancements";
		if(objectiveID > 199 && objectiveID < 300) tagID = "SolarionAdvancements";
		if(objectiveID > 299 && objectiveID < 400) tagID = "NihilionAdvancements";
		if(objectiveID > 399 && objectiveID < 500) tagID = "ExpionAdvancements";
		
		String pID = "VimionAdvancements";
		if(parentID > 99 && parentID < 200) pID = "NecrionAdvancements";
		if(parentID > 199 && parentID < 300) pID = "SolarionAdvancements";
		if(parentID > 299 && parentID < 400) pID = "NihilionAdvancements";
		if(parentID > 399 && parentID < 500) pID = "ExpionAdvancements";
		
		if(parentID == -1 || nbt.contains(pID))
		{
			int i = parentID;
			if(nbt.contains(pID) && parentID != -1)
			{
				if(nbt.getIntArray(pID)[(int) Math.IEEEremainder(parentID, 100)] >= 1)
				{
					i = -1;
				}
			}			
			if(i == -1)
			{
				if(nbt.contains(tagID))
				{
					if(nbt.getIntArray(tagID)[(int) Math.IEEEremainder(objectiveID, 100)] == 0)
					{
						int[] value = nbt.getIntArray(tagID);
						value[(int) Math.IEEEremainder(objectiveID, 100)] = 1;
						nbt.putIntArray(tagID, value);
					}
				}
				else
				{
					int[] value = new int[100];
					value[(int) Math.IEEEremainder(objectiveID, 100)] = 1;
					nbt.putIntArray(tagID, value);
				}
			}
		}
	}
}
