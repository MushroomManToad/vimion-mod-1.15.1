package mushroommantoad.mmpmod.entities.boss.solarionic_abomination;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SolarionicAbominationEyesLayer<T extends Entity> extends AbstractEyesLayer<T, SolarionicAbominationModel<T>> 
{
	private static final RenderType TEXTURE = RenderType.getEyes(ModEntities.location("textures/entity/boss/solarionic_abomination_eyes.png"));
	
	public SolarionicAbominationEyesLayer(IEntityRenderer<T, SolarionicAbominationModel<T>> p_i50928_1_) 
	{
		super(p_i50928_1_);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if(entitylivingbaseIn instanceof SolarionicAbominationEntity)
		{
			SolarionicAbominationEntity sa = (SolarionicAbominationEntity) entitylivingbaseIn;
			if(!sa.isLunarionic())
			{
				IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.getRenderType());
				this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}	
		}
	}

	@Override
	public RenderType getRenderType() {
		return TEXTURE;
	}
}
