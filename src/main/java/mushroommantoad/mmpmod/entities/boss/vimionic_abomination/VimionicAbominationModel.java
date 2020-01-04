package mushroommantoad.mmpmod.entities.boss.vimionic_abomination;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VimionicAbominationModel extends EntityModel<VimionicAbominationEntity> {
	private final RendererModel core;
	private final RendererModel rightarm;
	private final RendererModel body;
	private final RendererModel head;
	private final RendererModel leftarm;
	private final RendererModel leftleg;
	private final RendererModel rightleg;
	private final RendererModel tendril2;
	private final RendererModel p2;
	private final RendererModel p3;
	private final RendererModel p8;
	private final RendererModel p9;
	private final RendererModel tendril1;
	private final RendererModel p7;
	private final RendererModel p4;
	private final RendererModel p5;
	private final RendererModel p6;
	private float partialTicks;

	public VimionicAbominationModel() {
		textureWidth = 256;
		textureHeight = 256;

		core = new RendererModel(this);
		core.setRotationPoint(0.0F, 24.0F, 0.0F);

		rightarm = new RendererModel(this);
		rightarm.setRotationPoint(-6.0F, -36.0F, 0.0F);
		setRotationAngle(rightarm, 0.0F, 0.0F, 1.309F);
		core.addChild(rightarm);
		rightarm.cubeList.add(new ModelBox(rightarm, 204, 0, -1.8706F, -2.483F, -2.0F, 4, 12, 4, 0.0F, false));

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		core.addChild(body);
		body.cubeList.add(new ModelBox(body, 232, 34, -4.0F, -38.0F, -2.0F, 8, 12, 4, 0.0F, false));

		head = new RendererModel(this);
		head.setRotationPoint(0.0F, -38.0F, 0.0F);
		core.addChild(head);
		head.cubeList.add(new ModelBox(head, 224, 18, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 220, 0, -4.5F, -8.5F, -4.5F, 9, 9, 9, 0.0F, false));

		leftarm = new RendererModel(this);
		leftarm.setRotationPoint(6.0F, -36.0F, 0.0F);
		setRotationAngle(leftarm, 0.0F, 0.0F, -1.309F);
		core.addChild(leftarm);
		leftarm.cubeList.add(new ModelBox(leftarm, 204, 16, -2.2868F, -2.4096F, -2.0F, 4, 12, 4, 0.0F, true));

		leftleg = new RendererModel(this);
		leftleg.setRotationPoint(2.0F, -26.0F, 0.0F);
		setRotationAngle(leftleg, 0.0F, 0.0F, -0.1745F);
		core.addChild(leftleg);
		leftleg.cubeList.add(new ModelBox(leftleg, 204, 32, -1.9653F, -0.197F, -2.0F, 4, 12, 4, 0.0F, true));

		rightleg = new RendererModel(this);
		rightleg.setRotationPoint(-2.0F, -26.0F, 0.0F);
		setRotationAngle(rightleg, 0.0F, 0.0F, 0.1745F);
		core.addChild(rightleg);
		rightleg.cubeList.add(new ModelBox(rightleg, 188, 32, -2.0347F, -0.197F, -2.0F, 4, 12, 4, 0.0F, false));

		tendril2 = new RendererModel(this);
		tendril2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(tendril2, 0.0F, 3.1416F, 0.0F);
		tendril2.cubeList.add(new ModelBox(tendril2, 50, 24, -35.0F, -5.0F, 25.0F, 10, 5, 10, 0.0F, false));
		tendril2.cubeList.add(new ModelBox(tendril2, 52, 0, -33.0F, -12.0F, 24.0F, 9, 7, 9, 0.0F, false));
		tendril2.cubeList.add(new ModelBox(tendril2, 39, 85, -31.0F, -21.0F, 23.0F, 8, 9, 8, 0.0F, false));

		p2 = new RendererModel(this);
		p2.setRotationPoint(-27.0F, -16.5F, 27.0F);
		setRotationAngle(p2, 1.1345F, 0.6981F, 1.1345F);
		tendril2.addChild(p2);
		p2.cubeList.add(new ModelBox(p2, 74, 54, -13.5797F, -53.1731F, 5.8829F, 2, 17, 2, 0.0F, false));

		p3 = new RendererModel(this);
		p3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(p3, 0.0873F, 0.0873F, 0.0873F);
		tendril2.addChild(p3);
		p3.cubeList.add(new ModelBox(p3, 0, 76, -33.6147F, -27.6315F, 21.2125F, 7, 11, 7, 0.0F, false));

		p8 = new RendererModel(this);
		p8.setRotationPoint(-24.0F, -38.5F, 24.0F);
		setRotationAngle(p8, 0.4363F, 0.4363F, 0.4363F);
		tendril2.addChild(p8);
		p8.cubeList.add(new ModelBox(p8, 85, 34, -0.1843F, -4.7966F, -4.7805F, 6, 13, 6, 0.0F, false));

		p9 = new RendererModel(this);
		p9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(p9, 0.7854F, 0.7854F, 0.7854F);
		tendril2.addChild(p9);
		p9.cubeList.add(new ModelBox(p9, 85, 8, -46.6955F, -36.3189F, -4.7583F, 4, 15, 4, 0.0F, false));

		tendril1 = new RendererModel(this);
		tendril1.setRotationPoint(0.0F, 24.0F, 0.0F);
		tendril1.cubeList.add(new ModelBox(tendril1, 0, 0, -35.0F, -5.0F, 25.0F, 10, 5, 10, 0.0F, false));
		tendril1.cubeList.add(new ModelBox(tendril1, 27, 16, -33.0F, -12.0F, 24.0F, 9, 7, 9, 0.0F, false));
		tendril1.cubeList.add(new ModelBox(tendril1, 44, 49, -31.0F, -21.0F, 23.0F, 8, 9, 8, 0.0F, false));

		p7 = new RendererModel(this);
		p7.setRotationPoint(-27.0F, -16.5F, 27.0F);
		setRotationAngle(p7, 1.1345F, 0.6981F, 1.1345F);
		tendril1.addChild(p7);
		p7.cubeList.add(new ModelBox(p7, 49, 28, -13.5797F, -53.1731F, 5.8829F, 2, 17, 2, 0.0F, false));

		p4 = new RendererModel(this);
		p4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(p4, 0.0873F, 0.0873F, 0.0873F);
		tendril1.addChild(p4);
		p4.cubeList.add(new ModelBox(p4, 48, 33, -33.6147F, -27.6315F, 21.2125F, 7, 11, 7, 0.0F, false));

		p5 = new RendererModel(this);
		p5.setRotationPoint(-24.0F, -38.5F, 24.0F);
		setRotationAngle(p5, 0.4363F, 0.4363F, 0.4363F);
		tendril1.addChild(p5);
		p5.cubeList.add(new ModelBox(p5, 86, 73, -0.1843F, -4.7966F, -4.7805F, 6, 13, 6, 0.0F, false));

		p6 = new RendererModel(this);
		p6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(p6, 0.7854F, 0.7854F, 0.7854F);
		tendril1.addChild(p6);
		p6.cubeList.add(new ModelBox(p6, 27, 83, -46.6955F, -36.3189F, -4.7583F, 4, 15, 4, 0.0F, false));
	}
	
	@Override
	public void setLivingAnimations(VimionicAbominationEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) 
	{
		this.partialTicks = partialTick;
	}

	@Override
	public void render(VimionicAbominationEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setCustomRotations(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		core.render(scale);
		tendril2.render(scale);
		tendril1.render(scale);
	}
	
	public void setRotationAngle(RendererModel rM, float x, float y, float z) 
	{
		rM.rotateAngleX = x;
		rM.rotateAngleY = y;
		rM.rotateAngleZ = z;
	}
	
	public void setCustomRotations(VimionicAbominationEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float yaw, float pitch, float scaleFactor)
	{
		float entityRotation0 = (float) (this.tendril2.rotateAngleY - this.tendril2.rotateAngleY + Math.toRadians(yaw) - Math.toRadians(entityIn.getYaw(this.partialTicks)));
		this.head.rotateAngleY = (float) (yaw * ((float)Math.PI / 180) / 1.2);
		this.head.rotateAngleX = (float) (pitch * ((float)Math.PI / 180) / 1.2);
		
		this.rightarm.rotateAngleZ = (float) (1f + (Math.cos((ageInTicks) / 15 )) / 4);
		
		this.leftarm.rotateAngleZ = (float) (-1f - (MathHelper.cos((float) ((ageInTicks + 50) / 15.1 )) / 4));
		
		this.rightleg.rotateAngleZ = (float) (0.15f + (MathHelper.cos((float) (ageInTicks / 15)) / 15 ));
		
		this.leftleg.rotateAngleZ = (float) (-0.15f + (MathHelper.cos((float) ((ageInTicks + 50) / 15.1 )) / 15));
		
		this.tendril1.rotateAngleY = (float) (((ageInTicks) / 15) + entityRotation0);
	    this.tendril2.rotateAngleY = (float) (((ageInTicks) / 15) + Math.PI + entityRotation0);
	}
}