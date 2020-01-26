package mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererAbsorptionSpire extends EntityRenderer<EntityAbsorptionSpire>
{
	   private final ModelAbsorptionSpire model = new ModelAbsorptionSpire();
	   private final ResourceLocation TEXTURE = ModEntities.location("textures/entity/boss/absorption_spire.png");

	   public RendererAbsorptionSpire(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn);
	   }

	@Override
	public ResourceLocation getEntityTexture(EntityAbsorptionSpire entity) 
	{
		return TEXTURE;
	}

	// New doRender ??
	public void func_225623_a_(EntityAbsorptionSpire entity, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_)
	{
	    RenderSystem.pushMatrix();
	    RenderSystem.disableCull();
	    RenderSystem.enableAlphaTest();
	    IVertexBuilder ivertexbuilder = p_225623_5_.getBuffer(this.model.func_228282_a_(this.getEntityTexture(entity)));
	    RenderSystem.translatef(0.0F, 1F, 0.0F);
	    float scaleFactor = 2.0f;
	    RenderSystem.scalef(scaleFactor, scaleFactor, scaleFactor);
	    this.model.func_225597_a_(entity, p_225623_3_, 0.0F, entity.ticksExisted, 0.0F, 0.0F);
	    this.model.func_225598_a_(p_225623_4_, ivertexbuilder, p_225623_6_, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
	    RenderSystem.popMatrix();
	    RenderSystem.enableCull();
		super.func_225623_a_(entity, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
	}
	
	public static class RenderFactory implements IRenderFactory<EntityAbsorptionSpire>
	{
		public EntityRenderer<? super EntityAbsorptionSpire> createRenderFor(EntityRendererManager manager)
		{
			return new RendererAbsorptionSpire(manager);
		}
	}
}
