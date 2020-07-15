package mushroommantoad.mmpmod.entities.boss.solarionic_abomination;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SolarionicAbominationModel<T extends Entity> extends EntityModel<T>
{
	private final ModelRenderer Head;
	private final ModelRenderer RingX;
	private final ModelRenderer ForwardX;
	private final ModelRenderer BackwardX;
	private final ModelRenderer RingY;
	private final ModelRenderer ForwardY;
	private final ModelRenderer BackwardY;
	private final ModelRenderer RingZ;
	private final ModelRenderer ForwardZ;
	private final ModelRenderer BackwardZ;
	private float partialTicks;

	public SolarionicAbominationModel() {
		textureWidth = 256;
		textureHeight = 256;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -8.0F, 0.0F);
		Head.setTextureOffset(224, 0);
		Head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);

		RingX = new ModelRenderer(this);
		RingX.setRotationPoint(0.0F, -8.0F, 0.0F);
		RingX.setTextureOffset(128, 56);
		RingX.addBox(-10.0F, -20.0F, -2.0F, 20, 4, 4, 0.0F);
		RingX.setTextureOffset(128, 48);
		RingX.addBox(-10.0F, 16.0F, -2.0F, 20, 4, 4, 0.0F);
		RingX.setTextureOffset(172, 8);
		RingX.addBox(16.0F, -10.0F, -2.0F, 4, 20, 4, 0.0F);
		RingX.setTextureOffset(156, 8);
		RingX.addBox(-20.0F, -10.0F, -2.0F, 4, 20, 4, 0.0F);

		ForwardX = new ModelRenderer(this);
		ForwardX.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(ForwardX, 0.0F, 0.0F, 0.7854F);
		RingX.addChild(ForwardX);
		ForwardX.setTextureOffset(140, 40);
		ForwardX.addBox(-7.0F, 17.25F, -2.01F, 14, 4, 4, 0.0F);
		ForwardX.setTextureOffset(188, 0);
		ForwardX.addBox(-7.0F, -21.25F, -2.01F, 14, 4, 4, 0.0F);

		BackwardX = new ModelRenderer(this);
		BackwardX.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(BackwardX, 0.0F, 0.0F, -0.7854F);
		RingX.addChild(BackwardX);
		BackwardX.setTextureOffset(140, 32);
		BackwardX.addBox(-7.0F, 17.25F, -2.01F, 14, 4, 4, 0.0F);
		BackwardX.setTextureOffset(188, 8);
		BackwardX.addBox(-7.0F, -21.25F, -2.01F, 14, 4, 4, 0.0F);

		RingY = new ModelRenderer(this);
		RingY.setRotationPoint(0.0F, -8.0F, 0.0F);
		setRotationAngle(RingY, 0.0F, -1.5708F, 0.0F);
		RingY.setTextureOffset(168, 64);
		RingY.addBox(-12.0F, -24.0F, -2.0F, 24, 4, 4, 0.0F);
		RingY.setTextureOffset(168, 72);
		RingY.addBox(-12.0F, 20.0F, -2.0F, 24, 4, 4, 0.0F);
		RingY.setTextureOffset(224, 64);
		RingY.addBox(20.0F, -12.0F, -2.0F, 4, 24, 4, 0.0F);
		RingY.setTextureOffset(240, 64);
		RingY.addBox(-24.0F, -12.0F, -2.0F, 4, 24, 4, 0.0F);

		ForwardY = new ModelRenderer(this);
		ForwardY.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(ForwardY, 0.0F, 0.0F, 0.7854F);
		RingY.addChild(ForwardY);
		ForwardY.setTextureOffset(182, 96);
		ForwardY.addBox(-8.5F, 21.5F, -2.01F, 17, 4, 4, 0.0F);
		ForwardY.setTextureOffset(182, 80);
		ForwardY.addBox(-8.5F, -25.5F, -2.01F, 17, 4, 4, 0.0F);

		BackwardY = new ModelRenderer(this);
		BackwardY.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(BackwardY, 0.0F, 0.0F, -0.7854F);
		RingY.addChild(BackwardY);
		BackwardY.setTextureOffset(182, 104);
		BackwardY.addBox(-8.5F, 21.5F, -2.01F, 17, 4, 4, 0.0F);
		BackwardY.setTextureOffset(182, 88);
		BackwardY.addBox(-8.5F, -25.5F, -2.01F, 17, 4, 4, 0.0F);

		RingZ = new ModelRenderer(this);
		RingZ.setRotationPoint(0.0F, -8.0F, 0.0F);
		RingZ.setTextureOffset(192, 192);
		RingZ.addBox(-2.0F, -28.0F, -14.0F, 4, 4, 28, 0.0F);
		RingZ.setTextureOffset(192, 224);
		RingZ.addBox(-2.0F, 24.0F, -14.0F, 4, 4, 28, 0.0F);
		RingZ.setTextureOffset(176, 192);
		RingZ.addBox(-2.0F, -14.0F, -28.0F, 4, 28, 4, 0.0F);
		RingZ.setTextureOffset(176, 224);
		RingZ.addBox(-2.0F, -14.0F, 24.0F, 4, 28, 4, 0.0F);

		ForwardZ = new ModelRenderer(this);
		ForwardZ.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(ForwardZ, 0.7854F, 0.0F, 0.0F);
		RingZ.addChild(ForwardZ);
		ForwardZ.setTextureOffset(80, 208);
		ForwardZ.addBox(-1.99F, 25.75F, -10.0F, 4, 4, 20, 0.0F);
		ForwardZ.setTextureOffset(128, 208);
		ForwardZ.addBox(-1.99F, -29.75F, -10.0F, 4, 4, 20, 0.0F);

		BackwardZ = new ModelRenderer(this);
		BackwardZ.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(BackwardZ, -0.7854F, 0.0F, 0.0F);
		RingZ.addChild(BackwardZ);
		BackwardZ.setTextureOffset(80, 232);
		BackwardZ.addBox(-1.99F, 25.75F, -10.0F, 4, 4, 20, 0.0F);
		BackwardZ.setTextureOffset(128, 232);
		BackwardZ.addBox(-1.99F, -29.75F, -10.0F, 4, 4, 20, 0.0F);
	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) 
	{
		this.partialTicks = partialTick;
	}

	@Override
	public void render(@Nonnull MatrixStack matrix, @Nonnull IVertexBuilder vertexBuilder, int light, int overlayLight, float red, float green, float blue, float alpha) 
	{
		Head.render(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
		RingX.render(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
		RingY.render(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
		RingZ.render(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		float entityRotation0 = (float) (Math.toRadians(netHeadYaw) - Math.toRadians(entityIn.getYaw(this.partialTicks)));
		if(entityIn instanceof SolarionicAbominationEntity)
		{
			SolarionicAbominationEntity abomination = (SolarionicAbominationEntity) entityIn;
			if(!abomination.isLunarionic())
			{			
				this.RingX.rotateAngleX = (ageInTicks) / 6;
				this.RingX.rotateAngleY = entityRotation0;
				
				this.RingY.rotateAngleY = (ageInTicks) / 10 + entityRotation0;
				
				this.RingZ.rotateAngleX = 0;
				this.RingZ.rotateAngleY = 0;
				this.RingZ.rotateAngleZ = (float) ((ageInTicks) / 14);
			}
			else
			{
				this.RingX.rotateAngleX = 0;
				this.RingX.rotateAngleY = entityRotation0;
				
				this.RingY.rotateAngleY = entityRotation0;
				
				this.RingZ.rotateAngleZ = 0;
				this.RingZ.rotateAngleY = (float) (entityRotation0 - (Math.PI / 2));
			}
		}
	}
}