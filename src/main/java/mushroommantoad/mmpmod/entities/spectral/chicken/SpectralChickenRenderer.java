package mushroommantoad.mmpmod.entities.spectral.chicken;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class SpectralChickenRenderer extends MobRenderer<SpectralChickenEntity, SpectralChickenModel>
{
	public SpectralChickenRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralChickenModel(), 0f);
	}

	@Override
	protected ResourceLocation getEntityTexture(SpectralChickenEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/spectral_chicken.png");
	}
	
	public static class RenderFactory implements IRenderFactory<SpectralChickenEntity>
	{
		public EntityRenderer<? super SpectralChickenEntity> createRenderFor(EntityRendererManager manager)
		{
			return new SpectralChickenRenderer(manager);
		}
	}
}
