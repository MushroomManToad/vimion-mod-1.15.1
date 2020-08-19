package mushroommantoad.mmpmod.util;

import mushroommantoad.mmpmod.init.ModTomeQuests;
import mushroommantoad.mmpmod.tomequests.TomeQuest;
import mushroommantoad.mmpmod.tomequests.clearcons.EmptyClearCondition;
import mushroommantoad.mmpmod.tomequests.clearcons.ItemCraftedClearCondition;
import mushroommantoad.mmpmod.tomequests.clearcons.ItemPickupClearCondition;
import mushroommantoad.mmpmod.tomequests.clearcons.ItemUsedOnBlockClearCondition;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@SuppressWarnings("resource")
public class VimionicTomeListener 
{
	@SubscribeEvent
	public void emptyAddToBookEvent(PlayerEvent.PlayerLoggedInEvent event)
	{
		World worldIn = event.getPlayer().world;
		if(!worldIn.isRemote && event.getPlayer() instanceof ServerPlayerEntity)
		{
			for(TomeQuest q : ModTomeQuests.TOMEQUESTS)
			{
				if(q.getClearCondition() instanceof EmptyClearCondition)
				{
					q.clearClearCon(event.getPlayer());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void pickupItemAddToBookEvent(EntityItemPickupEvent event)
	{
		if(!event.getPlayer().getEntityWorld().isRemote)
		{
			for(TomeQuest q : ModTomeQuests.TOMEQUESTS)
			{
				if(q.getClearCondition() instanceof ItemPickupClearCondition)
				{
					ItemPickupClearCondition i = (ItemPickupClearCondition) q.getClearCondition();
					for(Item item : i.getItems())
					{
						if(event.getItem().getItem().getItem() == item) q.clearClearCon(event.getPlayer());						
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void craftItemAddToBookEvent(ItemCraftedEvent event)
	{
		if(!event.getPlayer().getEntityWorld().isRemote)
		{
			for(TomeQuest q : ModTomeQuests.TOMEQUESTS)
			{
				if(q.getClearCondition() instanceof ItemCraftedClearCondition)
				{
					ItemCraftedClearCondition i = (ItemCraftedClearCondition) q.getClearCondition();
					if(event.getCrafting().getItem() == i.getResult()) q.clearClearCon(event.getPlayer());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void playerInteractionAddToBookEvent(PlayerInteractEvent event)
	{
		if(!event.getWorld().isRemote)
		{
			for(TomeQuest q : ModTomeQuests.TOMEQUESTS)
			{
				if(q.getClearCondition() instanceof ItemUsedOnBlockClearCondition)
				{
					ItemUsedOnBlockClearCondition i = (ItemUsedOnBlockClearCondition) q.getClearCondition();
					if(i.getBlockState().getBlock() == event.getWorld().getBlockState(event.getPos()).getBlock())
					{
						for(Item item : i.getItems())
						{
							if(item == event.getItemStack().getItem()) q.clearClearCon(event.getPlayer());
						}
					}
				}
			}
		}
	}
}
