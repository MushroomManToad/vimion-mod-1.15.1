package mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelAbsorptionSpire extends EntityModel<EntityAbsorptionSpire> {
	private final RendererModel bone;

	public ModelAbsorptionSpire() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 32, 19, -4.0F, -36.0F, -4.0F, 8, 32, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 55, -3.0F, -4.0F, -3.0F, 6, 1, 6, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 26, 38, -3.0F, -37.0F, -3.0F, 6, 1, 6, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 32, 53, -2.0F, -3.0F, -2.0F, 4, 1, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 26, 40, -2.0F, -38.0F, -2.0F, 4, 1, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 53, 35, -1.0F, -2.0F, -1.0F, 2, 1, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 28, 18, -1.0F, -39.0F, -1.0F, 2, 1, 2, 0.0F, false));
	}

	@Override
	public void render(EntityAbsorptionSpire entity, float f, float f1, float ageInTicks, float f3, float f4, float f5) 
	{
		
		this.bone.rotateAngleY = ageInTicks / 30;
		bone.render(f5);
	}
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}