package mushroommantoad.mmpmod.entities.spectral.pig;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralPigRenderer extends MobRenderer<SpectralPigEntity, SpectralPigModel>
{
	public SpectralPigRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralPigModel(), 0f);
	}

	@Override
	public ResourceLocation getEntityTexture(SpectralPigEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/spectral_pig.png");
	}
}
