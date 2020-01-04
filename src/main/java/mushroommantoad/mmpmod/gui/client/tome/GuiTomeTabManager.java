package mushroommantoad.mmpmod.gui.client.tome;

import java.util.ArrayList;

import mushroommantoad.mmpmod.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiTomeTabManager
{
	private GuiTome tome;
	private ArrayList<GuiTomeTab> tabs = new ArrayList<>(); 
	private static ResourceLocation VIMION_GEMSTONE = new ResourceLocation(Main.modid + ":textures/gui/icon/vimion_gemstone.png");
	private static ResourceLocation NECRION_GEMSTONE = new ResourceLocation(Main.modid + ":textures/gui/icon/necrion_gemstone.png");
	private static ResourceLocation SOLARION_GEMSTONE = new ResourceLocation(Main.modid + ":textures/gui/icon/solarion_gemstone.png");
	private static ResourceLocation NIHILION_GEMSTONE = new ResourceLocation(Main.modid + ":textures/gui/icon/nihilion_gemstone.png");
	private static ResourceLocation EXPION_GEMSTONE = new ResourceLocation(Main.modid + ":textures/gui/icon/expion_gemstone.png");
	
	public int activeTab = 0;
	
	public GuiTomeTabManager(GuiTome tome, String activeTabID)
	{
		this.tome = tome;
		if(activeTabID == "necrion") activeTab = 1;
		if(activeTabID == "solarion") activeTab = 2;
		if(activeTabID == "nihilion") activeTab = 3;
		if(activeTabID == "expion") activeTab = 4;
	}
	
	public void getTabs()
	{
		tabs.clear();
		tabs.add(new GuiTomeTab(tome, 0, VIMION_GEMSTONE));
		tabs.add(new GuiTomeTab(tome, 1, NECRION_GEMSTONE));
		tabs.add(new GuiTomeTab(tome, 2, SOLARION_GEMSTONE));
		tabs.add(new GuiTomeTab(tome, 3, NIHILION_GEMSTONE));
		tabs.add(new GuiTomeTab(tome, 4, EXPION_GEMSTONE));
	}
	
	public void tabTick(int mouseX, int mouseY)
	{
		getTabs();
		drawTabs();
		runHoverEvents(mouseX, mouseY);
	}
	
	public void drawTabs()
	{
		for(GuiTomeTab t : tabs)
		{
			t.drawTab();
		}
	}
	
	public void runHoverEvents(int mouseX, int mouseY)
	{
		for(GuiTomeTab t : tabs)
		{
			t.doHoverEvent(mouseX, mouseY);
		}
	}
	
	public void handleClickEvent(int mouseX, int mouseY)
	{
		for(GuiTomeTab t : tabs)
		{
			if(t.checkForHover(mouseX, mouseY))
			{
				t.doClickEvent();
			}
		}
	}
}
