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
public class PagesSolarion 
{
	static int yOffset = -2;
	
	private static ResourceLocation SOLARION_GEMSTONE = new ResourceLocation(Main.modid + ":textures/gui/icon/solarion_gemstone.png");
	private static ResourceLocation SOLARIONITE_PICKAXE = new ResourceLocation(Main.modid + ":textures/gui/icon/solarionite_pickaxe.png");
	
	// TODO
	public static GuiTomePage getPage(GuiTome tome, int id)
	{
		return get0(tome);
	}
	
	public static GuiTomePage get0(GuiTome tome)
	{
		PlayerEntity player = tome.getPlayer();
		ArrayList<GuiTomeHoverObject> ho = new ArrayList<>();
		
		if(tome.getProgress()[VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER] == 1) ho.add(new GuiTomeHoverObject(tome, 10, tome.ySize / 2 - 14 + yOffset, SOLARION_GEMSTONE, "Solarion!", PagesDisplayText.HOVER_SOLARION_GEMSTONE, tome.getProgress()[VTIDHandler.OBJECTIVE_SOLARION_GEMSTONE] == 1, PagesDisplayText.GREY_SOLARION_GEMSTONE, PagesDisplayText.GOLD_SOLARION_GEMSTONE));
		if(tome.getProgress()[VTIDHandler.OBJECTIVE_SOLARION_GEMSTONE] == 1) ho.add(new GuiTomeHoverObject(tome, 40, tome.ySize / 2 - 42 + yOffset, SOLARIONITE_PICKAXE, "Solar Ore Pinging", PagesDisplayText.HOVER_SOLARIONITE_PICKAXE, tome.getProgress()[VTIDHandler.OBJECTIVE_CRAFT_SOLARIONITE_PICKAXE] == 1, PagesDisplayText.GREY_SOLARIONITE_PICKAXE, PagesDisplayText.GOLD_SOLARIONITE_PICKAXE));
		
		return new GuiTomePage(tome, ho);
	}
}
