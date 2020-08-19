package mushroommantoad.mmpmod.gui.client.tome;

import java.util.ArrayList;

import com.mojang.blaze3d.systems.RenderSystem;

import mushroommantoad.mmpmod.Main;
import mushroommantoad.mmpmod.init.ModTomeQuests;
import mushroommantoad.mmpmod.tomequests.TomeQuest;
import mushroommantoad.mmpmod.tomequests.VTIDs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiTomeChapter 
{
	public static final ResourceLocation VIMION_TOME = new ResourceLocation(Main.modid + ":textures/gui/vimionite_tome.png");
	public static final ResourceLocation NECRION_TOME = new ResourceLocation(Main.modid + ":textures/gui/necrionite_tome.png");
	public static final ResourceLocation SOLARION_TOME = new ResourceLocation(Main.modid + ":textures/gui/solarionite_tome.png");
	public static final ResourceLocation NIHILION_TOME = new ResourceLocation(Main.modid + ":textures/gui/nihilionite_tome.png");
	public static final ResourceLocation EXPION_TOME = new ResourceLocation(Main.modid + ":textures/gui/expionite_tome.png");
	
	public String name;
	private GuiTome tome;
	public GuiTomePage page;
	public int page_number = 0;
	
	public GuiTomeChapter(GuiTome tome, String name)
	{
		this.name = name;
		this.tome = tome;
	}
	
	public GuiTomeChapter(GuiTome tome, String name, int page_number)
	{
		this.name = name;
		this.tome = tome;
		this.page_number = page_number;
	}
	
	public void setPage()
	{
		tome.clearHoverButtons();
		ArrayList<GuiTomeHoverObject> hoverobjects = new ArrayList<>();
		for(TomeQuest q : ModTomeQuests.TOMEQUESTS)
		{
			if(q.getChapter().equals(name)) 
			{
				int id = -1;
				int pcount = 0;
				boolean parentsMet = false;
				for(int i = 0; i < tome.getProgressIDs().length; i++)
				{
					if(q.getName().getKey().equals(tome.getProgressIDs()[i]))
					{
						id = i;
					}
					if(!parentsMet)
					{
						PARENTCHECK: for(TranslationTextComponent t : q.getParents())
						{
							if(t.getKey().equals(VTIDs.NONE)) { parentsMet = true; break PARENTCHECK;}
							if(t.getKey().equals(tome.getProgressIDs()[i]) && tome.getProgressBoolData()[i])
							{
								pcount++;
							}
						}
						if(pcount == q.getParents().length) parentsMet = true;
					}
				}
				if(parentsMet) hoverobjects.add(new GuiTomeHoverObject(tome, q.getX(), q.getY(), q.getSprite(), q.getName().getUnformattedComponentText(), q.getHoverText().getUnformattedComponentText(), id == -1 ? false : tome.getProgressBoolData()[id], q.getGreyText().getUnformattedComponentText(), q.getGoldText().getUnformattedComponentText()));
			}
		}
		page = new GuiTomePage(tome, hoverobjects);
		for(GuiTomeHoverObject ho : page.ho)
		{
			GuiTome.HoverObjectButton hoverButton = new GuiTome.HoverObjectButton(tome.guiLeft + ho.x, tome.guiTop + ho.y, 28, 28, " ", (p_213029_1_) -> { page.handleHoverObjectClickEvent(tome.mousePosX, tome.mousePosY );}); 
			tome.addCustomButton(hoverButton);
		}
	}
	
	public void drawBackground()
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	    tome.getMinecraft().getTextureManager().bindTexture(VIMION_TOME);
	    if(name == "vimion") tome.getMinecraft().getTextureManager().bindTexture(VIMION_TOME);
	    if(name == "necrion") tome.getMinecraft().getTextureManager().bindTexture(NECRION_TOME);
	    if(name == "solarion") tome.getMinecraft().getTextureManager().bindTexture(SOLARION_TOME);
	    if(name == "nihilion") tome.getMinecraft().getTextureManager().bindTexture(NIHILION_TOME);
	    if(name == "expion") tome.getMinecraft().getTextureManager().bindTexture(EXPION_TOME);
	    int i = tome.guiLeft;
	    int j = (tome.height - tome.ySize) / 2;
	    tome.blit(i, j, 0, 0, tome.xSize, tome.ySize);
	}
	
	public void drawHoverObjects(int mouseX, int mouseY)
	{
		page.drawAllHoverObjects(mouseX, mouseY);
	}
	
	public String getName()
	{
		return name;
	}
}
