package mushroommantoad.mmpmod.entities;

import mushroommantoad.mmpmod.entities.boss.expionic_abomination.ExpionicAbominationEntity;
import mushroommantoad.mmpmod.entities.boss.expionic_abomination.ExpionicAbominationRenderer;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.VimionicAbominationEntity;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.VimionicAbominationRenderer;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire.EntityAbsorptionSpire;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire.RendererAbsorptionSpire;
import mushroommantoad.mmpmod.entities.spectral.chicken.SpectralChickenEntity;
import mushroommantoad.mmpmod.entities.spectral.chicken.SpectralChickenRenderer;
import mushroommantoad.mmpmod.entities.spectral.cow.SpectralCowEntity;
import mushroommantoad.mmpmod.entities.spectral.cow.SpectralCowRenderer;
import mushroommantoad.mmpmod.entities.spectral.pig.SpectralPigEntity;
import mushroommantoad.mmpmod.entities.spectral.pig.SpectralPigRenderer;
import mushroommantoad.mmpmod.entities.spectral.rabbit.SpectralRabbitEntity;
import mushroommantoad.mmpmod.entities.spectral.rabbit.SpectralRabbitRenderer;
import mushroommantoad.mmpmod.entities.spectral.sheep.SpectralSheepEntity;
import mushroommantoad.mmpmod.entities.spectral.sheep.SpectralSheepRenderer;
import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class VimionRenderRegistry 
{
	@SuppressWarnings("unchecked")
	public static void registerEntityRenders()
	{
		//RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECTRAL_SHEEP, SpectralSheepRenderer::new);
		//RenderingRegistry.registerEntityRenderingHandler(SpectralCowEntity.class, new SpectralCowRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECTRAL_CHICKEN, new SpectralChickenRenderer.RenderFactory());
		//RenderingRegistry.registerEntityRenderingHandler(SpectralPigEntity.class, new SpectralPigRenderer.RenderFactory());
		//RenderingRegistry.registerEntityRenderingHandler(SpectralRabbitEntity.class, new SpectralRabbitRenderer.RenderFactory());
		//RenderingRegistry.registerEntityRenderingHandler(VimionicAbominationEntity.class, new VimionicAbominationRenderer.RenderFactory());
		//RenderingRegistry.registerEntityRenderingHandler(ExpionicAbominationEntity.class, new ExpionicAbominationRenderer.RenderFactory());
		
		//RenderingRegistry.registerEntityRenderingHandler(EntityAbsorptionSpire.class, new RendererAbsorptionSpire.RenderFactory());
	}
}
