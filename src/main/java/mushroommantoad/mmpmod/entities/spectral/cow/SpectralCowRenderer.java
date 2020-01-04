package mushroommantoad.mmpmod.entities.spectral.cow;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class SpectralCowRenderer extends MobRenderer<SpectralCowEntity, SpectralCowModel>
{
	public SpectralCowRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralCowModel(), 0f);
	}

	@Override
	protected ResourceLocation getEntityTexture(SpectralCowEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/spectral_cow.png");
	}
	
	public static class RenderFactory implements IRenderFactory<SpectralCowEntity>
	{
		public EntityRenderer<? super SpectralCowEntity> createRenderFor(EntityRendererManager manager)
		{
			return new SpectralCowRenderer(manager);
		}
	}
}
