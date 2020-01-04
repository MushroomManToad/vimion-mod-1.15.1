package mushroommantoad.mmpmod.entities.boss.vimionic_abomination;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class VimionicAbominationRenderer extends MobRenderer<VimionicAbominationEntity, VimionicAbominationModel>
{
	public VimionicAbominationRenderer(EntityRendererManager manager) 
	{
		super(manager, new VimionicAbominationModel(), 0f);
	}

	@Override
	protected ResourceLocation getEntityTexture(VimionicAbominationEntity entity) 
	{
		return ModEntities.location("textures/entity/boss/vimionic_abomination.png");
	}
	
	public static class RenderFactory implements IRenderFactory<VimionicAbominationEntity>
	{
		public EntityRenderer<? super VimionicAbominationEntity> createRenderFor(EntityRendererManager manager)
		{
			return new VimionicAbominationRenderer(manager);
		}
	}
}
