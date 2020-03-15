package mushroommantoad.mmpmod.items;

import java.util.Random;

import mushroommantoad.mmpmod.blocks.necrion.NecrioniteSummonerBlock;
import mushroommantoad.mmpmod.entities.spectral.soul.SpectralSoulEntity;
import mushroommantoad.mmpmod.init.ModEntities;
import mushroommantoad.mmpmod.util.MushroomsUtil;
import net.minecraft.block.AirBlock;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemSoul extends Item
{
	public ItemSoul(Properties properties) 
	{
		super(properties);
	}
	
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		World worldIn = context.getWorld();
		PlayerEntity playerIn = context.getPlayer();
		BlockPos pos = context.getPos();
		Random rand = new Random();
		
		if(worldIn.isRemote)
		{
			if(worldIn.getBlockState(pos).getBlock() instanceof NecrioniteSummonerBlock)
			{
				for(int i = 0; i < 100; i++)
				{
					worldIn.addParticle(ParticleTypes.DRAGON_BREATH, (pos.getX() + 0.5), (pos.getY() + 0.1 + (0.05 * i)), (pos.getZ() + 0.5), 0, 0, 0);
				}
				for(double x = -3; x < 3; x = x + 0.1)
				{
					for(double y = -3; y < 3; y = y + 0.1)
					{
						if(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) < 2)
						{
							worldIn.addParticle(ParticleTypes.SQUID_INK, (pos.getX() + x + 0.5), (pos.getY() + 5.1), (pos.getZ() + y + 0.5), 0, 0, 0);
						}
					}
				}
			}
		}
		
		if(!worldIn.isRemote)
		{
			if(worldIn.getBlockState(pos).getBlock() instanceof NecrioniteSummonerBlock)
			{
				BlockPos nextPos = this.computeNextPos(worldIn, pos, rand);
				
				SpectralSoulEntity soul = new SpectralSoulEntity(ModEntities.SPECTRAL_SOUL, worldIn);
				
				soul.getType().spawn(worldIn, context.getItem(), playerIn, nextPos, SpawnReason.TRIGGERED, false, false);
				
				CompoundNBT nbt = context.getItem().getTag();
				if(nbt != null)
				{
					if(nbt.contains("entityIn"))
					{
						soul.setRender(nbt.getString("entityIn"));
					}
				}
				
				context.getItem().shrink(1);
				
				// Send packet to spawn particles with the summoner's pos and nextPos;
				worldIn.playSound(null, nextPos.getX(), nextPos.getY(), nextPos.getZ(), SoundEvents.BLOCK_BELL_RESONATE, SoundCategory.BLOCKS, 16, -1);
				worldIn.playSound(null, nextPos.getX(), nextPos.getY(), nextPos.getZ(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 16, 0);
				worldIn.playSound(null, nextPos.getX(), nextPos.getY(), nextPos.getZ(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 16, 0);
				worldIn.playSound(null, nextPos.getX(), nextPos.getY(), nextPos.getZ(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 16, 0);
				
				return ActionResultType.SUCCESS;
			}
		}
		
		return super.onItemUse(context);
	}
	
	public BlockPos computeNextPos(World worldIn, BlockPos pos, Random rand)
	{
		BlockPos testPos = new BlockPos(pos);
		for(int i = 0; i < 32; i++)
		{
			testPos = pos.add(rand.nextInt(5) * MushroomsUtil.StaticMinusPlus(), rand.nextInt(2) * MushroomsUtil.StaticMinusPlus(), rand.nextInt(5) * MushroomsUtil.StaticMinusPlus());
			if(!(worldIn.getBlockState(testPos).getBlock() instanceof AirBlock) && worldIn.getBlockState(testPos.add(0, 1, 0)).getBlock() instanceof AirBlock)
			{
				return testPos.add(0, 1, 0);
			}
			testPos = pos;
		}
		return pos.add(0, 1, 0);
	}
	
	public void updateName(ItemStack stack)
	{
		CompoundNBT nbt = stack.getTag();
		if(nbt != null)
		{
			if(nbt.contains("entityIn"))
			{
				StringTextComponent entityName = new StringTextComponent(MushroomsUtil.nameFixer(nbt.getString("entityIn")));
				entityName.appendText(" Soul");
				stack.setDisplayName(entityName);
			}
			else
			{
				stack.setDisplayName(new TranslationTextComponent("soul.empty"));
			}
		}
	}
}
