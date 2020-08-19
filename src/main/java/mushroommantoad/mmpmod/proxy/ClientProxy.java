package mushroommantoad.mmpmod.proxy;

import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.VimionicAbominationEntity;
import mushroommantoad.mmpmod.gui.client.note.GuiNote;
import mushroommantoad.mmpmod.gui.client.tome.GuiTome;
import mushroommantoad.mmpmod.network.SToCAbsorptionSpireParticlePacket;
import mushroommantoad.mmpmod.network.SToCParticleAtPosPacket;
import mushroommantoad.mmpmod.network.SendBookOpenPacket;
import mushroommantoad.mmpmod.network.SendNoteOpenPacket;
import mushroommantoad.mmpmod.proxy.client.HandleDisplayParticleAt;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("resource")
public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit() 
	{
		
	}
	
	@Override
	public void openVimionTomeGUI(ServerPlayerEntity sender, SendBookOpenPacket message) 
	{
		Minecraft.getInstance().displayGuiScreen(new GuiTome(sender, message.getIDData(), message.getBoolData()));
	}
	
	@Override
	public void openVimionNoteGUI(ServerPlayerEntity sender, SendNoteOpenPacket message) 
	{
		Minecraft.getInstance().displayGuiScreen(new GuiNote(sender));
	}
	
	@Override
	public void displaySpireParticles(ServerPlayerEntity sender, SToCAbsorptionSpireParticlePacket message) 
	{
		World worldIn = Minecraft.getInstance().world;
		AxisAlignedBB aabb = new AxisAlignedBB(message.sX + 5, message.sY + 5, message.sZ + 5, message.sX - 5, message.sY - 5, message.sZ - 5);
		for(LivingEntity livingentity : worldIn.getEntitiesWithinAABB(LivingEntity.class, aabb)) 
		{
			if(!(livingentity instanceof VimionicAbominationEntity) && livingentity.isAlive() && !livingentity.isInvulnerable())
			{
				BlockPos entityPos = livingentity.getPosition();
				BlockPos spirePos = new BlockPos(message.sX, message.sY, message.sZ);
				
				double xDif = entityPos.getX() - spirePos.getX();
				double yDif = entityPos.getY() + (livingentity.getHeight() / 2) - spirePos.getY();
				double zDif = entityPos.getZ() - spirePos.getZ();
				
				for(double i = 0; i < 16; i++)
				{
					worldIn.addParticle(ParticleTypes.ENCHANTED_HIT, spirePos.getX() + ((xDif / 16) * i) + 0.5, spirePos.getY() + ((yDif / 16) * i) + 1.0 - (i * 0.05), spirePos.getZ() + ((zDif / 16) * i) + 0.5, 0, 0, 0);
				}
			}
		}
	}
	
	@Override
	public void displayAtPosParticles(ServerPlayerEntity sender, SToCParticleAtPosPacket message) 
	{
		int extra = message.extraData;
		HandleDisplayParticleAt.display(message.type, message.sX, message.sY, message.sZ, extra);
	}
}
