package mushroommantoad.mmpmod.gui.client.tome.pages;

import java.util.ArrayList;

import mushroommantoad.mmpmod.Main;
import mushroommantoad.mmpmod.gui.client.tome.GuiTome;
import mushroommantoad.mmpmod.gui.client.tome.GuiTomeHoverObject;
import mushroommantoad.mmpmod.gui.client.tome.GuiTomePage;
import mushroommantoad.mmpmod.util.VTIDHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("unused")
@OnlyIn(Dist.CLIENT)
public class PagesExpion 
{
	static int yOffset = -4;
	
	private static ResourceLocation EXPION_GEMSTONE = new ResourceLocation(Main.modid + ":textures/gui/icon/expion_gemstone.png");
	private static ResourceLocation EXPIONITE_SPADE = new ResourceLocation(Main.modid + ":textures/gui/icon/expionite_spade.png");
	
	// TODO
	public static GuiTomePage getPage(GuiTome tome, int id)
	{
		return get0(tome);
	}
	
	public static GuiTomePage get0(GuiTome tome)
	{
		PlayerEntity player = tome.getPlayer();
		ArrayList<GuiTomeHoverObject> ho = new ArrayList<>();
		
		if(tome.getProgress()[VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER] == 1) ho.add(new GuiTomeHoverObject(tome, 10, tome.ySize / 2 - 14 + yOffset, EXPION_GEMSTONE, "Expion!", PagesDisplayText.HOVER_EXPION_GEMSTONE, tome.getProgress()[VTIDHandler.OBJECTIVE_EXPION_GEMSTONE] == 1, PagesDisplayText.GREY_EXPION_GEMSTONE, PagesDisplayText.GOLD_EXPION_GEMSTONE));
		if(tome.getProgress()[VTIDHandler.OBJECTIVE_EXPION_GEMSTONE] == 1) ho.add(new GuiTomeHoverObject(tome, 40, tome.ySize / 2 - 42 + yOffset, EXPIONITE_SPADE, "Dimensional Digging", PagesDisplayText.HOVER_EXPIONITE_SPADE, tome.getProgress()[VTIDHandler.OBJECTIVE_CRAFT_EXPION_SHOVEL] == 1, PagesDisplayText.GREY_EXPIONITE_SPADE, PagesDisplayText.GOLD_EXPIONITE_SPADE));
		
		return new GuiTomePage(tome, ho);
	}
}
