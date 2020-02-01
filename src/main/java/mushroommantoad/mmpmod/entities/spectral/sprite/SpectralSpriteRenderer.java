package mushroommantoad.mmpmod.entities.spectral.sprite;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralSpriteRenderer extends MobRenderer<SpectralSpriteEntity, SpectralSpriteModel>
{
	public SpectralSpriteRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralSpriteModel(), 0f);
	}

	@Override
	public ResourceLocation getEntityTexture(SpectralSpriteEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/sprite.png");
	}
	
	@Override
	protected boolean func_225622_a_(SpectralSpriteEntity entity) 
	{
		return false;
	}
}
