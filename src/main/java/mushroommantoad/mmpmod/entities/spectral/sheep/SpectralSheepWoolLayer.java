package mushroommantoad.mmpmod.entities.spectral.sheep;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralSheepWoolLayer extends LayerRenderer<SpectralSheepEntity, SpectralSheepModel>
{
	private static final ResourceLocation TEXTURE = ModEntities.location("textures/entity/spectral/spectral_sheep_fur.png");
	private final SpectralSheepWoolModel<SpectralSheepEntity> sheepModel = new SpectralSheepWoolModel<>();
	
	public SpectralSheepWoolLayer(IEntityRenderer<SpectralSheepEntity, SpectralSheepModel> entityRendererIn) 
	{
		super(entityRendererIn);
	}

	@Override
	public void render(SpectralSheepEntity entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) 
	{
		if (!entityIn.isInvisible()) 
		{
			this.bindTexture(TEXTURE);

			this.getEntityModel().setModelAttributes(this.sheepModel);
			this.sheepModel.setLivingAnimations(entityIn, p_212842_2_, p_212842_3_, p_212842_4_);
			this.sheepModel.render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
		}
	}

	@Override
	public boolean shouldCombineTextures() 
	{
		return true;
	}
}
