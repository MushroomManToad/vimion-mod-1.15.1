package mushroommantoad.mmpmod.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemMemoryCrystal extends Item
{
	String[] nbtIDs = {"VimionAdvancements", "NecrionAdvancements", "SolarionAdvancements","NihilionAdvancements","ExpionAdvancements"};
	
	public ItemMemoryCrystal(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		if(!worldIn.isRemote)
		{			
			if(playerIn.isCrouching())
			{
				for(int i = 0; i < 5; i++)
				{
					int[] emptiness = new int[100];
					playerIn.getPersistentData().putIntArray(nbtIDs[i], emptiness);
				}
				playerIn.getHeldItem(handIn).shrink(1);
				return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{	
		tooltip.add(new TranslationTextComponent("vimion.memory.crystal.lore").applyTextStyle(TextFormatting.WHITE));
	}
}
