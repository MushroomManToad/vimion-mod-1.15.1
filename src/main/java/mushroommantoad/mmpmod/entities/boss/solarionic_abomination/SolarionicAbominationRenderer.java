package mushroommantoad.mmpmod.entities.boss.solarionic_abomination;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SolarionicAbominationRenderer extends MobRenderer<SolarionicAbominationEntity, SolarionicAbominationModel<SolarionicAbominationEntity>>
{
	private static final ResourceLocation TEXTURE_SOLARIONIC = ModEntities.location("textures/entity/boss/solarionic_abomination.png");
	private static final ResourceLocation TEXTURE_LUNARIONIC = ModEntities.location("textures/entity/boss/lunarionic_abomination.png");
	
	private static final ResourceLocation TEXTURE_BLUE = ModEntities.location("textures/entity/boss/blue_solarionic_abomination.png");
	private static final ResourceLocation TEXTURE_RED = ModEntities.location("textures/entity/boss/red_solarionic_abomination.png");	
	private static final ResourceLocation TEXTURE_ORANGE = ModEntities.location("textures/entity/boss/orange_solarionic_abomination.png");	
	private static final ResourceLocation TEXTURE_WHITE = ModEntities.location("textures/entity/boss/white_solarionic_abomination.png");	
	private static final ResourceLocation TEXTURE_BLACK = ModEntities.location("textures/entity/boss/black_solarionic_abomination.png");
	private static final ResourceLocation TEXTURE_PURPLE = ModEntities.location("textures/entity/boss/purple_solarionic_abomination.png");	
	
	public SolarionicAbominationRenderer(EntityRendererManager manager) 
	{
		super(manager, new SolarionicAbominationModel<SolarionicAbominationEntity>(), 0f);
		this.addLayer(new SolarionicAbominationEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(SolarionicAbominationEntity entity) 
	{
		switch(entity.getStellarType())
		{
		case(0):
			return entity.isLunarionic() ? TEXTURE_LUNARIONIC : TEXTURE_SOLARIONIC;
		case(1):
			return TEXTURE_BLUE;
		case(2):
			return TEXTURE_RED;
		case(3):
			return TEXTURE_ORANGE;
		case(4):
			return TEXTURE_WHITE;
		case(5):
			return TEXTURE_BLACK;
		case(6):
			return TEXTURE_PURPLE;
		default:
			return TEXTURE_SOLARIONIC;
		}
	}
}
