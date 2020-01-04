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
public class PagesNecrion 
{
	static int yOffset = -1;
	
	private static ResourceLocation NECRION_GEMSTONE = new ResourceLocation(Main.modid + ":textures/gui/icon/necrion_gemstone.png");
	private static ResourceLocation NECRIONITE_SUMMONER = new ResourceLocation(Main.modid + ":textures/gui/icon/necrionite_summoner.png");
	
	// TODO
	public static GuiTomePage getPage(GuiTome tome, int id)
	{
		return get0(tome);
	}
	
	public static GuiTomePage get0(GuiTome tome)
	{
		PlayerEntity player = tome.getPlayer();
		ArrayList<GuiTomeHoverObject> ho = new ArrayList<>();
		
		if(tome.getProgress()[VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER] == 1) ho.add(new GuiTomeHoverObject(tome, 10, tome.ySize / 2 - 14  + yOffset, NECRION_GEMSTONE, "Necrion!", PagesDisplayText.HOVER_NECRION_GEMSTONE, tome.getProgress()[VTIDHandler.OBJECTIVE_NECRION_GEMSTONE] == 1, PagesDisplayText.GREY_NECRION_GEMSTONE, PagesDisplayText.GOLD_NECRION_GEMSTONE));
		if(tome.getProgress()[VTIDHandler.OBJECTIVE_OBTAIN_MOB_SPIRIT] == 1 && tome.getProgress()[VTIDHandler.OBJECTIVE_NECRION_GEMSTONE] == 1) ho.add(new GuiTomeHoverObject(tome, 40, tome.ySize / 2 + 14  + yOffset, NECRIONITE_SUMMONER, "The Bridge...", PagesDisplayText.HOVER_NECRION_SUMMONER, tome.getProgress()[VTIDHandler.OBJECTIVE_CRAFT_NECRIONITE_SUMMONER] == 1, PagesDisplayText.GREY_NECRION_SUMMONER, PagesDisplayText.GOLD_NECRION_SUMMONER));
		
		return new GuiTomePage(tome, ho);
	}
}
