package mushroommantoad.mmpmod.items;

import java.util.List;

import javax.annotation.Nullable;

import mushroommantoad.mmpmod.init.ModTomeQuests;
import mushroommantoad.mmpmod.tomequests.TomeQuest;
import mushroommantoad.mmpmod.tomequests.clearcons.EmptyClearCondition;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
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
				CompoundNBT nbt = playerIn.getPersistentData();
				if(nbt.contains("VimionTomeQuests"))
				{
					CompoundNBT nNBT = new CompoundNBT();
					nbt.put("VimionTomeQuests", nNBT);
					for(TomeQuest q : ModTomeQuests.TOMEQUESTS)
					{
						if(q.getClearCondition() instanceof EmptyClearCondition)
						{
							q.clearClearCon(playerIn);
						}
					}					
					playerIn.getHeldItem(handIn).shrink(1);
					return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
				}
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
