package mushroommantoad.mmpmod.entities.spectral.pig;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralPigModel extends QuadrupedModel<SpectralPigEntity>
{
	public SpectralPigModel() 
	{
		this(0.0F);
	}

	public SpectralPigModel(float scale) 
	{
		super(6, scale, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
		this.headModel.setTextureOffset(16, 16).func_228301_a_(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, scale);
	}
}
