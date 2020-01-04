package mushroommantoad.mmpmod.gui.client.tome;

import com.mojang.blaze3d.systems.RenderSystem;

import mushroommantoad.mmpmod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiTomeTab 
{
	private GuiTome tome;
	private int id;
	private ResourceLocation sprite;
	
	private GuiTomeTabManager tabManager;
	
	public static final ResourceLocation VIMION_TOME = new ResourceLocation(Main.modid + ":textures/gui/vimionite_tome.png");
	
	public GuiTomeTab(GuiTome tome, int id, ResourceLocation sprite)
	{
		this.tome = tome;
		this.id = id;
		this.sprite = sprite;
		this.tabManager = tome.tabManager;
	}
	
	public void drawTab()
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		tome.getMinecraft().getTextureManager().bindTexture(VIMION_TOME);
		
	    int i = tome.guiLeft;
	    int j = (tome.height - tome.ySize) / 2;
	    
	    if(tabManager.activeTab == id) tome.blit(i - 32, j + 4 + (32 * id), 72, 226, 33, 30);
	    else tome.blit(i - 32, j + 4 + (32 * id), 40, 226, 32, 30);
		
		tome.getMinecraft().getTextureManager().bindTexture(sprite);
		RenderSystem.scaled(0.0625, 0.0625, 0);
	    tome.blit(16 * (i - 32) + 144, 16 * (j + 4 + (32 * id)) + 112, 0, 0, 256, 256);
	    RenderSystem.scaled(16, 16, 0);
	}
	
	public boolean checkForHover(int mouseX, int mouseY)
	{
		boolean ret = false;
		if(this.id != tabManager.activeTab)
		{
			int mX = mouseX - tome.guiLeft;
			int mY = mouseY - tome.guiTop;
			
			int topLeft = tome.ySize / 2 - 83 + (32 * id) + 4;
			
			if((mX < 0 && mX >= -25) && (mY >= topLeft + 1 && mY <= topLeft + 29)) ret = true;
			if((mX == -26) && (mY >= topLeft + 2 && mY <= topLeft + 28)) ret = true;
			if((mX == -27) && (mY >= topLeft + 3 && mY <= topLeft + 27)) ret = true;
			if((mX == -28) && (mY >= topLeft + 4 && mY <= topLeft + 26)) ret = true;
			if((mX == -29) && (mY >= topLeft + 5 && mY <= topLeft + 25)) ret = true;
			if((mX == -30) && (mY >= topLeft + 6 && mY <= topLeft + 24)) ret = true;
			if((mX == -31) && (mY >= topLeft + 8 && mY <= topLeft + 22)) ret = true;
		}
		return ret;
	}
	
	public void doHoverEvent(int mouseX, int mouseY)
	{
		if(checkForHover(mouseX, mouseY) && tabManager.activeTab != id)
		{
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			tome.getMinecraft().getTextureManager().bindTexture(VIMION_TOME);
			
		    int i = tome.guiLeft;
		    int j = (tome.height - tome.ySize) / 2;
		    
		    tome.blit(i - 32, j + 4 + (32 * id), 40, 196, 32, 30);
		    
			tome.getMinecraft().getTextureManager().bindTexture(sprite);
			RenderSystem.scaled(0.0625, 0.0625, 0);
		    tome.blit(16 * (i - 32) + 144, 16 * (j + 4 + (32 * id)) + 112, 0, 0, 256, 256);
		    RenderSystem.scaled(16, 16, 0);
		}
	}
	
	public void doClickEvent()
	{
		tome.tabHandler.playDownSound(Minecraft.getInstance().getSoundHandler());
		tabManager.activeTab = id;
		tome.chapter = new GuiTomeChapter(tome, getNameByID());
		tome.chapter.setPage();
	}
	
	private String getNameByID()
	{
		if(id == 0) return "vimion";
		if(id == 1) return "necrion";
		if(id == 2) return "solarion";
		if(id == 3) return "nihilion";
		if(id == 4) return "expion";
		else return "vimion";
	}
	
	public void setActive()
	{
		tabManager.activeTab = id;
	}
}
