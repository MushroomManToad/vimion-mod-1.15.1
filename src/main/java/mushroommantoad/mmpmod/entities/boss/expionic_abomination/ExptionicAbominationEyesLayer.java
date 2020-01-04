package mushroommantoad.mmpmod.entities.boss.expionic_abomination;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExptionicAbominationEyesLayer<T extends Entity> extends LayerRenderer<T, ExpionicAbominationModel<T>> 
{
	private static final ResourceLocation TEXTURE = ModEntities.location("textures/entity/boss/expionic_abomination_eyes.png");
	
	public ExptionicAbominationEyesLayer(IEntityRenderer<T, ExpionicAbominationModel<T>> p_i50928_1_) 
	{
		super(p_i50928_1_);
	}

	public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) 
	{
		this.bindTexture(TEXTURE);
		GlStateManager.enableBlend();
		GlStateManager.disableAlphaTest();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
		GlStateManager.disableLighting();
		GlStateManager.depthMask(!entityIn.isInvisible());
		GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 61680.0F, 0.0F);
		GlStateManager.enableLighting();
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
		gamerenderer.setupFogColor(true);
		this.getEntityModel().render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
		gamerenderer.setupFogColor(false);
		this.func_215334_a(entityIn);
		GlStateManager.depthMask(true);
		GlStateManager.disableBlend();
		GlStateManager.enableAlphaTest();
	}

	public boolean shouldCombineTextures() 
	{
		return false;
	}
}
