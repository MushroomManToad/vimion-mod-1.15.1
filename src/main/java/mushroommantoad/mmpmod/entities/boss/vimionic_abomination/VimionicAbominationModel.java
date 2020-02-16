package mushroommantoad.mmpmod.entities.boss.vimionic_abomination;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VimionicAbominationModel extends EntityModel<VimionicAbominationEntity> {
	private final ModelRenderer core;
	private final ModelRenderer rightarm;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leftarm;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;
	private final ModelRenderer tendril2;
	private final ModelRenderer p2;
	private final ModelRenderer p3;
	private final ModelRenderer p8;
	private final ModelRenderer p9;
	private final ModelRenderer tendril1;
	private final ModelRenderer p7;
	private final ModelRenderer p4;
	private final ModelRenderer p5;
	private final ModelRenderer p6;
	private float partialTicks;

	public VimionicAbominationModel() {
		textureWidth = 256;
		textureHeight = 256;

		core = new ModelRenderer(this);
		core.setRotationPoint(0.0F, 24.0F, 0.0F);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-6.0F, -36.0F, 0.0F);
		setRotationAngle(rightarm, 0.0F, 0.0F, 1.309F);
		core.addChild(rightarm);
		rightarm.setTextureOffset(204, 0);
		rightarm.addBox(-1.8706F, -2.483F, -2.0F, 4, 12, 4, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		core.addChild(body);
		body.setTextureOffset(232, 34);
		body.addBox(-4.0F, -38.0F, -2.0F, 8, 12, 4, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -38.0F, 0.0F);
		core.addChild(head);
		head.setTextureOffset(224, 18);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		head.setTextureOffset(220, 0);
		head.addBox(-4.5F, -8.5F, -4.5F, 9, 9, 9, 0.0F);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(6.0F, -36.0F, 0.0F);
		setRotationAngle(leftarm, 0.0F, 0.0F, -1.309F);
		core.addChild(leftarm);
		leftarm.setTextureOffset(204, 16);
		leftarm.addBox(-2.2868F, -2.4096F, -2.0F, 4, 12, 4, 0.0F);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(2.0F, -26.0F, 0.0F);
		setRotationAngle(leftleg, 0.0F, 0.0F, -0.1745F);
		core.addChild(leftleg);
		leftleg.setTextureOffset(204, 32);
		leftleg.addBox(-1.9653F, -0.197F, -2.0F, 4, 12, 4, 0.0F);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-2.0F, -26.0F, 0.0F);
		setRotationAngle(rightleg, 0.0F, 0.0F, 0.1745F);
		core.addChild(rightleg);
		rightleg.setTextureOffset(188, 32);
		rightleg.addBox(-2.0347F, -0.197F, -2.0F, 4, 12, 4, 0.0F);

		tendril2 = new ModelRenderer(this);
		tendril2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(tendril2, 0.0F, 3.1416F, 0.0F);
		tendril2.setTextureOffset(50, 24);
		tendril2.addBox(-35.0F, -5.0F, 25.0F, 10, 5, 10, 0.0F);
		tendril2.setTextureOffset(52, 0);
		tendril2.addBox(-33.0F, -12.0F, 24.0F, 9, 7, 9, 0.0F);
		tendril2.setTextureOffset(39, 85);
		tendril2.addBox(-31.0F, -21.0F, 23.0F, 8, 9, 8, 0.0F);

		p2 = new ModelRenderer(this);
		p2.setRotationPoint(-27.0F, -16.5F, 27.0F);
		setRotationAngle(p2, 1.1345F, 0.6981F, 1.1345F);
		tendril2.addChild(p2);
		p2.setTextureOffset(74, 54);
		p2.addBox(-13.5797F, -53.1731F, 5.8829F, 2, 17, 2, 0.0F);

		p3 = new ModelRenderer(this);
		p3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(p3, 0.0873F, 0.0873F, 0.0873F);
		tendril2.addChild(p3);
		p3.setTextureOffset(0, 76);
		p3.addBox(-33.6147F, -27.6315F, 21.2125F, 7, 11, 7, 0.0F);

		p8 = new ModelRenderer(this);
		p8.setRotationPoint(-24.0F, -38.5F, 24.0F);
		setRotationAngle(p8, 0.4363F, 0.4363F, 0.4363F);
		tendril2.addChild(p8);
		p8.setTextureOffset(85, 34);
		p8.addBox(-0.1843F, -4.7966F, -4.7805F, 6, 13, 6, 0.0F);

		p9 = new ModelRenderer(this);
		p9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(p9, 0.7854F, 0.7854F, 0.7854F);
		tendril2.addChild(p9);
		p9.setTextureOffset(85, 8);
		p9.addBox(-46.6955F, -36.3189F, -4.7583F, 4, 15, 4, 0.0F);

		tendril1 = new ModelRenderer(this);
		tendril1.setRotationPoint(0.0F, 24.0F, 0.0F);
		tendril1.setTextureOffset(0, 0);
		tendril1.addBox(-35.0F, -5.0F, 25.0F, 10, 5, 10, 0.0F);
		tendril1.setTextureOffset(27, 16);
		tendril1.addBox(-33.0F, -12.0F, 24.0F, 9, 7, 9, 0.0F);
		tendril1.setTextureOffset(44, 49);
		tendril1.addBox(-31.0F, -21.0F, 23.0F, 8, 9, 8, 0.0F);

		p7 = new ModelRenderer(this);
		p7.setRotationPoint(-27.0F, -16.5F, 27.0F);
		setRotationAngle(p7, 1.1345F, 0.6981F, 1.1345F);
		tendril1.addChild(p7);
		p7.setTextureOffset(49, 28);
		p7.addBox(-13.5797F, -53.1731F, 5.8829F, 2, 17, 2, 0.0F);

		p4 = new ModelRenderer(this);
		p4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(p4, 0.0873F, 0.0873F, 0.0873F);
		tendril1.addChild(p4);
		p4.setTextureOffset(48, 33);
		p4.addBox(-33.6147F, -27.6315F, 21.2125F, 7, 11, 7, 0.0F);

		p5 = new ModelRenderer(this);
		p5.setRotationPoint(-24.0F, -38.5F, 24.0F);
		setRotationAngle(p5, 0.4363F, 0.4363F, 0.4363F);
		tendril1.addChild(p5);
		p5.setTextureOffset(86, 73);
		p5.addBox(-0.1843F, -4.7966F, -4.7805F, 6, 13, 6, 0.0F);

		p6 = new ModelRenderer(this);
		p6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(p6, 0.7854F, 0.7854F, 0.7854F);
		tendril1.addChild(p6);
		p6.setTextureOffset(27, 83);
		p6.addBox(-46.6955F, -36.3189F, -4.7583F, 4, 15, 4, 0.0F);
	}
	
	@Override
	public void setLivingAnimations(VimionicAbominationEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) 
	{
		this.partialTicks = partialTick;
	}

	@Override
	public void render(@Nonnull MatrixStack matrix, @Nonnull IVertexBuilder vertexBuilder, int light, int overlayLight, float red, float green, float blue, float alpha)
	{
		core.render(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
		tendril2.render(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
		tendril1.render(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	}
	
	public void setRotationAngle(ModelRenderer rM, float x, float y, float z) 
	{
		rM.rotateAngleX = x;
		rM.rotateAngleY = y;
		rM.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(VimionicAbominationEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float yaw, float pitch)
	{
		float entityRotation0 = (float) (this.tendril2.rotateAngleY - this.tendril2.rotateAngleY + Math.toRadians(yaw) - Math.toRadians(entityIn.getYaw(this.partialTicks)));
		this.head.rotateAngleY = (float) (yaw * ((float)Math.PI / 180) / 1.2);
		this.head.rotateAngleX = (float) (pitch * ((float)Math.PI / 180) / 1.2);
		
		this.rightarm.rotateAngleZ = (float) (1f + (Math.cos((ageInTicks) / 15 )) / 4);
		
		this.leftarm.rotateAngleZ = (float) (-1f - (MathHelper.cos((float) ((ageInTicks + 50) / 15.1 )) / 4));
		
		this.rightleg.rotateAngleZ = (float) (0.15f + (MathHelper.cos((float) (ageInTicks / 15)) / 15 ));
		
		this.leftleg.rotateAngleZ = (float) (-0.15f + (MathHelper.cos((float) ((ageInTicks + 50) / 15.1 )) / 15));
		
		this.core.setRotationPoint(0.0F, (float) (24.0F + 2 * Math.cos(ageInTicks / 30)), 0.0F);
		
		this.tendril1.rotateAngleY = (float) (((ageInTicks) / 15) + entityRotation0);
	    this.tendril2.rotateAngleY = (float) (((ageInTicks) / 15) + Math.PI + entityRotation0);
	}
}