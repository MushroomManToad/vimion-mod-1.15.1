package mushroommantoad.mmpmod.entities.spectral.sheep;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralSheepModel extends QuadrupedModel<SpectralSheepEntity>
{
	private float headRotationAngleX;
	
	public SpectralSheepModel() 
	{
		super(12, 0.0F);
		this.headModel = new RendererModel(this, 0, 0);
		this.headModel.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
		this.headModel.setRotationPoint(0.0F, 6.0F, -8.0F);
		this.body = new RendererModel(this, 28, 8);
		this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
		this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
	}
		
	public void setLivingAnimations(SpectralSheepEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) 
	{
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.headModel.rotationPointY = 6.0F + entityIn.getHeadRotationPointY(partialTick) * 9.0F;
		this.headRotationAngleX = entityIn.getHeadRotationAngleX(partialTick);
	}
		
	public void setRotationAngles(SpectralSheepEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) 
	{
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		this.headModel.rotateAngleX = this.headRotationAngleX;
	}
	
}
