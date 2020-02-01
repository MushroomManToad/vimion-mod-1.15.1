package mushroommantoad.mmpmod.network;

import java.util.function.Supplier;

import mushroommantoad.mmpmod.Main;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class SToCParticleAtPosPacket 
{
	public double sX;
	public double sY;
	public double sZ;
	public int type;

	public SToCParticleAtPosPacket(double sX, double sY, double sZ, int type) 
	{
		this.sX = sX;
		this.sY = sY;
		this.sZ = sZ;
		this.type = type;
	}
	
	public void serialize(PacketBuffer buf) 
	{
		buf.writeDouble(sX);
		buf.writeDouble(sY);
		buf.writeDouble(sZ);
		buf.writeInt(type);
	}
	
	public static SToCParticleAtPosPacket deserialize(PacketBuffer buf) 
	{
		double sX = buf.readDouble();
		double sY = buf.readDouble();
		double sZ = buf.readDouble();
		int color = buf.readInt();
		
		return new SToCParticleAtPosPacket(sX, sY, sZ, color);
	}
	
	public static void handle(SToCParticleAtPosPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		ServerPlayerEntity sender = contextSupplier.get().getSender();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Main.proxy.displayAtPosParticles(sender, message);
			});
			context.setPacketHandled(true);
		}
	}
}
