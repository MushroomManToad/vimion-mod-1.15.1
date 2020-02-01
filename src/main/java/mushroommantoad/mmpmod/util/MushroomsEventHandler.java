package mushroommantoad.mmpmod.util;

import mushroommantoad.mmpmod.entities.boss.expionic_abomination.ExpionicAbominationEntity;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.VimionicAbominationEntity;
import mushroommantoad.mmpmod.entities.spectral.ISpectralEntity;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModItems;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MushroomsEventHandler 
{
	@SubscribeEvent
    public void tickEvent(TickEvent.PlayerTickEvent event) 
    {
		//if(event.player.getPersistentData().getIntArray("VimionAdvancements").length > 0)
		//System.out.println(event.player.getPersistentData().getIntArray("VimionAdvancements")[0]);
    }
	
	@SubscribeEvent
	public void playerFirstJoinEvent(PlayerEvent.PlayerLoggedInEvent event)
	{
		World worldIn = event.getPlayer().world;
		if(!worldIn.isRemote && event.getPlayer() instanceof ServerPlayerEntity)
		{
			ServerPlayerEntity playerIn = (ServerPlayerEntity) event.getPlayer();
			CompoundNBT nbt = playerIn.getPersistentData();
			if(nbt == null)	{nbt = new CompoundNBT();}
			if(!nbt.contains("hasGottenNote")) {nbt.putBoolean("hasGottenNote", false);}
			if(!nbt.getBoolean("hasGottenNote"))
			{
				ItemStack note = new ItemStack(ModItems.vimionic_note, 1);
				playerIn.addItemStackToInventory(note);
				nbt.putBoolean("hasGottenNote", true);
			}
		}
	}
	
	@SubscribeEvent
	public void abominationSpawnEvent(LivingDeathEvent event)
	{
		World worldIn = event.getEntity().world;
		if(!worldIn.isRemote && event.getEntityLiving() instanceof ISpectralEntity)
		{
			Entity entity = event.getSource().getTrueSource();
			if(entity != null)
			{
				if(entity instanceof MobEntity)
				{
					MobEntity mobIn = (MobEntity) entity;
					if(mobIn.isEntityUndead())
					{
						CompoundNBT nbt = entity.getPersistentData();
						if(nbt.contains("VimionAscension"))
						{
							nbt.putInt("VimionAscension", nbt.getInt("VimionAscension") + 1);
							if(nbt.getInt("VimionAscension") >= 5)
							{
								if(mobIn instanceof PhantomEntity)
								{
									ExpionicAbominationEntity vim = new ExpionicAbominationEntity((EntityType<? extends CreatureEntity>) ModEntities.EXPIONIC_ABOMINATION, worldIn);
									vim.setPosition(VTranslate.getEntityX(entity), VTranslate.getEntityY(entity), VTranslate.getEntityZ(entity));
									worldIn.addEntity(vim);
									entity.remove();
								}
								else
								{
									VimionicAbominationEntity vim = new VimionicAbominationEntity((EntityType<? extends CreatureEntity>) ModEntities.VIMIONIC_ABOMINATION, worldIn);
									vim.setPosition(VTranslate.getEntityX(entity), VTranslate.getEntityY(entity), VTranslate.getEntityZ(entity));
									worldIn.addEntity(vim);
									entity.remove();
								}
							}
						}
						else
						{
							nbt.putInt("VimionAscension", 1);
						}
					}
				}
			}
		}
	}
}
