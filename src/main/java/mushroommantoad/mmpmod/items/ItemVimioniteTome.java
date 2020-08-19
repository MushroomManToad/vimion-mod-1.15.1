package mushroommantoad.mmpmod.items;

import java.util.List;

import javax.annotation.Nullable;

import mushroommantoad.mmpmod.init.ModTomeQuests;
import mushroommantoad.mmpmod.network.SendBookOpenPacket;
import mushroommantoad.mmpmod.network.VimionPacketHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;

public class ItemVimioniteTome extends Item
{	
	public ItemVimioniteTome(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		if(!worldIn.isRemote)
		{			
			ServerPlayerEntity playerMP = (ServerPlayerEntity) playerIn;
			CompoundNBT nbt = playerMP.getPersistentData();
			if (!nbt.contains("VimionTomeQuests")) {
				nbt.put("VimionTomeQuests", new CompoundNBT());
			}
			
			CompoundNBT tomenbt = nbt.getCompound("VimionTomeQuests");
			
			boolean[] boolData = new boolean[ModTomeQuests.TOMEQUESTS.size()];
			String[] idData = new String[ModTomeQuests.TOMEQUESTS.size()];
			for(int i = 0; i < ModTomeQuests.TOMEQUESTS.size(); i++)
			{
				boolData[i] = tomenbt.contains(ModTomeQuests.TOMEQUESTS.get(i).getName().getKey()) ? tomenbt.getBoolean(ModTomeQuests.TOMEQUESTS.get(i).getName().getKey()) : false;
				idData[i] = ModTomeQuests.TOMEQUESTS.get(i).getName().getKey();
			}
			VimionPacketHandler.CHANNEL.sendTo(new SendBookOpenPacket(boolData, idData), playerMP.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{	
		tooltip.add(new TranslationTextComponent("vimion.vimionic.tome.lore").applyTextStyle(TextFormatting.WHITE));
	}
}
