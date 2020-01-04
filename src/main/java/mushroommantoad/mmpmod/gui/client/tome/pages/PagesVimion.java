package mushroommantoad.mmpmod.gui.client.tome.pages;

import java.util.ArrayList;

import mushroommantoad.mmpmod.Main;
import mushroommantoad.mmpmod.gui.client.tome.GuiTome;
import mushroommantoad.mmpmod.gui.client.tome.GuiTomeHoverObject;
import mushroommantoad.mmpmod.gui.client.tome.GuiTomePage;
import mushroommantoad.mmpmod.util.VTIDHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("unused")
@OnlyIn(Dist.CLIENT)
public class PagesVimion 
{
	private static ResourceLocation VIMIONIC_TOME = new ResourceLocation(Main.modid + ":textures/item/vimionic_tome.png");
	private static ResourceLocation VIMION_GEMSTONE = new ResourceLocation(Main.modid + ":textures/gui/icon/vimion_gemstone.png");
	private static ResourceLocation VIMIONITE_DAGGER = new ResourceLocation(Main.modid + ":textures/item/vimionite_dagger.png");
	private static ResourceLocation ADVANCED_PHASER = new ResourceLocation(Main.modid + ":textures/gui/icon/advanced_geologic_phaser.png");
	private static ResourceLocation VIMION_SPIRIT = new ResourceLocation(Main.modid + ":textures/gui/icon/vimion_spirit.png");
	
	// TODO
	public static GuiTomePage getPage(GuiTome tome, int id)
	{
		return get0(tome);
	}
	
	public static GuiTomePage get0(GuiTome tome)
	{
		PlayerEntity player = tome.getPlayer();
		
		ArrayList<GuiTomeHoverObject> ho = new ArrayList<>();
		
		String tempText = "The Vimionic Tome can be used to view information about and your progress towards various objectives within the Vimion Mod." + "\n\n" + "It has many useful features included for your use. These will be outlined in the following pages." + "\n\n" + "First, the arrows on the bottom of each page can be used to cycle through these info pages. It's probably important to mention that first." + "\n\n" + "To exit out of this info page and return to where you were in the Vimionic Tome, just press [Esc] on your keyboard." + "\n" + "The Vimionic Tome contains 5 chapters. These can be cycled through with the tab buttons on the side of the Objective View. These chapters may appear empty early in the mod, but they will slowly fill up with Objectives as you progress." + "\n\n\n" + "Hovering over an objective in the Objective View part of this GUI will display that Objective's title and a short description of what you have to do to complete it." + "\n\n\n\n\n\n" + "Before completing the Objective, it will appear in grey. Clicking on the Objective at this point will take you to an info page that will go into more detail about what you need to do to complete the Objective as well as share a little lore about it." + "\n\n" + "After completing an Objective, it will appear in gold in Objective View. Clicking on it at this point will open up an info page that goes much deeper into its lore as well as giving hints about what you should do next." + "\n\n\n\n" + "Note that you cannot complete an Objective prior to completing its \"Parent\" Objective (This is usually the Objective you had to complete before it appeared in the Tome)." + "\n\n\n\n\n" + "For example, you can't complete the \"Craft a Necrion Gemstone\" Objective before completing the \"Craft an Advanced Geologic Phaser\" one.";
		
		ho.add(new GuiTomeHoverObject(tome, 10, tome.ySize / 2 - 14, VIMIONIC_TOME, "Basics of the Vimionic Tome", PagesDisplayText.HOVER_VIMIONIC_TOME, true, PagesDisplayText.GREY_VIMIONIC_TOME, tempText));
		ho.add(new GuiTomeHoverObject(tome, 40, tome.ySize / 2 - 14, VIMION_GEMSTONE, "Vimion!", PagesDisplayText.HOVER_VIMION_GEMSTONE, tome.getProgress()[VTIDHandler.OBJECTIVE_VIMION_GEMSTONE] == 1, PagesDisplayText.GREY_VIMION_GEMSTONE, PagesDisplayText.GOLD_VIMION_GEMSTONE));
		if(tome.getProgress()[VTIDHandler.OBJECTIVE_VIMION_GEMSTONE] == 1) ho.add(new GuiTomeHoverObject(tome, 70, tome.ySize / 2 + 14, ADVANCED_PHASER, "Advanced Phasing", PagesDisplayText.HOVER_ADVANCED_GEOLOGIC_PHASER, tome.getProgress()[VTIDHandler.OBJECTIVE_CRAFT_ADVANCED_GEOLOGIC_PHASER] == 1, PagesDisplayText.GREY_ADVANCED_GEOLOGIC_PHASER, PagesDisplayText.GOLD_ADVANCED_GEOLOGIC_PHASER));
		if(tome.getProgress()[VTIDHandler.OBJECTIVE_VIMION_GEMSTONE] == 1) ho.add(new GuiTomeHoverObject(tome, 70, tome.ySize / 2 - 42, VIMIONITE_DAGGER, "Spirits?", PagesDisplayText.HOVER_SPIRITS_QUESTION, tome.getProgress()[VTIDHandler.OBJECTIVE_CRAFT_VIMIONIC_DAGGER] == 1, PagesDisplayText.GREY_SPIRITS_QUESTION, PagesDisplayText.GOLD_SPIRITS_QUESTION));
		if(tome.getProgress()[VTIDHandler.OBJECTIVE_CRAFT_VIMIONIC_DAGGER] == 1) ho.add(new GuiTomeHoverObject(tome, 138, tome.ySize / 2 - 42 - 4, VIMION_SPIRIT, "Spirits!", PagesDisplayText.HOVER_OBTAIN_MOB_SPIRIT, tome.getProgress()[VTIDHandler.OBJECTIVE_OBTAIN_MOB_SPIRIT] == 1, PagesDisplayText.GREY_OBTAIN_MOB_SPIRIT, PagesDisplayText.GOLD_OBTAIN_MOB_SPIRIT));
		
		return new GuiTomePage(tome, ho);
	}
}
