package mushroommantoad.mmpmod.network;

import java.util.function.Supplier;

import mushroommantoad.mmpmod.Main;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class SendBookOpenPacket 
{
	private int[] bookData;

	public SendBookOpenPacket(int[] data) 
	{
		this.setBookData(data);
	}
	
	public void serialize(PacketBuffer buf) 
	{
		buf.writeVarIntArray(this.getBookData());
	}
	
	public static SendBookOpenPacket deserialize(PacketBuffer buf) {
		int[] data = buf.readVarIntArray();
		return new SendBookOpenPacket(data);
	}
	
	public static void handle(SendBookOpenPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		ServerPlayerEntity sender = contextSupplier.get().getSender();
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Main.proxy.openVimionTomeGUI(sender, message);
			});
			context.setPacketHandled(true);
		}
	}

	public int[] getBookData() {
		return bookData;
	}

	public void setBookData(int[] bookData) {
		this.bookData = bookData;
	}
}
