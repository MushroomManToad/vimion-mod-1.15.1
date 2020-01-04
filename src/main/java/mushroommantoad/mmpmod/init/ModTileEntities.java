package mushroommantoad.mmpmod.init;

import org.apache.logging.log4j.Logger;

import mushroommantoad.mmpmod.Main;
import mushroommantoad.mmpmod.blocks.expion.crate.TileEntityExpionCrate;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;

public class ModTileEntities 
{
	public static TileEntityType<TileEntityExpionCrate> EXPIONITE_CRATE;
	
	@SuppressWarnings("unchecked")
	public static void registerAll(RegistryEvent.Register<TileEntityType<?>> event, Logger logger)
	{
		event.getRegistry().register(EXPIONITE_CRATE = (TileEntityType<TileEntityExpionCrate>) TileEntityType.Builder.create(TileEntityExpionCrate::new, ModBlocks.expionite_crate).build(null).setRegistryName(Main.modid, "expionite_crate"));
	}
}
