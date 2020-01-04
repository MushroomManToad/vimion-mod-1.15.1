package mushroommantoad.mmpmod.blocks.expion.crate;

import javax.annotation.Nullable;

import mushroommantoad.mmpmod.init.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityExpionCrate extends TileEntity implements ITickableTileEntity
{
	private ItemStackHandler isHandler;
	
	private int itemCount; 
	
	public int getItemCount() { return itemCount; }
	public void setItemCount(int value) { this.itemCount = value; }
	
	public ItemStackHandler getItemStackHandler()
	{
		if(isHandler == null)
		{
			isHandler = new ItemStackHandler(1);
		}
		return isHandler;
	}
	
	public TileEntityExpionCrate() 
	{
		super(ModTileEntities.EXPIONITE_CRATE);
	}
	
	@Override
	public void read(CompoundNBT compound) 
	{
		CompoundNBT invTag = compound.getCompound("inventory");
		getItemStackHandler().deserializeNBT(invTag);
		super.read(compound);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) 
	{
		CompoundNBT nbt = getItemStackHandler().serializeNBT();
		compound.put("inv", nbt);
		return super.write(compound);
	}
	
	// TODO
	public int addItems(int amount)
	{
		if(this.getItemCount() + amount <= 8192)
		{
			this.setItemCount(this.getItemCount() + amount);
			return amount;
		}
		else
		{
			int i = this.getItemCount();
			this.setItemCount(8192);
			return 8192 - i;
		}
	}
	
	// TODO
	public void subtractItems(int amount)
	{
		
		this.setItemCount(this.getItemCount() - amount);
	}
	
	// TODO
	public void subtractItemsPlayer(PlayerEntity player, int amount)
	{
		
	}
	
	// TODO
	public void addItemsPlayer(PlayerEntity player, int amount)
	{
		
	}

	@Override
	public void tick() 
	{
		if(!this.getWorld().isRemote)
		{
			if(this.getItemCount() >= 2)
			{
				ItemStack held = isHandler.getStackInSlot(0);
				if(held.getCount() > 2)
				{
					addItems(held.getCount() - 2);
					held.shrink(held.getCount() - 2);
				}
				if(held.getCount() == 2)
				{
					subtractItems(2 - held.getCount());
					held.grow(2 - held.getCount());
				}
			}
		}
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() 
	{
		return new SUpdateTileEntityPacket(this.pos, 11, this.getUpdateTag());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return LazyOptional.of(() -> (T) getItemStackHandler());
		}
		return super.getCapability(cap);
	}
}
