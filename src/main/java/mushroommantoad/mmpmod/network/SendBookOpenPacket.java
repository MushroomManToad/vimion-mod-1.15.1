package mushroommantoad.mmpmod.network;

import java.util.function.Supplier;

import mushroommantoad.mmpmod.Main;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class SendBookOpenPacket 
{
	private int arrayLength;
	private boolean[] boolData;
	private String[] idData;

	public SendBookOpenPacket(boolean[] booleanData, String[] idData) 
	{
		this.setArrayLength(booleanData.length);
		this.setBoolData(booleanData);
		this.setIDData(idData);
	}
	
	public void serialize(PacketBuffer buf) 
	{
		buf.writeInt(this.getArrayLength());
		for(int i = 0; i < this.getArrayLength(); i++) 
		{
			buf.writeBoolean(this.getBoolData()[i]);
			buf.writeString(this.getIDData()[i]);
		}
	}
	
	public static SendBookOpenPacket deserialize(PacketBuffer buf) {
		int arrayL = buf.readInt();
		boolean[] bools = new boolean[arrayL]; 
		String[] ids = new String[arrayL]; 
		for(int i = 0; i < arrayL; i++) 
		{
			bools[i] = buf.readBoolean();
			ids[i] = buf.readString();
		}
		return new SendBookOpenPacket(bools, ids);
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
	
	public String[] getIDData() {
		return idData;
	}

	public void setIDData(String[] idData) {
		this.idData = idData;
	}

	public boolean[] getBoolData() {
		return boolData;
	}

	public void setBoolData(boolean[] boolData) {
		this.boolData = boolData;
	}

	public int getArrayLength() {
		return arrayLength;
	}

	public void setArrayLength(int arrayLength) {
		this.arrayLength = arrayLength;
	}
}
