package mushroommantoad.mmpmod.entities.spectral.rabbit;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralRabbitRenderer extends MobRenderer<SpectralRabbitEntity, SpectralRabbitModel>
{
	public SpectralRabbitRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralRabbitModel(), 0f);
	}

	@Override
	public ResourceLocation getEntityTexture(SpectralRabbitEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/spectral_rabbit.png");
	}
}
