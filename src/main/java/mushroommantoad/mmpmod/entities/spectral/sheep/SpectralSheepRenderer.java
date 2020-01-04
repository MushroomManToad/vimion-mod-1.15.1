package mushroommantoad.mmpmod.entities.spectral.sheep;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class SpectralSheepRenderer extends MobRenderer<SpectralSheepEntity, SpectralSheepModel>
{
	public SpectralSheepRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralSheepModel(), 0f);
		this.addLayer(new SpectralSheepWoolLayer(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(SpectralSheepEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/spectral_sheep.png");
	}
	
	public static class RenderFactory implements IRenderFactory<SpectralSheepEntity>
	{
		public EntityRenderer<? super SpectralSheepEntity> createRenderFor(EntityRendererManager manager)
		{
			return new SpectralSheepRenderer(manager);
		}
	}
}
