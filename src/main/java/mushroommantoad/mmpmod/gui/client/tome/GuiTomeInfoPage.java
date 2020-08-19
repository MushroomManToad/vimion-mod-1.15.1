package mushroommantoad.mmpmod.gui.client.tome;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mojang.blaze3d.systems.RenderSystem;

import mushroommantoad.mmpmod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiTomeInfoPage extends Screen
{	
	private static final Pattern PATTERN = Pattern.compile("(.+) \\S+");
	
	int mousePosX = 0;
	int mousePosY = 0;
	
    public int xSize = 256;
    public int ySize = 166;
    public int guiLeft;
    public int guiTop;
    
    public ResourceLocation BACKGROUND;
    
	int wrapWidth = 100;
	
    PlayerEntity playerIn;
    String[] ids;
    boolean[] boolData;
	String oldChapter;
	int oldPage;
	String displayText;
	int page = 0;
	
	protected GuiTomeInfoPage(PlayerEntity playerIn, String[] ids, boolean[] boolData, String oldChapter, int oldPage, String displayText) 
	{
		super(new StringTextComponent(" "));
		
		this.playerIn = playerIn;
		this.ids = ids;
		this.boolData = boolData;
		this.oldChapter = oldChapter;
		this.oldPage = oldPage;
		this.displayText = displayText;
		if(oldChapter == "vimion") BACKGROUND = new ResourceLocation(Main.modid + ":textures/gui/vimionite_tome_smooth.png");
		if(oldChapter == "necrion") BACKGROUND = new ResourceLocation(Main.modid + ":textures/gui/necrionite_tome_smooth.png");
		if(oldChapter == "solarion") BACKGROUND = new ResourceLocation(Main.modid + ":textures/gui/solarionite_tome_smooth.png");
		if(oldChapter == "nihilion") BACKGROUND = new ResourceLocation(Main.modid + ":textures/gui/nihilionite_tome_smooth.png");
		if(oldChapter == "expion") BACKGROUND = new ResourceLocation(Main.modid + ":textures/gui/expionite_tome_smooth.png");
	}
	
	@Override
	protected void init() 
	{
		this.buttons.clear();
		this.children.clear();
	    this.guiLeft = (this.width - this.xSize) / 2;
	    this.guiTop = (this.height - this.ySize) / 2;
	    
		if(findOptimalLines(displayText, wrapWidth).size() > 28)
		{
			this.addButton(new PageTurnButton(this.guiLeft + 230, this.guiTop + 144 + getOffsetYArray()[1], 20, 14, " ", (p_213029_1_) -> { this.advancePage(1); }));
		}
	    
		super.init();
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) 
	{		
		this.mousePosX = mouseX;
		this.mousePosY = mouseY;
		
		this.renderBackground();
		drawBackground();
		drawPage();
		super.render(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void onClose() 
	{
		this.minecraft.displayGuiScreen((Screen)null);
		
		GuiTome tome = new GuiTome(playerIn, ids, boolData, oldChapter, oldPage);
		Minecraft.getInstance().displayGuiScreen(tome);
	}
	
	public void drawBackground()
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
	    int i = this.guiLeft;
	    int j = (this.height - this.ySize) / 2;
	    this.blit(i, j, 0, 0, this.xSize, this.ySize);
	}
	
	public void drawText()
	{		
		int lineOffset = 28 * page;
		
		for(int i = 0; i < findOptimalLines(displayText, wrapWidth).size() - lineOffset; i++)
		{
			if(i < 14) this.getFontRenderer().drawString(findOptimalLines(displayText, wrapWidth).get(i + lineOffset), this.guiLeft + 10, this.guiTop + 6 + getOffsetYArray()[0] + (i * 10), 0);
			if(i >= 14 && i < 28) this.getFontRenderer().drawString(findOptimalLines(displayText, wrapWidth).get(i + lineOffset), this.guiLeft + (this.xSize / 2) + 10, this.guiTop + 6 + getOffsetYArray()[1] + ((i - 14) * 10), 0);
		}
	}
	
	public void drawPage()
	{
		drawText();
		if(findOptimalLines(displayText, wrapWidth).size() > page * 28 + 28)
		{
			drawForwardArrow(0);
			if(checkForHover(this.guiLeft + 230, this.guiTop + 144 + getOffsetYArray()[1], 20, 14)) drawForwardArrow(1);
		}
		if(page > 0)
		{
			drawBackwardArrow(0);
			if(checkForHover(this.guiLeft + 10, this.guiTop + 144 + getOffsetYArray()[0], 20, 14)) drawBackwardArrow(1);
		}
	}
	
	public boolean checkForHover(int x, int y, int width, int height)
	{
		if((mousePosX >= x && mousePosX < x + width) && (mousePosY >= y && mousePosY < y + height))
		{
			return true;
		}
		else return false;
	}
	
	public void advancePage(int amount)
	{
		this.buttons.clear();
		this.children.clear();
		page = page + amount;
		if(findOptimalLines(displayText, wrapWidth).size() > page * 27 + 27)
		{
			this.addButton(new PageTurnButton(this.guiLeft + 230, this.guiTop + 144 + getOffsetYArray()[1], 20, 14, " ", (p_213029_1_) -> { this.advancePage(1); }));
		}
		if(page > 0)
		{
			this.addButton(new PageTurnButton(this.guiLeft + 10, this.guiTop + 144 + getOffsetYArray()[0], 20, 14, " ", (p_213029_1_) -> { this.advancePage(-1); }));
		}
	}
	
	private List<String> findOptimalLines(String hover, int wrapWidth) 
	{
		if (hover.isEmpty()) 
		{
			return Collections.emptyList();
		} 
		else 
		{
			Minecraft mc = this.getMinecraft();
			List<String> list = mc.fontRenderer.listFormattedStringToWidth(hover, wrapWidth);
			if (list.size() < 2) 
			{
				return list;
			} 
			else 
			{
				String s = list.get(0);
				String s1 = list.get(1);
				int i = mc.fontRenderer.getStringWidth(s + ' ' + s1.split(" ")[0]);
				if (i - wrapWidth <= 10)
				{
					return mc.fontRenderer.listFormattedStringToWidth(hover, i);
				} 
				else 
				{
					Matcher matcher = PATTERN.matcher(s);
					if (matcher.matches()) 
					{
						int j = mc.fontRenderer.getStringWidth(matcher.group(1));
						if (wrapWidth - j <= 10) 
						{
							return mc.fontRenderer.listFormattedStringToWidth(hover, j);
						}
					}
					return list;
				}
			}
		}
	}
	
	public FontRenderer getFontRenderer()
	{
		return font;
	}
	
	public void drawForwardArrow(int state)
	{
		if(state == 0)
		{
			this.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		    int i = guiLeft + 230;
		    int j = (height - ySize) / 2;
		    blit(i, j + 144 + getOffsetYArray()[1], 0, 181, 20, 14);
		}
		else
		{
			this.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		    int i = guiLeft + 230;
		    int j = (height - ySize) / 2;
		    blit(i, j + 144 + getOffsetYArray()[1], 20, 181, 20, 14);
		}
	}
	
	public void drawBackwardArrow(int state)
	{
		if(state == 0)
		{
			this.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		    int i = guiLeft + 10;
		    int j = (height - ySize) / 2;
		    blit(i, j + 144 + getOffsetYArray()[0], 0, 167, 20, 14);
		}
		else
		{
			this.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		    int i = guiLeft + 10;
		    int j = (height - ySize) / 2;
		    blit(i, j + 144 + getOffsetYArray()[0], 20, 167, 20, 14);
		}
	}
	
	public int[] getOffsetYArray()
	{
		int offset1 = 2;
		int offset2 = -2;
		if(oldChapter == "necrion") { offset1 = 1; offset2 = -1; }
		if(oldChapter == "solarion") { offset1 = 0; offset2 = 0; }
		if(oldChapter == "nihilion") { offset1 = -1; offset2 = 1; }
		if(oldChapter == "expion") { offset1 = -2; offset2 = 2; }
		int[] offsets = {offset1, offset2};
		return offsets;
	}
	
	@OnlyIn(Dist.CLIENT)
    public static class PageTurnButton extends Button
    {		
		public PageTurnButton(int xPos, int yPos, int width, int height, String text, IPressable onPress) 
		{
			super(xPos, yPos, width, height, text, onPress);
		}
		
		
		@Override
		public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) 
		{
			
		}
    }
}
