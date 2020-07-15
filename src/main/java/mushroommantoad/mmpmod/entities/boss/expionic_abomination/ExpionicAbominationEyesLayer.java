package mushroommantoad.mmpmod.entities.boss.expionic_abomination;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExpionicAbominationEyesLayer<T extends Entity> extends AbstractEyesLayer<T, ExpionicAbominationModel<T>> 
{
	private static final RenderType TEXTURE = RenderType.getEyes(ModEntities.location("textures/entity/boss/expionic_abomination_eyes.png"));
	
	public ExpionicAbominationEyesLayer(IEntityRenderer<T, ExpionicAbominationModel<T>> p_i50928_1_) 
	{
		super(p_i50928_1_);
	}

	/*
	public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) 
	{
		this.bindTexture(TEXTURE);
		RenderSystem.enableBlend();
		RenderSystem.disableAlphaTest();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
		RenderSystem.disableLighting();
		RenderSystem.depthMask(!entityIn.isInvisible());
		GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 61680.0F, 0.0F);
		RenderSystem.enableLighting();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
		gamerenderer.setupFogColor(true);
		this.getEntityModel().render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
		gamerenderer.setupFogColor(false);
		this.func_215334_a(entityIn);
		RenderSystem.depthMask(true);
		RenderSystem.disableBlend();
		RenderSystem.enableAlphaTest();
	}
	*/

	@Override
	public RenderType getRenderType() {
		return TEXTURE;
	}
}
