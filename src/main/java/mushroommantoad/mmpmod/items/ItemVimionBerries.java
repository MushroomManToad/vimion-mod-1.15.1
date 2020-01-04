package mushroommantoad.mmpmod.items;

import java.util.List;

import javax.annotation.Nullable;

import mushroommantoad.mmpmod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemVimionBerries extends Item
{
	public ItemVimionBerries(Properties properties) 
	{
		super(properties);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onItemUse(ItemUseContext context)
	{
		World w = context.getWorld();
		BlockPos p = context.getPos();
		Block b = w.getBlockState(p).getBlock();
		Direction dir = context.getFace();
		ItemStack stack = context.getItem();
		if(!w.isRemote)
		{
			if(b == Blocks.GRASS_BLOCK && dir == Direction.UP && w.getBlockState(p.add(0, 1, 0)).isAir())
			{
				stack.shrink(1);
				w.setBlockState(p.add(0, 1, 0), Blocks.SWEET_BERRY_BUSH.getDefaultState());
				return ActionResultType.SUCCESS;
			}
			else return ActionResultType.PASS;
		}
		else return super.onItemUse(context);
	}
	

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		if(stack.getItem() == ModItems.berries_of_annihilation)
		{
			tooltip.add(new TranslationTextComponent("berries.vimion.annihilation.lore").applyTextStyle(TextFormatting.RED));
		}
	}
}
