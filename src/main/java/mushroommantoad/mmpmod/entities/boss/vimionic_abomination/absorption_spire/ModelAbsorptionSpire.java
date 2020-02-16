package mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelAbsorptionSpire extends EntityModel<EntityAbsorptionSpire> {
	private final ModelRenderer bone;

	public ModelAbsorptionSpire() {
		textureWidth = 64;
		textureHeight = 64;

		int yModif = 16;
		
		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.setTextureOffset(32, 19);
		bone.addBox(-4.0F, -36.0F + yModif, -4.0F, 8, 32, 8, 0.0F);
		bone.setTextureOffset(0, 55);
		bone.addBox(-3.0F, -4.0F + yModif, -3.0F, 6, 1, 6, 0.0F);
		bone.setTextureOffset(26, 38);
		bone.addBox(-3.0F, -37.0F + yModif, -3.0F, 6, 1, 6, 0.0F);
		bone.setTextureOffset(32, 53);
		bone.addBox(-2.0F, -3.0F + yModif, -2.0F, 4, 1, 4, 0.0F);
		bone.setTextureOffset(26, 40);
		bone.addBox(-2.0F, -38.0F + yModif, -2.0F, 4, 1, 4, 0.0F);
		bone.setTextureOffset(53, 35);
		bone.addBox(-1.0F, -2.0F + yModif, -1.0F, 2, 1, 2, 0.0F);
		bone.setTextureOffset(28, 18);
		bone.addBox(-1.0F, -39.0F + yModif, -1.0F, 2, 1, 2, 0.0F);
	}

	@Override
	public void render(@Nonnull MatrixStack matrix, @Nonnull IVertexBuilder vertexBuilder, int light, int overlayLight, float red, float green, float blue, float alpha) 
	{
		bone.render(matrix, vertexBuilder, light, overlayLight, red, green, blue, alpha);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(EntityAbsorptionSpire entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float yaw, float pitch)
	{
		this.bone.rotateAngleY = ageInTicks / 30;
	}
}