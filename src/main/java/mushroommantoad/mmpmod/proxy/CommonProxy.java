package mushroommantoad.mmpmod.proxy;

import mushroommantoad.mmpmod.network.SToCAbsorptionSpireParticlePacket;
import mushroommantoad.mmpmod.network.SToCExpionicTeleportParticlePacket;
import mushroommantoad.mmpmod.network.SendBookOpenPacket;
import net.minecraft.entity.player.ServerPlayerEntity;

public class CommonProxy 
{	
	public void preInit() {}
		
	public void openVimionTomeGUI(ServerPlayerEntity sender, SendBookOpenPacket message) {}
	public void displaySpireParticles(ServerPlayerEntity sender, SToCAbsorptionSpireParticlePacket message) {}
	public void displayExpionicAbominationParticles(ServerPlayerEntity sender, SToCExpionicTeleportParticlePacket message) {}
}
