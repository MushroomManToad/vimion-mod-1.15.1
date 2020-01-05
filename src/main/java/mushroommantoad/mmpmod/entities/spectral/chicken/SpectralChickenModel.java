package mushroommantoad.mmpmod.entities.spectral.chicken;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralChickenModel extends EntityModel<SpectralChickenEntity>
{
   private final ModelRenderer head;
   private final ModelRenderer bill;
   private final ModelRenderer chin;
   private final ModelRenderer body;
   private final ModelRenderer rightWing;
   private final ModelRenderer leftWing;
   private final ModelRenderer field_78137_g;
   private final ModelRenderer field_78143_h;

   public SpectralChickenModel() {
      this.head = new ModelRenderer(this, 0, 0);
      this.head.func_228301_a_(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
      this.head.setRotationPoint(0.0F, 15.0F, -4.0F);
      this.field_78137_g = new ModelRenderer(this, 14, 0);
      this.field_78137_g.func_228301_a_(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
      this.field_78137_g.setRotationPoint(0.0F, 15.0F, -4.0F);
      this.field_78143_h = new ModelRenderer(this, 14, 4);
      this.field_78143_h.func_228301_a_(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
      this.field_78143_h.setRotationPoint(0.0F, 15.0F, -4.0F);
      this.bill = new ModelRenderer(this, 0, 9);
      this.bill.func_228301_a_(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
      this.bill.setRotationPoint(0.0F, 16.0F, 0.0F);
      this.chin = new ModelRenderer(this, 26, 0);
      this.chin.func_228300_a_(-1.0F, 0.0F, -3.0F, 3, 5, 3);
      this.chin.setRotationPoint(-2.0F, 19.0F, 1.0F);
      this.body = new ModelRenderer(this, 26, 0);
      this.body.func_228300_a_(-1.0F, 0.0F, -3.0F, 3, 5, 3);
      this.body.setRotationPoint(1.0F, 19.0F, 1.0F);
      this.rightWing = new ModelRenderer(this, 24, 13);
      this.rightWing.func_228300_a_(0.0F, 0.0F, -3.0F, 1, 4, 6);
      this.rightWing.setRotationPoint(-4.0F, 13.0F, 0.0F);
      this.leftWing = new ModelRenderer(this, 24, 13);
      this.leftWing.func_228300_a_(-1.0F, 0.0F, -3.0F, 1, 4, 6);
      this.leftWing.setRotationPoint(4.0F, 13.0F, 0.0F);
   }

   /*
   @Override
   public void render(SpectralChickenEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      if (this.isChild) {
         GlStateManager.pushMatrix();
         GlStateManager.translatef(0.0F, 5.0F * scale, 2.0F * scale);
         this.head.render(scale);
         this.field_78137_g.render(scale);
         this.field_78143_h.render(scale);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.scalef(0.5F, 0.5F, 0.5F);
         GlStateManager.translatef(0.0F, 24.0F * scale, 0.0F);
         this.bill.render(scale);
         this.chin.render(scale);
         this.body.render(scale);
         this.rightWing.render(scale);
         this.leftWing.render(scale);
         GlStateManager.popMatrix();
      } else {
         this.head.render(scale);
         this.field_78137_g.render(scale);
         this.field_78143_h.render(scale);
         this.bill.render(scale);
         this.chin.render(scale);
         this.body.render(scale);
         this.rightWing.render(scale);
         this.leftWing.render(scale);
      }

   }
   */

   // setRotationAngles
   @Override
   public void func_225597_a_(SpectralChickenEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
      this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
      this.field_78137_g.rotateAngleX = this.head.rotateAngleX;
      this.field_78137_g.rotateAngleY = this.head.rotateAngleY;
      this.field_78143_h.rotateAngleX = this.head.rotateAngleX;
      this.field_78143_h.rotateAngleY = this.head.rotateAngleY;
      this.bill.rotateAngleX = ((float)Math.PI / 2F);
      this.chin.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
      this.body.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
      this.rightWing.rotateAngleZ = (float) (0.1f + Math.abs((MathHelper.cos(ageInTicks / 15) / 2)));
      this.leftWing.rotateAngleZ = (float) (-0.1f - Math.abs((MathHelper.cos(ageInTicks / 15) / 2)));
   }

   // Render ??
   @Override
   public void func_225598_a_(@Nonnull MatrixStack matrix, @Nonnull IVertexBuilder vertexBuilder, int light, int overlayLight, float red, float green, float blue, float alpha)
   {
	   float scale = 1.0f;
	   //this.func_225597_a_(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	   if (this.isChild) {
	      RenderSystem.pushMatrix();
	      RenderSystem.translatef(0.0F, 5.0F * scale, 2.0F * scale);
	      this.head.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.field_78137_g.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.field_78143_h.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      RenderSystem.popMatrix();
	      RenderSystem.pushMatrix();
	      RenderSystem.scalef(0.5F, 0.5F, 0.5F);
	      RenderSystem.translatef(0.0F, 24.0F * scale, 0.0F);
	      this.bill.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.chin.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.body.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.rightWing.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.leftWing.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      RenderSystem.popMatrix();
	   } else {
	      this.head.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.field_78137_g.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.field_78143_h.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.bill.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.chin.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.body.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.rightWing.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	      this.leftWing.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	   }		
	}
}
