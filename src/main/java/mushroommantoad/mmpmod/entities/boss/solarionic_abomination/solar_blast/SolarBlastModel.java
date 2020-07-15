package mushroommantoad.mmpmod.entities.boss.solarionic_abomination.solar_blast;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// Made with Blockbench 3.5.3
// Thanks Blockbench :)

@OnlyIn(Dist.CLIENT)
public class SolarBlastModel extends EntityModel<SolarBlastEntity> {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public SolarBlastModel() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.setTextureOffset(0, 68).addBox(0.0F, -16.0F, -9.0F, 0.0F, 40.0F, 20.0F, 0.0F, false);
		bone.setTextureOffset(84, 86).addBox(-11.0F, -16.0F, 0.0F, 22.0F, 42.0F, 0.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.2618F, 0.7854F, 0.7854F);
		bone2.setTextureOffset(0, 0).addBox(3.1106F, 12.7307F, 0.5834F, 16.0F, 16.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(SolarBlastEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.bone.rotateAngleY = 0;
		this.bone.rotateAngleX = (float) Math.PI;
		this.bone.rotateAngleZ = 0;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}	
	
	
	/*	 
	private final ModelRenderer bone;

	public SolarBlastModel() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.setTextureOffset(0, 0).addBox(-16.0F, 24.0F, -16.0F, 32.0F, 0.0F, 32.0F, 0.0F, false);
		bone.setTextureOffset(0, 76).addBox(8.0F, -12.0F, -8.0F, 0.0F, 36.0F, 16.0F, 0.0F, false);
		bone.setTextureOffset(32, 92).addBox(-8.0F, -12.0F, -8.0F, 16.0F, 36.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(64, 76).addBox(-8.0F, -12.0F, -8.0F, 0.0F, 36.0F, 16.0F, 0.0F, false);
		bone.setTextureOffset(96, 92).addBox(-8.0F, -12.0F, 8.0F, 16.0F, 36.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(SolarBlastEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.bone.rotateAngleY = 0;
		this.bone.rotateAngleX = (float) Math.PI;
		this.bone.rotateAngleZ = 0;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	*/
}