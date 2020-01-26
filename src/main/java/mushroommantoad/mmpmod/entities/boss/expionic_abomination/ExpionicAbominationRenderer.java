package mushroommantoad.mmpmod.entities.boss.expionic_abomination;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExpionicAbominationRenderer extends MobRenderer<ExpionicAbominationEntity, ExpionicAbominationModel<ExpionicAbominationEntity>>
{
	public ExpionicAbominationRenderer(EntityRendererManager manager) 
	{
		super(manager, new ExpionicAbominationModel<ExpionicAbominationEntity>(), 0f);
		this.addLayer(new ExptionicAbominationEyesLayer<>(this));
	}
	
	/*
	@Override
	public void doRender(ExpionicAbominationEntity entity, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		ExpionicAbominationModel<ExpionicAbominationEntity> expionicmodel = this.getEntityModel();
		expionicmodel.isMoving = entity.isMoving();
		expionicmodel.isTPChannelling = entity.isTPChannelling();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	*/

	@Override
	public ResourceLocation getEntityTexture(ExpionicAbominationEntity entity) 
	{
		return ModEntities.location("textures/entity/boss/expionic_abomination.png");
	}
}
