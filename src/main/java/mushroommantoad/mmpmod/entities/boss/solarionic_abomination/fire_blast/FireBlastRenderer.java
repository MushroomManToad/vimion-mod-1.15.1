package mushroommantoad.mmpmod.entities.boss.solarionic_abomination.fire_blast;

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
public class FireBlastRenderer extends EntityRenderer<FireBlastEntity>
{
	   private final FireBlastModel model = new FireBlastModel();
	   private final ResourceLocation TEXTURE = ModEntities.location("textures/entity/boss/fire_blast.png");

	   public FireBlastRenderer(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn);
	   }

	@Override
	public ResourceLocation getEntityTexture(FireBlastEntity entity) 
	{
		return TEXTURE;
	}

	// New doRender ??
	public void render(FireBlastEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
	    RenderSystem.pushMatrix();
	    RenderSystem.disableCull();
	    RenderSystem.enableAlphaTest();
	    IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(this.getEntityTexture(entityIn)));
	    float scaleFactor = 2.0f;
	    RenderSystem.translatef(10.0f, 10.0f, 10.0f);
	    RenderSystem.scalef(scaleFactor, scaleFactor, scaleFactor);
	    this.model.setRotationAngles(entityIn, partialTicks, 0.0F, entityIn.ticksExisted, 0.0F, 0.0F);
	    this.model.render(matrixStackIn, ivertexbuilder, 150, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	    // This number here controls the brightness of   ^^^ the entity. Not sure what the numbers mean, but 150 seems pretty bright :P
	    RenderSystem.popMatrix();
	    RenderSystem.enableCull();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	public static class RenderFactory implements IRenderFactory<FireBlastEntity>
	{
		public EntityRenderer<? super FireBlastEntity> createRenderFor(EntityRendererManager manager)
		{
			return new FireBlastRenderer(manager);
		}
	}
}
