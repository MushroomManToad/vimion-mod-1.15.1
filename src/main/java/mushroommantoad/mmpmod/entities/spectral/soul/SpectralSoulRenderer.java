package mushroommantoad.mmpmod.entities.spectral.soul;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralSoulRenderer extends MobRenderer<SpectralSoulEntity, SpectralSoulModel>
{
	public SpectralSoulRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralSoulModel(), 0f);
	}

	@Override
	public ResourceLocation getEntityTexture(SpectralSoulEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/sprite.png");
	}
	
	@Override
	protected boolean isVisible(SpectralSoulEntity entity) 
	{
		return false;
	}
}
