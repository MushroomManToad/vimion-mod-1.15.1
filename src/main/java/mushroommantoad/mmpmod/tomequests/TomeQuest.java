package mushroommantoad.mmpmod.tomequests;

import mushroommantoad.mmpmod.tomequests.clearcons.ClearCondition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class TomeQuest 
{
	TranslationTextComponent name;
	TranslationTextComponent[] parents;
	ClearCondition clearcon;
	String chapter;
	TranslationTextComponent hoverText;
	TranslationTextComponent greyText;
	TranslationTextComponent goldText;
	int page;
	int x;
	int y;
	ResourceLocation sprite;
	
	public TomeQuest(String name, String chapter, int page, int x, int y, ClearCondition clearcon, ResourceLocation sprite, String...parents)
	{
		this.name = new TranslationTextComponent(name);
		this.parents = new TranslationTextComponent[parents.length];
		for(int i = 0; i < parents.length; i++)
		{
			this.parents[i] = new TranslationTextComponent(parents[i]);
		}
		this.clearcon = clearcon;
		this.chapter = chapter;
		this.hoverText = new TranslationTextComponent(name + ".hover");
		this.greyText = new TranslationTextComponent(name + ".grey");
		this.goldText = new TranslationTextComponent(name + ".gold");
		this.page = page;
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void clearClearCon(PlayerEntity playerIn)
	{
		CompoundNBT nbt = playerIn.getPersistentData();
		if (!nbt.contains("VimionTomeQuests")) {
			nbt.put("VimionTomeQuests", new CompoundNBT());
		}
		
		CompoundNBT tomenbt = nbt.getCompound("VimionTomeQuests");
		
		if(!tomenbt.getBoolean(name.getKey()) && evaluateParentsServer(tomenbt, this.getParents()))
		{
			tomenbt.putBoolean(name.getKey(), true);
			if(!this.parents[0].getKey().equals(VTIDs.NONE))
			{
				String s0 = "Reasearch on ";
				ITextComponent s1 = new StringTextComponent("[").appendSibling(new StringTextComponent(name.getUnformattedComponentText()).appendSibling(new StringTextComponent("]"))).applyTextStyle(chapter.equals("necrion") ? TextFormatting.LIGHT_PURPLE : (chapter.equals("solarion") ? TextFormatting.YELLOW : (chapter.equals("nihilion") ? TextFormatting.RED : (chapter.equals("expion") ? TextFormatting.BLUE : TextFormatting.GREEN))));
				String s2 = " complete! More research notes are now available in your Vimionic Tome!";
				playerIn.sendMessage(new StringTextComponent(s0).appendSibling(s1).appendText(s2));
			}
		}
	}
	
	public static boolean evaluateParentsServer(CompoundNBT tomenbt, TranslationTextComponent[] parentsArray)
	{
		int count = 0;
		for(TranslationTextComponent t : parentsArray)
		{
			if(t.getKey().equals(VTIDs.NONE)) return true;
			if(tomenbt.contains(t.getKey()))
			{
				if(tomenbt.getBoolean(t.getKey())) count++;
			}
		}
		if(count == parentsArray.length) return true;
		else return false;
	}
	
	// Getters VV
	
	public TranslationTextComponent getName() {return this.name;}
	
	public TranslationTextComponent[] getParents(){return this.parents;}
	
	public ClearCondition getClearCondition(){return this.clearcon;}
	
	public String getChapter(){return this.chapter;}
	
	public TranslationTextComponent getHoverText(){return this.hoverText;}
	
	public TranslationTextComponent getGreyText(){return this.greyText;}
	
	public TranslationTextComponent getGoldText(){return this.goldText;}
	
	public int getPage(){return this.page;}
	
	public int getX(){return this.x;}
	
	public int getY(){return this.y;}
	
	public ResourceLocation getSprite(){return this.sprite;}
	
	
}
