package mushroommantoad.mmpmod.items;

import java.util.List;

import javax.annotation.Nullable;

import mushroommantoad.mmpmod.network.SendNoteOpenPacket;
import mushroommantoad.mmpmod.network.VimionPacketHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;

public class ItemVimionicNote extends Item
{	
	public ItemVimionicNote(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		if(!worldIn.isRemote)
		{			
			ServerPlayerEntity playerMP = (ServerPlayerEntity) playerIn;
			
			VimionPacketHandler.CHANNEL.sendTo(new SendNoteOpenPacket(), playerMP.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
		
		worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1, 1);
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{	
		tooltip.add(new TranslationTextComponent("vimion.vimionic.note.lore").applyTextStyle(TextFormatting.LIGHT_PURPLE));
	}
}
