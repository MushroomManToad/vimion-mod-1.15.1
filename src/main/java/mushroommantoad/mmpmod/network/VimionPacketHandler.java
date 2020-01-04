package mushroommantoad.mmpmod.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class VimionPacketHandler 
{
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
	    new ResourceLocation("vimion", "main"),
	    () -> PROTOCOL_VERSION,
	    PROTOCOL_VERSION::equals,
	    PROTOCOL_VERSION::equals
	);
	
	public void registerPackets()
	{
		int id = -1;
		CHANNEL.messageBuilder(SendBookOpenPacket.class, id++)
		.encoder(SendBookOpenPacket::serialize).decoder(SendBookOpenPacket::deserialize)
		.consumer(SendBookOpenPacket::handle)
		.add();
		
		CHANNEL.messageBuilder(SToCAbsorptionSpireParticlePacket.class, id++)
		.encoder(SToCAbsorptionSpireParticlePacket::serialize).decoder(SToCAbsorptionSpireParticlePacket::deserialize)
		.consumer(SToCAbsorptionSpireParticlePacket::handle)
		.add();
		
		CHANNEL.messageBuilder(SToCExpionicTeleportParticlePacket.class, id++)
		.encoder(SToCExpionicTeleportParticlePacket::serialize).decoder(SToCExpionicTeleportParticlePacket::deserialize)
		.consumer(SToCExpionicTeleportParticlePacket::handle)
		.add();
	}
}
