package mushroommantoad.mmpmod.entities.spectral.sheep;

import com.mojang.blaze3d.matrix.MatrixStack;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.IRenderTypeBuffer;
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
	public void func_225628_a_(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, SpectralSheepEntity entityIn, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) 
	{
		if (!entityIn.isInvisible()) 
		{
			this.getEntityModel().setModelAttributes(this.sheepModel);
			func_229140_a_(this.getEntityModel(), this.sheepModel, TEXTURE, p_225628_1_, p_225628_2_, p_225628_3_, entityIn, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_, 0, 1, 1, 1);
		}		
	}
}
