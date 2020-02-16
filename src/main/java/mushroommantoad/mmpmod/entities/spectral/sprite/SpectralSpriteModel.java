package mushroommantoad.mmpmod.entities.spectral.sprite;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralSpriteModel extends EntityModel<SpectralSpriteEntity> {
	private final ModelRenderer core;
	private final ModelRenderer lwing;
	private final ModelRenderer rwing;

	public SpectralSpriteModel() 
	{
		textureWidth = 16;
		textureHeight = 16;

		core = new ModelRenderer(this);
		core.setRotationPoint(0.0F, 22.0F, 0.0F);
		core.setTextureOffset(0, 0);
		core.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);

		lwing = new ModelRenderer(this);
		lwing.setRotationPoint(2.0F, 0.0F, 0.0F);
		setRotationAngle(lwing, 0.1745F, 0.0F, 0.0F);
		core.addChild(lwing);
		lwing.setTextureOffset(0, 8);
		lwing.addBox(0.0F, 0.0F, -2.0F, 4, 0, 4, 0.0F);

		rwing = new ModelRenderer(this);
		rwing.setRotationPoint(-2.0F, 0.0F, 0.0F);
		setRotationAngle(rwing, 0.1745F, 0.0F, 0.0F);
		core.addChild(rwing);
		rwing.setTextureOffset(0, 12);
		rwing.addBox(-4.0F, 0.0F, -2.0F, 4, 0, 4, 0.0F);
	}

	@Override
	public void render(@Nonnull MatrixStack matrix, @Nonnull IVertexBuilder vertexBuilder, int light, int overlayLight, float red, float green, float blue, float alpha)
	{
		core.render(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	}
	
	@Override
	public void setRotationAngles(SpectralSpriteEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float yaw, float pitch)
	{
		this.core.setRotationPoint(0.0F, (float) (22.0F + Math.cos(ageInTicks / 30)), 0.0F);
		
		this.lwing.rotateAngleZ = (float) Math.cos(ageInTicks / 2);
		this.rwing.rotateAngleZ = (float) -Math.cos(ageInTicks / 2);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}