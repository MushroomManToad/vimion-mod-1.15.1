package mushroommantoad.mmpmod.entities.spectral.rabbit;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralRabbitModel extends EntityModel<SpectralRabbitEntity>
{
	   private final ModelRenderer rabbitLeftFoot = new ModelRenderer(this, 26, 24);
	   private final ModelRenderer rabbitRightFoot;
	   private final ModelRenderer rabbitLeftThigh;
	   private final ModelRenderer rabbitRightThigh;
	   private final ModelRenderer rabbitBody;
	   private final ModelRenderer rabbitLeftArm;
	   private final ModelRenderer rabbitRightArm;
	   private final ModelRenderer rabbitHead;
	   private final ModelRenderer rabbitRightEar;
	   private final ModelRenderer rabbitLeftEar;
	   private final ModelRenderer rabbitTail;
	   private final ModelRenderer rabbitNose;
	   private float jumpRotation;

	   public SpectralRabbitModel() {
	      this.rabbitLeftFoot.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
	      this.rabbitLeftFoot.setRotationPoint(3.0F, 17.5F, 3.7F);
	      this.rabbitLeftFoot.mirror = true;
	      this.setRotationOffset(this.rabbitLeftFoot, 0.0F, 0.0F, 0.0F);
	      this.rabbitRightFoot = new ModelRenderer(this, 8, 24);
	      this.rabbitRightFoot.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
	      this.rabbitRightFoot.setRotationPoint(-3.0F, 17.5F, 3.7F);
	      this.rabbitRightFoot.mirror = true;
	      this.setRotationOffset(this.rabbitRightFoot, 0.0F, 0.0F, 0.0F);
	      this.rabbitLeftThigh = new ModelRenderer(this, 30, 15);
	      this.rabbitLeftThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
	      this.rabbitLeftThigh.setRotationPoint(3.0F, 17.5F, 3.7F);
	      this.rabbitLeftThigh.mirror = true;
	      this.setRotationOffset(this.rabbitLeftThigh, -0.34906584F, 0.0F, 0.0F);
	      this.rabbitRightThigh = new ModelRenderer(this, 16, 15);
	      this.rabbitRightThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
	      this.rabbitRightThigh.setRotationPoint(-3.0F, 17.5F, 3.7F);
	      this.rabbitRightThigh.mirror = true;
	      this.setRotationOffset(this.rabbitRightThigh, -0.34906584F, 0.0F, 0.0F);
	      this.rabbitBody = new ModelRenderer(this, 0, 0);
	      this.rabbitBody.addBox(-3.0F, -2.0F, -10.0F, 6, 5, 10);
	      this.rabbitBody.setRotationPoint(0.0F, 19.0F, 8.0F);
	      this.rabbitBody.mirror = true;
	      this.setRotationOffset(this.rabbitBody, -0.34906584F, 0.0F, 0.0F);
	      this.rabbitLeftArm = new ModelRenderer(this, 8, 15);
	      this.rabbitLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
	      this.rabbitLeftArm.setRotationPoint(3.0F, 17.0F, -1.0F);
	      this.rabbitLeftArm.mirror = true;
	      this.setRotationOffset(this.rabbitLeftArm, -0.17453292F, 0.0F, 0.0F);
	      this.rabbitRightArm = new ModelRenderer(this, 0, 15);
	      this.rabbitRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
	      this.rabbitRightArm.setRotationPoint(-3.0F, 17.0F, -1.0F);
	      this.rabbitRightArm.mirror = true;
	      this.setRotationOffset(this.rabbitRightArm, -0.17453292F, 0.0F, 0.0F);
	      this.rabbitHead = new ModelRenderer(this, 32, 0);
	      this.rabbitHead.addBox(-2.5F, -4.0F, -5.0F, 5, 4, 5);
	      this.rabbitHead.setRotationPoint(0.0F, 16.0F, -1.0F);
	      this.rabbitHead.mirror = true;
	      this.setRotationOffset(this.rabbitHead, 0.0F, 0.0F, 0.0F);
	      this.rabbitRightEar = new ModelRenderer(this, 52, 0);
	      this.rabbitRightEar.addBox(-2.5F, -9.0F, -1.0F, 2, 5, 1);
	      this.rabbitRightEar.setRotationPoint(0.0F, 16.0F, -1.0F);
	      this.rabbitRightEar.mirror = true;
	      this.setRotationOffset(this.rabbitRightEar, 0.0F, -0.2617994F, 0.0F);
	      this.rabbitLeftEar = new ModelRenderer(this, 58, 0);
	      this.rabbitLeftEar.addBox(0.5F, -9.0F, -1.0F, 2, 5, 1);
	      this.rabbitLeftEar.setRotationPoint(0.0F, 16.0F, -1.0F);
	      this.rabbitLeftEar.mirror = true;
	      this.setRotationOffset(this.rabbitLeftEar, 0.0F, 0.2617994F, 0.0F);
	      this.rabbitTail = new ModelRenderer(this, 52, 6);
	      this.rabbitTail.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2);
	      this.rabbitTail.setRotationPoint(0.0F, 20.0F, 7.0F);
	      this.rabbitTail.mirror = true;
	      this.setRotationOffset(this.rabbitTail, -0.3490659F, 0.0F, 0.0F);
	      this.rabbitNose = new ModelRenderer(this, 32, 9);
	      this.rabbitNose.addBox(-0.5F, -2.5F, -5.5F, 1, 1, 1);
	      this.rabbitNose.setRotationPoint(0.0F, 16.0F, -1.0F);
	      this.rabbitNose.mirror = true;
	      this.setRotationOffset(this.rabbitNose, 0.0F, 0.0F, 0.0F);
	   }

	   private void setRotationOffset(ModelRenderer renderer, float x, float y, float z) {
	      renderer.rotateAngleX = x;
	      renderer.rotateAngleY = y;
	      renderer.rotateAngleZ = z;
	   }

	   public void render(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		      if (this.isChild) {
		         p_225598_1_.push();
		         p_225598_1_.scale(0.56666666F, 0.56666666F, 0.56666666F);
		         p_225598_1_.translate(0.0D, 1.375D, 0.125D);
		         ImmutableList.of(this.rabbitHead, this.rabbitLeftEar, this.rabbitRightEar, this.rabbitNose).forEach((p_228292_8_) -> {
		            p_228292_8_.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
		         });
		         p_225598_1_.pop();
		         p_225598_1_.push();
		         p_225598_1_.scale(0.4F, 0.4F, 0.4F);
		         p_225598_1_.translate(0.0D, 2.25D, 0.0D);
		         ImmutableList.of(this.rabbitLeftFoot, this.rabbitRightFoot, this.rabbitLeftThigh, this.rabbitRightThigh, this.rabbitBody, this.rabbitLeftArm, this.rabbitRightArm, this.rabbitTail).forEach((p_228291_8_) -> {
		            p_228291_8_.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
		         });
		         p_225598_1_.pop();
		      } else {
		         p_225598_1_.push();
		         p_225598_1_.scale(0.6F, 0.6F, 0.6F);
		         p_225598_1_.translate(0.0D, 1.0D, 0.0D);
		         ImmutableList.of(this.rabbitLeftFoot, this.rabbitRightFoot, this.rabbitLeftThigh, this.rabbitRightThigh, this.rabbitBody, this.rabbitLeftArm, this.rabbitRightArm, this.rabbitHead, this.rabbitRightEar, this.rabbitLeftEar, this.rabbitTail, this.rabbitNose).forEach((p_228290_8_) -> {
		            p_228290_8_.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
		         });
		         p_225598_1_.pop();
		      }

		   }

		   public void setRotationAngles(SpectralRabbitEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
		      float f = p_225597_4_ - (float)p_225597_1_.ticksExisted;
		      this.rabbitNose.rotateAngleX = p_225597_6_ * ((float)Math.PI / 180F);
		      this.rabbitHead.rotateAngleX = p_225597_6_ * ((float)Math.PI / 180F);
		      this.rabbitRightEar.rotateAngleX = p_225597_6_ * ((float)Math.PI / 180F);
		      this.rabbitLeftEar.rotateAngleX = p_225597_6_ * ((float)Math.PI / 180F);
		      this.rabbitNose.rotateAngleY = p_225597_5_ * ((float)Math.PI / 180F);
		      this.rabbitHead.rotateAngleY = p_225597_5_ * ((float)Math.PI / 180F);
		      this.rabbitRightEar.rotateAngleY = this.rabbitNose.rotateAngleY - 0.2617994F;
		      this.rabbitLeftEar.rotateAngleY = this.rabbitNose.rotateAngleY + 0.2617994F;
		      this.jumpRotation = MathHelper.sin(p_225597_1_.getJumpCompletion(f) * (float)Math.PI);
		      this.rabbitLeftThigh.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
		      this.rabbitRightThigh.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
		      this.rabbitLeftFoot.rotateAngleX = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
		      this.rabbitRightFoot.rotateAngleX = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
		      this.rabbitLeftArm.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
		      this.rabbitRightArm.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
		   }

		   public void setLivingAnimations(SpectralRabbitEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		      super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
		      this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(partialTick) * (float)Math.PI);
		   }
}
