package mushroommantoad.mmpmod.entities.spectral.sheep;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralSheepModel extends QuadrupedModel<SpectralSheepEntity>
{
	private float headRotationAngleX;
	
	   public SpectralSheepModel() {
		      super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
		      this.headModel = new ModelRenderer(this, 0, 0);
		      this.headModel.addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, 0.0F);
		      this.headModel.setRotationPoint(0.0F, 6.0F, -8.0F);
		      this.body = new ModelRenderer(this, 28, 8);
		      this.body.addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, 0.0F);
		      this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
		   }

		   public void setLivingAnimations(SpectralSheepEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		      super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
		      this.headModel.rotationPointY = 6.0F + entityIn.getHeadRotationPointY(partialTick) * 9.0F;
		      this.headRotationAngleX = entityIn.getHeadRotationAngleX(partialTick);
		   }

		   public void setRotationAngles(SpectralSheepEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
		      super.setRotationAngles(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
		      this.headModel.rotateAngleX = this.headRotationAngleX;
		   }
}
