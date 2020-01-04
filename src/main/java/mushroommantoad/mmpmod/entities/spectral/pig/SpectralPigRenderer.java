package mushroommantoad.mmpmod.entities.spectral.pig;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class SpectralPigRenderer extends MobRenderer<SpectralPigEntity, SpectralPigModel>
{
	public SpectralPigRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralPigModel(), 0f);
	}

	@Override
	protected ResourceLocation getEntityTexture(SpectralPigEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/spectral_pig.png");
	}
	
	public static class RenderFactory implements IRenderFactory<SpectralPigEntity>
	{
		public EntityRenderer<? super SpectralPigEntity> createRenderFor(EntityRendererManager manager)
		{
			return new SpectralPigRenderer(manager);
		}
	}
}
