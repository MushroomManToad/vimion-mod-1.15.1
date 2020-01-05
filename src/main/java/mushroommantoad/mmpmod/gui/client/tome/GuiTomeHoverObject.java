package mushroommantoad.mmpmod.gui.client.tome;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mojang.blaze3d.systems.RenderSystem;

import mushroommantoad.mmpmod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiTomeHoverObject 
{
	public static final ResourceLocation VIMION_TOME = new ResourceLocation(Main.modid + ":textures/gui/vimionite_tome.png");
	
	private static final Pattern PATTERN = Pattern.compile("(.+) \\S+");
	
	public GuiTome tome;
	
	public int x;
	public int y;
	
	public int wrapWidth;
	public int reverseWrapWidth;
	
	public ResourceLocation icon;
	public String name;
	public String hover;
	public boolean isGold;
	
	public String greyText;
	public String goldText;
	
	public GuiTomeHoverObject(GuiTome tome, int x, int y, ResourceLocation icon, String name, String hover, boolean isGold, String greyText, String goldText)
	{
		this.tome = tome;
		this.x = x;
		this.y = y;
		this.icon = icon;
		this.name = name;
		this.hover = hover;
		this.isGold = isGold;
		this.greyText = greyText;
		this.goldText = goldText;
		this.wrapWidth = this.tome.guiLeft + this.tome.xSize - this.tome.guiLeft - this.x - 50;
		this.reverseWrapWidth = this.x - 4;
	}
	
	// Draws the Icon
	public void drawIcon()
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		tome.getMinecraft().getTextureManager().bindTexture(VIMION_TOME);
		
	    int i = tome.guiLeft;
	    int j = (tome.height - tome.ySize) / 2;
	    if(this.isGold) tome.blit(i + x, j + y, 130, 228, 28, 28);
	    else tome.blit(i + x, j + y, 0, 228, 28, 28);
	    
	    tome.getMinecraft().getTextureManager().bindTexture(icon);
	    RenderSystem.scaled(0.0625, 0.0625, 0);
	    tome.blit(16 * (i + x) + 96, 16 * (j + y) + 96, 0, 0, 256, 256);
	    RenderSystem.scaled(16, 16, 0);
	}
	
	// Draws the string if it's being hovered over
	public boolean checkForHover(int mouseX, int mouseY)
	{
		boolean ret = false;
		int mX = mouseX - tome.guiLeft;
		int mY = mouseY - tome.guiTop;
		if((mX >= x + 7 && mX <= x + 20) && (mY == y || mY == y + 27)) ret = true;
		if((mX >= x + 6 && mX <= x + 21) && (mY == y + 1 || mY == y + 2 || mY == y + 25 || mY == y + 26)) ret = true;
		if((mX >= x + 5 && mX <= x + 22) && (mY == y + 3 || mY == y + 4 || mY == y + 23 || mY == y + 24)) ret = true;
		if((mX >= x + 4 && mX <= x + 23) && (mY == y + 5 || mY == y + 6 || mY == y + 21 || mY == y + 22)) ret = true;
		if((mX >= x + 3 && mX <= x + 24) && (mY == y + 7 || mY == y + 8 || mY == y + 19 || mY == y + 20)) ret = true;
		if((mX >= x + 2 && mX <= x + 25) && (mY == y + 9 || mY == y + 10 || mY == y + 17 || mY == y + 18)) ret = true;
		if((mX >= x + 1 && mX <= x + 26) && (mY == y + 11 || mY == y + 12 || mY == y + 15 || mY == y + 16)) ret = true;
		if((mX >= x && mX <= x + 27) && (mY == y + 13 || mY == y + 14)) ret = true;
		
		if(ret) drawHoverBox();
		
		return ret;
	}
	
	public void drawHoverBox()
	{
		if(tome.guiLeft + x + 28 < tome.width / 2)
		{
			drawHoverBackground();
			drawHoverText();
		}
		else
		{
			drawReverseHoverBackground();
			drawReverseHoverText();
		}
		drawHoverIcon();
	}
	
	public void drawHoverIcon()
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		tome.getMinecraft().getTextureManager().bindTexture(VIMION_TOME);
		
	    int i = tome.guiLeft;
	    int j = (tome.height - tome.ySize) / 2;
	    if(this.isGold) tome.blit(i + x, j + y, 130, 200, 28, 28);
	    else tome.blit(i + x, j + y, 0, 200, 28, 28);
	    
	    tome.getMinecraft().getTextureManager().bindTexture(icon);
	    RenderSystem.scaled(0.0625, 0.0625, 1);
	    tome.blit(16 * (i + x) + 96, 16 * (j + y) + 96, 0, 0, 256, 256);
	    RenderSystem.scaled(16, 16, 1);
	}
	
	public void drawHoverBackground()
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		tome.getMinecraft().getTextureManager().bindTexture(VIMION_TOME);
		
	    int i = tome.guiLeft + x + 19;
	    int j = y + ((tome.height - tome.ySize) / 2);
	    
	    
	    tome.blit(i, j, 28, 244, 4, 4);
	    int lS = findLongestString(findOptimalLines(hover, wrapWidth)) / 4;
	    for(int k = 0; k <= lS + 2; k++)
	    {
	    	tome.blit(i + (k * 4) + 4, j, 32, 244, 4, 4);
	    }
	    tome.blit(i + (lS * 4) + 16, j, 36, 244, 4, 4);
	    
	    for(int k = 1; k <= ((findOptimalLines(hover, wrapWidth).size() * 10) / 4) + 3; k++)
	    {
		    tome.blit(i, j + (k * 4), 28, 248, 4, 4);
		    for(int l = 0; l <= lS + 2; l++)
		    {
		    	tome.blit(i + ((l + 1) * 4), j + (k * 4), 32, 248, 4, 4);
		    }
		    tome.blit(i + (lS * 4) + 16, j + (k * 4), 36, 248, 4, 4);
	    }
	    
	    int yValEnd = 4 + j + (((findOptimalLines(hover, wrapWidth).size() * 10) / 4) + 3) * 4;
	    
	    tome.blit(i, yValEnd, 28, 252, 4, 4);
	    for(int k = 0; k <= lS + 2; k++)
	    {
	    	tome.blit(i + (k * 4) + 4, yValEnd, 32, 252, 4, 4);
	    }
	    tome.blit(i + (lS * 4) + 16, yValEnd, 36, 252, 4, 4);
	}
	
	public void drawHoverText()
	{
		int titleColor = 16763136;
		if(tome.chapter.name == "vimion") titleColor = 9626932;
		if(tome.chapter.name == "necrion") titleColor = 6637703;
		if(tome.chapter.name == "solarion") titleColor = 16579401;
		if(tome.chapter.name == "nihilion") titleColor = 16537929;
		if(tome.chapter.name == "expion") titleColor = 4821756;
		tome.drawString(tome.getFontRenderer(), this.name, tome.guiLeft + x + 30, tome.guiTop + y + 4, titleColor);
		
		for(int i = 0; i < findOptimalLines(hover, wrapWidth).size(); i++)
		{
			tome.drawString(tome.getFontRenderer(), findOptimalLines(hover, wrapWidth).get(i), tome.guiLeft + x + 30, tome.guiTop + y + 16 + (i * 10), 14737632);
		}
	}
	
	public void drawReverseHoverBackground()
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		tome.getMinecraft().getTextureManager().bindTexture(VIMION_TOME);
		
	    int i = tome.guiLeft + x + 5;
	    int j = y + ((tome.height - tome.ySize) / 2);
	    
	    
	    tome.blit(i, j, 36, 244, 4, 4);
	    int lS = findLongestString(findOptimalLines(hover, reverseWrapWidth)) / 4;
	    for(int k = 0; k <= lS + 1; k++)
	    {
	    	tome.blit(i - (k * 4) - 4, j, 32, 244, 4, 4);
	    }
	    tome.blit(i - (lS * 4) - 12, j, 28, 244, 4, 4);
	    
	    for(int k = 1; k <= ((findOptimalLines(hover, reverseWrapWidth).size() * 10) / 4) + 3; k++)
	    {
		    tome.blit(i, j + (k * 4), 36, 248, 4, 4);
		    for(int l = 0; l <= lS + 1; l++)
		    {
		    	tome.blit(i - ((l + 1) * 4), j + (k * 4), 32, 248, 4, 4);
		    }
		    tome.blit(i - (lS * 4) - 12, j + (k * 4), 28, 248, 4, 4);
	    }
	    
	    int yValEnd = 4 + j + (((findOptimalLines(hover, reverseWrapWidth).size() * 10) / 4) + 3) * 4;
	    
	    tome.blit(i, yValEnd, 36, 252, 4, 4);
	    for(int k = 0; k <= lS + 1; k++)
	    {
	    	tome.blit(i - (k * 4) - 4, yValEnd, 32, 252, 4, 4);
	    }
	    tome.blit(i - (lS * 4) - 12, yValEnd, 28, 252, 4, 4);
	}
	
	public void drawReverseHoverText()
	{
		int titleColor = 16763136;
		if(tome.chapter.name == "vimion") titleColor = 9626932;
		if(tome.chapter.name == "necrion") titleColor = 6637703;
		if(tome.chapter.name == "solarion") titleColor = 16579401;
		if(tome.chapter.name == "nihilion") titleColor = 16537929;
		if(tome.chapter.name == "expion") titleColor = 4821756;
		
		tome.drawString(tome.getFontRenderer(), this.name, tome.guiLeft + x - findLongestString(findOptimalLines(hover, reverseWrapWidth)), tome.guiTop + y + 4, titleColor);
		
		for(int i = 0; i < findOptimalLines(hover, reverseWrapWidth).size(); i++)
		{
			tome.drawString(tome.getFontRenderer(), findOptimalLines(hover, reverseWrapWidth).get(i), tome.guiLeft + x - findLongestString(findOptimalLines(hover, reverseWrapWidth)), tome.guiTop + y + 16 + (i * 10), 14737632);
		}
	}
	
	public int findLongestString(List<String> text)
	{
		int lS = 0;
		for(int i = 0; i < text.size(); i++)
		{
			if(tome.getMinecraft().fontRenderer.getStringWidth(text.get(i)) > lS)
			{
				lS = tome.getMinecraft().fontRenderer.getStringWidth(text.get(i));
			}
		}
		return lS;
	}
	
   private List<String> findOptimalLines(String hover, int wrapWidth) 
   {
	   if (hover.isEmpty()) 
	   {
		   return Collections.emptyList();
	   } 
	   else 
	   {
		   List<String> list = tome.getMinecraft().fontRenderer.listFormattedStringToWidth(hover, wrapWidth);
		   if (list.size() < 2) 
		   {
			   return list;
		   } 
		   else 
		   {
			   String s = list.get(0);
			   String s1 = list.get(1);
			   int i = tome.getMinecraft().fontRenderer.getStringWidth(s + ' ' + s1.split(" ")[0]);
			   if (i - wrapWidth <= 10)
			   {
				   return tome.getMinecraft().fontRenderer.listFormattedStringToWidth(hover, i);
			   } 
			   else 
			   {
				   Matcher matcher = PATTERN.matcher(s);
				   if (matcher.matches()) 
				   {
					   int j = tome.getMinecraft().fontRenderer.getStringWidth(matcher.group(1));
					   if (wrapWidth - j <= 10) 
					   {
						   return tome.getMinecraft().fontRenderer.listFormattedStringToWidth(hover, j);
					   }
				   }
				   return list;
			   }
		   }
	   }
   }
   
   public void openInfoGui()
   {
	   String chapter = tome.chapter.getName();
	   int page_number = tome.chapter.page_number;
	   tome.getMinecraft().displayGuiScreen((Screen)null);
	   Minecraft.getInstance().displayGuiScreen(new GuiTomeInfoPage(tome.getPlayer(), tome.getProgress(), chapter, page_number, this.isGold ? this.goldText : this.greyText));
   }
}
