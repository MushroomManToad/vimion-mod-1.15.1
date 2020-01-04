package mushroommantoad.mmpmod.network;

import java.util.function.Supplier;

import mushroommantoad.mmpmod.Main;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class SToCAbsorptionSpireParticlePacket 
{
	public double sX;
	public double sY;
	public double sZ;

	public SToCAbsorptionSpireParticlePacket(double sX, double sY, double sZ) 
	{
		this.sX = sX;
		this.sY = sY;
		this.sZ = sZ;
	}
	
	public void serialize(PacketBuffer buf) 
	{
		buf.writeDouble(sX);
		buf.writeDouble(sY);
		buf.writeDouble(sZ);
	}
	
	public static SToCAbsorptionSpireParticlePacket deserialize(PacketBuffer buf) 
	{
		double sX = buf.readDouble();
		double sY = buf.readDouble();
		double sZ = buf.readDouble();
		
		return new SToCAbsorptionSpireParticlePacket(sX, sY, sZ);
	}
	
	public static void handle(SToCAbsorptionSpireParticlePacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		ServerPlayerEntity sender = contextSupplier.get().getSender();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Main.proxy.displaySpireParticles(sender, message);
			});
			context.setPacketHandled(true);
		}
	}
}
