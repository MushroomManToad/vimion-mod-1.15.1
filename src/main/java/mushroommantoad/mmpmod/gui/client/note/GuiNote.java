package mushroommantoad.mmpmod.gui.client.note;

import java.util.ArrayList;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import mushroommantoad.mmpmod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.Button.IPressable;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("unused")
@OnlyIn(Dist.CLIENT)
public class GuiNote extends Screen 
{
	public static final ResourceLocation VIMION_NOTE = new ResourceLocation(Main.modid + ":textures/gui/vimionic_note.png");
    public int xSize = 166;
    public int ySize = 250;
    public int guiLeft;
    public int guiTop;
    protected PlayerEntity player;
    
    int mousePosX = 0;
    int mousePosY = 0;
	   
	public GuiNote(PlayerEntity player) 
	{
		// Set GUI Title
		super(new StringTextComponent(" "));
		
		this.player = player;
	}

	// Set any variables immediately upon opening the GUI
	// In this case, I've set a couple of variables used by `render` to re-scale and center the GUI at any window size
	@Override
	protected void init() 
	{
		this.buttons.clear();
		this.children.clear();
	    this.guiLeft = (this.width - this.xSize) / 2;
	    this.guiTop = (this.height - this.ySize) / 2;
	    
		super.init();
	}
	
	// Replaces the old draw() function
	// Taken from CraftingScreen.java -- Draws a background from the specified texture.
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) 
	{
		this.mousePosX = mouseX;
		this.mousePosY = mouseY;
		
		this.renderBackground();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.getMinecraft().getTextureManager().bindTexture(VIMION_NOTE);
	    int i = guiLeft;
	    int j = (height - ySize) / 2;
	    blit(i, j, 0, 0, xSize, ySize);	    
		super.render(mouseX, mouseY, partialTicks);
	}
	
	public PlayerEntity getPlayer()
	{
		return player;
	}
	
	public FontRenderer getFontRenderer()
	{
		return font;
	}
}
