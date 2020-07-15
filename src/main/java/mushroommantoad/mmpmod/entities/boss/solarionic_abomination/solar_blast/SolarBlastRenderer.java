package mushroommantoad.mmpmod.entities.boss.solarionic_abomination.solar_blast;

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
public class SolarBlastRenderer extends EntityRenderer<SolarBlastEntity>
{
	   private final SolarBlastModel model = new SolarBlastModel();
	   private final ResourceLocation YELLOW = ModEntities.location("textures/entity/boss/solar_blast.png");
	   private final ResourceLocation BLUE = ModEntities.location("textures/entity/boss/blue_solar_blast.png");
	   private final ResourceLocation RED = ModEntities.location("textures/entity/boss/red_solar_blast.png");
	   private final ResourceLocation ORANGE = ModEntities.location("textures/entity/boss/orange_solar_blast.png");
	   private final ResourceLocation WHITE = ModEntities.location("textures/entity/boss/white_solar_blast.png");
	   private final ResourceLocation BLACK = ModEntities.location("textures/entity/boss/black_solar_blast.png");
	   private final ResourceLocation PURPLE = ModEntities.location("textures/entity/boss/purple_solar_blast.png");

	   public SolarBlastRenderer(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn);
	   }

	@Override
	public ResourceLocation getEntityTexture(SolarBlastEntity entity) 
	{
		switch(entity.getStellarType())
		{
		case(1): return BLUE;
		case(2): return RED;
		case(3): return ORANGE;
		case(4): return WHITE;
		case(5): return BLACK;
		case(6): return PURPLE;
		default: return YELLOW;	
		}
	}

	// New doRender ??
	public void render(SolarBlastEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
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
	
	public static class RenderFactory implements IRenderFactory<SolarBlastEntity>
	{
		public EntityRenderer<? super SolarBlastEntity> createRenderFor(EntityRendererManager manager)
		{
			return new SolarBlastRenderer(manager);
		}
	}
}
