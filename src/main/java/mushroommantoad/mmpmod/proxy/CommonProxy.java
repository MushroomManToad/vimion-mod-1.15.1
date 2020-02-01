package mushroommantoad.mmpmod.proxy;

import mushroommantoad.mmpmod.network.SToCAbsorptionSpireParticlePacket;
import mushroommantoad.mmpmod.network.SToCParticleAtPosPacket;
import mushroommantoad.mmpmod.network.SendBookOpenPacket;
import mushroommantoad.mmpmod.network.SendNoteOpenPacket;
import net.minecraft.entity.player.ServerPlayerEntity;

public class CommonProxy 
{	
	public void preInit() {}
		
	public void openVimionTomeGUI(ServerPlayerEntity sender, SendBookOpenPacket message) {}
	public void openVimionNoteGUI(ServerPlayerEntity sender, SendNoteOpenPacket message) {}
	public void displaySpireParticles(ServerPlayerEntity sender, SToCAbsorptionSpireParticlePacket message) {}
	public void displayAtPosParticles(ServerPlayerEntity sender, SToCParticleAtPosPacket message) {}
}
