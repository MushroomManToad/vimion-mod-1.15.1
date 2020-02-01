package mushroommantoad.mmpmod.lists;

import mushroommantoad.mmpmod.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum ItemTierList implements IItemTier
{
	vimionite(5.0f, 9.0f, 400, 3, 1, ModItems.vimion_shard),
	necrionite(6.0f, 4.0f, 600, 2, 25, ModItems.necrion_shard),
	solarionite(4.0f, 12.0f, 400, 2, 5, ModItems.solarion_shard),
	nihilionite(8.0f, 10.0f, 400, 3, 1, ModItems.nihilion_shard),
	expionite(2.0f, 0.3f, 250, -1, 25, ModItems.expion_shard);
	
	
	
	
	private float attackDamage, effiency;
	private int durability, harvestLevel, enchantability;
	private Item repairMaterial;
	
	private ItemTierList(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantability, Item repairMaterial)
	{
		this.attackDamage = attackDamage;
		this.effiency = efficiency;
		this.durability = durability;
		this.harvestLevel = harvestLevel;
		this.enchantability = enchantability;
		this.repairMaterial = repairMaterial;
	}

	@Override
	public int getMaxUses()
	{
		return this.durability;
	}

	@Override
	public float getEfficiency() 
	{
		return this.effiency;
	}

	@Override
	public float getAttackDamage() 
	{
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() 
	{
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() 
	{
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() 
	{
		return Ingredient.fromItems(this.repairMaterial);
	}
}
