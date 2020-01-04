package mushroommantoad.mmpmod.entities.spectral.rabbit;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class SpectralRabbitRenderer extends MobRenderer<SpectralRabbitEntity, SpectralRabbitModel>
{
	public SpectralRabbitRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralRabbitModel(), 0f);
	}

	@Override
	protected ResourceLocation getEntityTexture(SpectralRabbitEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/spectral_rabbit.png");
	}
	
	public static class RenderFactory implements IRenderFactory<SpectralRabbitEntity>
	{
		public EntityRenderer<? super SpectralRabbitEntity> createRenderFor(EntityRendererManager manager)
		{
			return new SpectralRabbitRenderer(manager);
		}
	}
}
