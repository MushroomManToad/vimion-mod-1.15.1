package mushroommantoad.mmpmod.entities.boss.expionic_abomination;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ExpionicAbominationModel<T extends Entity> extends EntityModel<T> {
	private final RendererModel Head;
	private final RendererModel Spike;
	private final RendererModel Torso;
	private final RendererModel LeftWing;
	private final RendererModel LeftCoreBone;
	private final RendererModel LeftLeftBone;
	private final RendererModel LeftRightBone;
	private final RendererModel RightWing;
	private final RendererModel RightCoreBone;
	private final RendererModel RightLeftBone;
	private final RendererModel RightRightBone;
	private final RendererModel Tail;
	private final RendererModel bone;
	private final RendererModel bone2;
	private final RendererModel bone3;
	private final RendererModel bone4;
	
	public boolean isMoving = false;
	public boolean isTPChannelling = false;
	
	@SuppressWarnings("unused")
	private float partialTicks;

	public ExpionicAbominationModel() 
	{
		textureWidth = 256;
		textureHeight = 256;

		Head = new RendererModel(this);
		Head.setRotationPoint(0.0F, -6.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 232, 6, -3.0F, -3.0F, -3.0F, 6, 3, 6, 0.0F, false));

		Spike = new RendererModel(this);
		Spike.setRotationPoint(0.0F, -2.0F, 0.0F);
		setRotationAngle(Spike, 0.7854F, 0.0F, 0.0F);
		Head.addChild(Spike);
		Spike.cubeList.add(new ModelBox(Spike, 244, 0, -0.5F, -0.5F, 0.0F, 1, 1, 5, 0.0F, false));

		Torso = new RendererModel(this);
		Torso.setRotationPoint(0.0F, 2.0F, 0.0F);
		Torso.cubeList.add(new ModelBox(Torso, 232, 15, -4.0F, -8.0F, -2.0F, 8, 16, 4, 0.0F, false));

		LeftWing = new RendererModel(this);
		LeftWing.setRotationPoint(4.0F, -4.0F, 1.5F);
		setRotationAngle(LeftWing, 0.0F, -0.6109F, -0.2618F);
		LeftWing.cubeList.add(new ModelBox(LeftWing, 216, 25, -1.0F, -1.0F, -0.5F, 7, 1, 1, 0.0F, false));
		LeftWing.cubeList.add(new ModelBox(LeftWing, 214, 0, 2.0F, -4.75F, 0.0F, 9, 25, 0, 0.0F, false));

		LeftCoreBone = new RendererModel(this);
		LeftCoreBone.setRotationPoint(6.5F, -0.5F, 0.0F);
		LeftWing.addChild(LeftCoreBone);
		LeftCoreBone.cubeList.add(new ModelBox(LeftCoreBone, 210, 0, -0.5F, -6.5F, -0.5F, 1, 16, 1, 0.0F, false));

		LeftLeftBone = new RendererModel(this);
		LeftLeftBone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(LeftLeftBone, 0.0F, 0.0F, -0.7854F);
		LeftWing.addChild(LeftLeftBone);
		LeftLeftBone.cubeList.add(new ModelBox(LeftLeftBone, 228, 27, 4.5F, -2.0F, -0.475F, 1, 6, 1, 0.0F, false));

		LeftRightBone = new RendererModel(this);
		LeftRightBone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(LeftRightBone, 0.0F, 0.0F, 0.7854F);
		LeftWing.addChild(LeftRightBone);
		LeftRightBone.cubeList.add(new ModelBox(LeftRightBone, 224, 27, 3.5F, -11.0F, -0.475F, 1, 6, 1, 0.0F, false));

		RightWing = new RendererModel(this);
		RightWing.setRotationPoint(-4.0F, -4.0F, 1.5F);
		setRotationAngle(RightWing, 0.0F, 0.6109F, 0.2618F);
		RightWing.cubeList.add(new ModelBox(RightWing, 240, 60, -6.0F, -1.0F, -0.5F, 7, 1, 1, 0.0F, true));
		RightWing.cubeList.add(new ModelBox(RightWing, 238, 35, -11.0F, -4.75F, 0.0F, 9, 25, 0, 0.0F, false));

		RightCoreBone = new RendererModel(this);
		RightCoreBone.setRotationPoint(-6.5F, -0.5F, 0.0F);
		RightWing.addChild(RightCoreBone);
		RightCoreBone.cubeList.add(new ModelBox(RightCoreBone, 234, 35, -0.5F, -6.5F, -0.5F, 1, 16, 1, 0.0F, false));

		RightLeftBone = new RendererModel(this);
		RightLeftBone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(RightLeftBone, 0.0F, 0.0F, 0.7854F);
		RightWing.addChild(RightLeftBone);
		RightLeftBone.cubeList.add(new ModelBox(RightLeftBone, 230, 35, -5.5F, -2.0F, -0.475F, 1, 6, 1, 0.0F, false));

		RightRightBone = new RendererModel(this);
		RightRightBone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(RightRightBone, 0.0F, 0.0F, -0.7854F);
		RightWing.addChild(RightRightBone);
		RightRightBone.cubeList.add(new ModelBox(RightRightBone, 226, 35, -4.5F, -11.0F, -0.475F, 1, 6, 1, 0.0F, false));
		
		Tail = new RendererModel(this);
		Tail.setRotationPoint(0.0F, 9.0F, 2.0F);
		Tail.cubeList.add(new ModelBox(Tail, 0, 1, -1.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F, false));

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 2.0F, 2.0F);
		Tail.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 0, 5, -1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F, false));

		bone2 = new RendererModel(this);
		bone2.setRotationPoint(0.0F, 1.0F, 2.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 9, -1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F, false));

		bone3 = new RendererModel(this);
		bone3.setRotationPoint(0.0F, 1.0F, 2.0F);
		bone2.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 0, 13, -1.0F, -3.0F, 0.0F, 2, 2, 2, 0.0F, false));

		bone4 = new RendererModel(this);
		bone4.setRotationPoint(0.0F, -2.0F, 2.0F);
		bone3.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 17, -0.5F, -1.5F, 0.0F, 1, 1, 1, 0.0F, false));
	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) 
	{
		this.partialTicks = partialTick;
	}

	@Override
	public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) 
	{
		this.setCustomRotations(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		Head.render(scale);
		Torso.render(scale);
		LeftWing.render(scale);
		RightWing.render(scale);
		Tail.render(scale);
	}
	
	public void setCustomRotations(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float yaw, float pitch, float scaleFactor)
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
	
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}