package mushroommantoad.mmpmod.entities.spectral.chicken;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralChickenRenderer extends MobRenderer<SpectralChickenEntity, SpectralChickenModel> {

	private static final ResourceLocation TEXTURE = ModEntities.location("textures/entity/spectral/spectral_chicken.png");
	
	public SpectralChickenRenderer(EntityRendererManager manager) {
		super(manager, new SpectralChickenModel(), 0.0F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(SpectralChickenEntity entity) {
		return TEXTURE;
	}
}
