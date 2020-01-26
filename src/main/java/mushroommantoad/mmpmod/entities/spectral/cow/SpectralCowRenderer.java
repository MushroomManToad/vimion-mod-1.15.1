package mushroommantoad.mmpmod.entities.spectral.cow;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralCowRenderer extends MobRenderer<SpectralCowEntity, SpectralCowModel>
{
	public SpectralCowRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralCowModel(), 0f);
	}

	@Override
	public ResourceLocation getEntityTexture(SpectralCowEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/spectral_cow.png");
	}
}
