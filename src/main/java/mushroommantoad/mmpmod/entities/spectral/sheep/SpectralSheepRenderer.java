package mushroommantoad.mmpmod.entities.spectral.sheep;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralSheepRenderer extends MobRenderer<SpectralSheepEntity, SpectralSheepModel>
{
	public SpectralSheepRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralSheepModel(), 0f);
		this.addLayer(new SpectralSheepWoolLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(SpectralSheepEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/spectral_sheep.png");
	}
}
