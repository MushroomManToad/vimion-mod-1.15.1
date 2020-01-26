package mushroommantoad.mmpmod.entities.boss.vimionic_abomination;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VimionicAbominationRenderer extends MobRenderer<VimionicAbominationEntity, VimionicAbominationModel>
{
	private static final ResourceLocation TEXTURE = ModEntities.location("textures/entity/boss/vimionic_abomination.png");
	private static final ResourceLocation TEXTURE_CASTING = ModEntities.location("textures/entity/boss/vimionic_abomination_casting.png");
	
	public VimionicAbominationRenderer(EntityRendererManager manager) 
	{
		super(manager, new VimionicAbominationModel(), 0f);
	}

	@Override
	public ResourceLocation getEntityTexture(VimionicAbominationEntity entity) 
	{
		return entity.isSummoningEntity() ? TEXTURE_CASTING : TEXTURE;
	}
}
