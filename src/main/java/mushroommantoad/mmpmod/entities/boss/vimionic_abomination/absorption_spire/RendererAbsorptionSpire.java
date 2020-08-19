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
	public void render(EntityAbsorptionSpire entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
	    RenderSystem.pushMatrix();
	    RenderSystem.disableCull();
	    RenderSystem.enableAlphaTest();
	    IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(this.getEntityTexture(entityIn)));
	    RenderSystem.translatef(0.0F, 1F, 0.0F);
	    float scaleFactor = 2.0f;
	    RenderSystem.scalef(scaleFactor, scaleFactor, scaleFactor);
	    this.model.setRotationAngles(entityIn, partialTicks, 0.0F, entityIn.ticksExisted, 0.0F, 0.0F);
	    this.model.render(matrixStackIn, ivertexbuilder, 180, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	    RenderSystem.popMatrix();
	    RenderSystem.enableCull();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	public static class RenderFactory implements IRenderFactory<EntityAbsorptionSpire>
	{
		public EntityRenderer<? super EntityAbsorptionSpire> createRenderFor(EntityRendererManager manager)
		{
			return new RendererAbsorptionSpire(manager);
		}
	}
}
