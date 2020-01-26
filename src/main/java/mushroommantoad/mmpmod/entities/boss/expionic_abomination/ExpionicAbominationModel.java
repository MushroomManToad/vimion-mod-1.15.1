package mushroommantoad.mmpmod.entities.boss.expionic_abomination;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ExpionicAbominationModel<T extends Entity> extends EntityModel<T> {
	private final ModelRenderer Head;
	private final ModelRenderer Spike;
	private final ModelRenderer Torso;
	private final ModelRenderer LeftWing;
	private final ModelRenderer LeftCoreBone;
	private final ModelRenderer LeftLeftBone;
	private final ModelRenderer LeftRightBone;
	private final ModelRenderer RightWing;
	private final ModelRenderer RightCoreBone;
	private final ModelRenderer RightLeftBone;
	private final ModelRenderer RightRightBone;
	private final ModelRenderer Tail;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	
	public boolean isMoving = false;
	public boolean isTPChannelling = false;
	
	@SuppressWarnings("unused")
	private float partialTicks;

	public ExpionicAbominationModel() 
	{
		textureWidth = 256;
		textureHeight = 256;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -6.0F, 0.0F);
		Head.setTextureOffset(232, 6);
		Head.func_228301_a_(-3.0F, -3.0F, -3.0F, 6, 3, 6, 0.0F);

		Spike = new ModelRenderer(this);
		Spike.setRotationPoint(0.0F, -2.0F, 0.0F);
		setRotationAngle(Spike, 0.7854F, 0.0F, 0.0F);
		Head.addChild(Spike);
		Spike.setTextureOffset(244, 0);
		Spike.func_228301_a_(-0.5F, -0.5F, 0.0F, 1, 1, 5, 0.0F);

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, 2.0F, 0.0F);
		Torso.setTextureOffset(232, 15);
		Torso.func_228301_a_(-4.0F, -8.0F, -2.0F, 8, 16, 4, 0.0F);

		LeftWing = new ModelRenderer(this);
		LeftWing.setRotationPoint(4.0F, -4.0F, 1.5F);
		setRotationAngle(LeftWing, 0.0F, -0.6109F, -0.2618F);
		LeftWing.setTextureOffset(216, 25);
		LeftWing.func_228301_a_(-1.0F, -1.0F, -0.5F, 7, 1, 1, 0.0F);
		LeftWing.setTextureOffset(214, 0);
		LeftWing.func_228301_a_(2.0F, -4.75F, 0.0F, 9, 25, 0, 0.0F);

		LeftCoreBone = new ModelRenderer(this);
		LeftCoreBone.setRotationPoint(6.5F, -0.5F, 0.0F);
		LeftWing.addChild(LeftCoreBone);
		LeftCoreBone.setTextureOffset(210, 0);
		LeftCoreBone.func_228301_a_(-0.5F, -6.5F, -0.5F, 1, 16, 1, 0.0F);

		LeftLeftBone = new ModelRenderer(this);
		LeftLeftBone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(LeftLeftBone, 0.0F, 0.0F, -0.7854F);
		LeftWing.addChild(LeftLeftBone);
		LeftLeftBone.setTextureOffset(228, 27);
		LeftLeftBone.func_228301_a_(4.5F, -2.0F, -0.475F, 1, 6, 1, 0.0F);

		LeftRightBone = new ModelRenderer(this);
		LeftRightBone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(LeftRightBone, 0.0F, 0.0F, 0.7854F);
		LeftWing.addChild(LeftRightBone);
		LeftRightBone.setTextureOffset(224, 27);
		LeftRightBone.func_228301_a_(3.5F, -11.0F, -0.475F, 1, 6, 1, 0.0F);

		RightWing = new ModelRenderer(this);
		RightWing.setRotationPoint(-4.0F, -4.0F, 1.5F);
		setRotationAngle(RightWing, 0.0F, 0.6109F, 0.2618F);
		RightWing.setTextureOffset(240, 60);
		RightWing.func_228301_a_(-6.0F, -1.0F, -0.5F, 7, 1, 1, 0.0F);
		RightWing.setTextureOffset(238, 35);
		RightWing.func_228301_a_(-11.0F, -4.75F, 0.0F, 9, 25, 0, 0.0F);

		RightCoreBone = new ModelRenderer(this);
		RightCoreBone.setRotationPoint(-6.5F, -0.5F, 0.0F);
		RightWing.addChild(RightCoreBone);
		RightCoreBone.setTextureOffset(234, 35);
		RightCoreBone.func_228301_a_(-0.5F, -6.5F, -0.5F, 1, 16, 1, 0.0F);

		RightLeftBone = new ModelRenderer(this);
		RightLeftBone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(RightLeftBone, 0.0F, 0.0F, 0.7854F);
		RightWing.addChild(RightLeftBone);
		RightLeftBone.setTextureOffset(230, 35);
		RightLeftBone.func_228301_a_(-5.5F, -2.0F, -0.475F, 1, 6, 1, 0.0F);

		RightRightBone = new ModelRenderer(this);
		RightRightBone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(RightRightBone, 0.0F, 0.0F, -0.7854F);
		RightWing.addChild(RightRightBone);
		RightRightBone.setTextureOffset(226, 35);
		RightRightBone.func_228301_a_(-4.5F, -11.0F, -0.475F, 1, 6, 1, 0.0F);
		
		Tail = new ModelRenderer(this);
		Tail.setRotationPoint(0.0F, 9.0F, 2.0F);
		Tail.setTextureOffset(0, 1);
		Tail.func_228301_a_(-1.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 2.0F, 2.0F);
		Tail.addChild(bone);
		bone.setTextureOffset(0, 5);
		bone.func_228301_a_(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 1.0F, 2.0F);
		bone.addChild(bone2);
		bone2.setTextureOffset(0, 9);
		bone2.func_228301_a_(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 1.0F, 2.0F);
		bone2.addChild(bone3);
		bone3.setTextureOffset(0, 13);
		bone3.func_228301_a_(-1.0F, -3.0F, 0.0F, 2, 2, 2, 0.0F);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -2.0F, 2.0F);
		bone3.addChild(bone4);
		bone4.setTextureOffset(0, 17);
		bone4.func_228301_a_(-0.5F, -1.5F, 0.0F, 1, 1, 1, 0.0F);
	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) 
	{
		this.partialTicks = partialTick;
	}

	@Override
	public void func_225598_a_(@Nonnull MatrixStack matrix, @Nonnull IVertexBuilder vertexBuilder, int light, int overlayLight, float red, float green, float blue, float alpha) 
	{
		Head.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
		Torso.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
		LeftWing.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
		RightWing.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
		Tail.func_228309_a_(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	}
	
	@Override
	public void func_225597_a_(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float yaw, float pitch)
	{
		LeftWing.rotateAngleY = (float) ((Math.cos((ageInTicks) / 15 )) / 2.0);
		RightWing.rotateAngleY = (float) ((-Math.cos((ageInTicks) / 15 )) / 2.0);
		Spike.rotateAngleX = (float) ((Math.PI / 5) + (Math.cos((ageInTicks) / 35 )) / 8.0);
		Tail.rotateAngleX = (float) ((Math.cos((ageInTicks) / 17 )) / 8.0);
		bone.rotateAngleX = (float) ((Math.cos((ageInTicks) / 16 )) / 7.0);
		bone2.rotateAngleX = (float) ((Math.cos((ageInTicks) / 15 )) / 6.0);
		bone3.rotateAngleX = (float) ((Math.cos((ageInTicks) / 14 )) / 5.0);
		bone4.rotateAngleX = (float) ((Math.cos((ageInTicks) / 13 )) / 4.0);
		
		this.Head.rotateAngleY = (float) (yaw * ((float)Math.PI / 180) / 1.2);
		this.Head.rotateAngleX = (float) (pitch * ((float)Math.PI / 180) / 1.2);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}