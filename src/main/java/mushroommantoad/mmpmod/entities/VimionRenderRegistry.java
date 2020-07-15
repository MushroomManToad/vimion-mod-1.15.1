package mushroommantoad.mmpmod.entities;

import mushroommantoad.mmpmod.entities.boss.expionic_abomination.ExpionicAbominationRenderer;
import mushroommantoad.mmpmod.entities.boss.solarionic_abomination.SolarionicAbominationRenderer;
import mushroommantoad.mmpmod.entities.boss.solarionic_abomination.fire_blast.FireBlastRenderer;
import mushroommantoad.mmpmod.entities.boss.solarionic_abomination.solar_blast.SolarBlastRenderer;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.VimionicAbominationRenderer;
import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.absorption_spire.RendererAbsorptionSpire;
import mushroommantoad.mmpmod.entities.spectral.chicken.SpectralChickenRenderer;
import mushroommantoad.mmpmod.entities.spectral.cow.SpectralCowRenderer;
import mushroommantoad.mmpmod.entities.spectral.pig.SpectralPigRenderer;
import mushroommantoad.mmpmod.entities.spectral.rabbit.SpectralRabbitRenderer;
import mushroommantoad.mmpmod.entities.spectral.sheep.SpectralSheepRenderer;
import mushroommantoad.mmpmod.entities.spectral.soul.SpectralSoulRenderer;
import mushroommantoad.mmpmod.entities.spectral.sprite.SpectralSpriteRenderer;
import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class VimionRenderRegistry 
{
	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECTRAL_SHEEP, SpectralSheepRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECTRAL_COW, SpectralCowRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECTRAL_CHICKEN, SpectralChickenRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECTRAL_PIG, SpectralPigRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECTRAL_RABBIT, SpectralRabbitRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECTRAL_SPRITE, SpectralSpriteRenderer::new);
		
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPECTRAL_SOUL, SpectralSoulRenderer::new);
		
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.VIMIONIC_ABOMINATION, VimionicAbominationRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SOLARIONIC_ABOMINATION, SolarionicAbominationRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.EXPIONIC_ABOMINATION, ExpionicAbominationRenderer::new);
		
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ABSORPTION_SPIRE, RendererAbsorptionSpire::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SOLAR_BLAST, SolarBlastRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.FIRE_BLAST, FireBlastRenderer::new);
	}
}
