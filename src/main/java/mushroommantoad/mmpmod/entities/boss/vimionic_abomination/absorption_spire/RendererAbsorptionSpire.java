package mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire;

import com.mojang.blaze3d.platform.GlStateManager;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RendererAbsorptionSpire extends EntityRenderer<EntityAbsorptionSpire>
{
	   private final ModelAbsorptionSpire model = new ModelAbsorptionSpire();

	   public RendererAbsorptionSpire(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn);
	   }

	@Override
	protected ResourceLocation getEntityTexture(EntityAbsorptionSpire entity) 
	{
		return ModEntities.location("textures/entity/boss/absorption_spire.png");
	}
	
	@Override
	public void doRender(EntityAbsorptionSpire entity, double x, double y, double z, float entityYaw, float partialTicks) {	
	     GlStateManager.pushMatrix();
	     GlStateManager.disableCull();
	     GlStateManager.enableAlphaTest();
	     this.bindEntityTexture(entity);
	     GlStateManager.translatef((float)x, (float)y, (float)z);
	     GlStateManager.translatef(0.0F, 1F, 0.0F);
	     float scaleFactor = 2.0f;
	     GlStateManager.scalef(scaleFactor, scaleFactor, scaleFactor);
	     this.model.render(entity, 0.0F, 0.0F, entity.ticksExisted + partialTicks, entity.rotationYaw, entity.rotationPitch, 0.05F);
	     GlStateManager.popMatrix();
	     GlStateManager.enableCull();
	     super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	public static class RenderFactory implements IRenderFactory<EntityAbsorptionSpire>
	{
		public EntityRenderer<? super EntityAbsorptionSpire> createRenderFor(EntityRendererManager manager)
		{
			return new RendererAbsorptionSpire(manager);
		}
	}
}
