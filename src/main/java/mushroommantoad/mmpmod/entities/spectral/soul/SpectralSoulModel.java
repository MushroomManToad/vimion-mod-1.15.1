package mushroommantoad.mmpmod.entities.spectral.soul;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpectralSoulModel extends EntityModel<SpectralSoulEntity> {

	public SpectralSoulModel() 
	{
		textureWidth = 16;
		textureHeight = 16;
	}

	@Override
	public void render(@Nonnull MatrixStack matrix, @Nonnull IVertexBuilder vertexBuilder, int light, int overlayLight, float red, float green, float blue, float alpha)
	{
		
	}
	
	@Override
	public void setRotationAngles(SpectralSoulEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float yaw, float pitch)
	{
		
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}