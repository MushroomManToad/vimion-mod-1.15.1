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
public class PagesNihilion 
{
	static int yOffset = -3;
	
	private static ResourceLocation NIHILION_GEMSTONE = new ResourceLocation(Main.modid + ":textures/gui/icon/nihilion_gemstone.png");
	private static ResourceLocation NIHILIONITE_AXE = new ResourceLocation(Main.modid + ":textures/gui/icon/nihilionite_axe.png");
	
	// TODO
	public static GuiTomePage getPage(GuiTome tome, int id)
	{
		return get0(tome);
	}
	
	public static GuiTomePage get0(GuiTome tome)
	{
		PlayerEntity player = tome.getPlayer();
		ArrayList<GuiTomeHoverObject> ho = new ArrayList<>();
		
		if(tome.getProgress()[VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER] == 1) ho.add(new GuiTomeHoverObject(tome, 10, tome.ySize / 2 - 14 + yOffset, NIHILION_GEMSTONE, "Nihilion!", PagesDisplayText.HOVER_NIHILION_GEMSTONE, tome.getProgress()[VTIDHandler.OBJECTIVE_NIHILION_GEMSTONE] == 1, PagesDisplayText.GREY_NIHILION_GEMSTONE, PagesDisplayText.GOLD_NIHILION_GEMSTONE));
		if(tome.getProgress()[VTIDHandler.OBJECTIVE_NIHILION_GEMSTONE] == 1) ho.add(new GuiTomeHoverObject(tome, 40, tome.ySize / 2 - 42 + yOffset, NIHILIONITE_AXE, "Tree Remover", PagesDisplayText.HOVER_NIHILIONITE_AXE, tome.getProgress()[VTIDHandler.OBJECTIVE_CRAFT_NIHILIONITE_AXE] == 1, PagesDisplayText.GREY_NIHILIONITE_AXE, PagesDisplayText.GOLD_NIHILIONITE_AXE));
		
		return new GuiTomePage(tome, ho);
	}
}
