package mushroommantoad.mmpmod.entities.spectral.cow;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralCowModel extends QuadrupedModel<SpectralCowEntity>
{
	   public SpectralCowModel() 
	   {
	      super(12, 0.0F);
	      this.headModel = new RendererModel(this, 0, 0);
	      this.headModel.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
	      this.headModel.setRotationPoint(0.0F, 4.0F, -8.0F);
	      this.headModel.setTextureOffset(22, 0).addBox(-5.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
	      this.headModel.setTextureOffset(22, 0).addBox(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
	      this.body = new RendererModel(this, 18, 4);
	      this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
	      this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
	      this.body.setTextureOffset(52, 0).addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1);
	      --this.legBackRight.rotationPointX;
	      ++this.legBackLeft.rotationPointX;
	      this.legBackRight.rotationPointZ += 0.0F;
	      this.legBackLeft.rotationPointZ += 0.0F;
	      --this.legFrontRight.rotationPointX;
	      ++this.legFrontLeft.rotationPointX;
	      --this.legFrontRight.rotationPointZ;
	      --this.legFrontLeft.rotationPointZ;
	      this.childZOffset += 2.0F;
	   }

	   public RendererModel getHead() {
	      return this.headModel;
	   }
}
