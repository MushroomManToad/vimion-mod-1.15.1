package mushroommantoad.mmpmod.network;

import java.util.function.Supplier;

import mushroommantoad.mmpmod.Main;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class SendNoteOpenPacket 
{
	public SendNoteOpenPacket() {}
	
	public void serialize(PacketBuffer buf)	{}
	
	public static SendNoteOpenPacket deserialize(PacketBuffer buf) {return new SendNoteOpenPacket();}
	
	public static void handle(SendNoteOpenPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		ServerPlayerEntity sender = contextSupplier.get().getSender();
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Main.proxy.openVimionNoteGUI(sender, message);
			});
			context.setPacketHandled(true);
		}
	}
}
