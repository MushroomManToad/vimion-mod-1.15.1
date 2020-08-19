package mushroommantoad.mmpmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mushroommantoad.mmpmod.entities.VimionRenderRegistry;
import mushroommantoad.mmpmod.init.ModBlocks;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.init.ModItems;
import mushroommantoad.mmpmod.init.ModSoundEvents;
import mushroommantoad.mmpmod.init.ModTileEntities;
import mushroommantoad.mmpmod.init.ModTomeQuests;
import mushroommantoad.mmpmod.itemgroups.ItemGroupVimion;
import mushroommantoad.mmpmod.network.VimionPacketHandler;
import mushroommantoad.mmpmod.proxy.ClientProxy;
import mushroommantoad.mmpmod.proxy.CommonProxy;
import mushroommantoad.mmpmod.util.MushroomsEventHandler;
import mushroommantoad.mmpmod.util.VimionicTomeListener;
import mushroommantoad.mmpmod.util.registry.ParticleRegistry;
import mushroommantoad.mmpmod.world.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("vimion")
public class Main {
	public static Main instance;
	public static final String modid = "vimion";
	private static final Logger logger = LogManager.getLogger(modid);
	public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	VimionPacketHandler networkHandler = new VimionPacketHandler();

	public static final ItemGroup vimion = new ItemGroupVimion();
	
	

	public Main() {
		instance = this;

		networkHandler.registerPackets();
		
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::clientRegistries);
		
		// Deferred Registries should eventually be moved here. @1.16
		ParticleRegistry.PARTICLES.register(modEventBus);
		// Deferred Registries should eventually be moved here. @1.16

		MinecraftForge.EVENT_BUS.register(this);
		
		MinecraftForge.EVENT_BUS.register(new MushroomsEventHandler());
		MinecraftForge.EVENT_BUS.register(new VimionicTomeListener());
	}

	// PreInit
	private void setup(final FMLCommonSetupEvent event) {
		OreGeneration.setupOreGeneration();
		logger.info("Setup Server PreInit");
	}

	// PreInit
    @SuppressWarnings("unused")
	private void clientRegistries(final FMLClientSetupEvent event) {
		VimionRenderRegistry.registerEntityRenders();
    	RenderType solid = RenderType.getSolid();
    	RenderType cutout = RenderType.getCutout();
        RenderType cutoutmipped = RenderType.getCutoutMipped();
		RenderType translucent = RenderType.getTranslucent();
        RenderType translucentmipped = RenderType.getTranslucentNoCrumbling();

        RenderTypeLookup.setRenderLayer(ModBlocks.berries_of_life_bush, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.berries_of_death_bush, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.berries_of_the_sun_bush, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.berries_of_annihilation_bush, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.berries_of_the_universe_bush, cutout);
        
        RenderTypeLookup.setRenderLayer(ModBlocks.blue_stellar_torch, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.blue_stellar_wall_torch, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.red_stellar_torch, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.red_stellar_wall_torch, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.orange_stellar_torch, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.orange_stellar_wall_torch, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.white_stellar_torch, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.white_stellar_wall_torch, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.black_stellar_torch, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.black_stellar_wall_torch, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.purple_stellar_torch, cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.purple_stellar_wall_torch, cutout);
        logger.debug("Completed Misc. Renders");
		logger.info("Client Registries Added");
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void registerSounds(final RegistryEvent.Register<SoundEvent> event) {
			ModSoundEvents.registerAll(event, logger);
		}
		
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			ModItems.registerAll(event, logger);
			ModTomeQuests.registerAll();
		}

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			ModBlocks.registerAll(event, logger);
		}
		
		@SubscribeEvent
		public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event)	{
			ModTileEntities.registerAll(event, logger);
		}
		
		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
			ModEntities.registerAll(event, logger);
		}
	}
}
