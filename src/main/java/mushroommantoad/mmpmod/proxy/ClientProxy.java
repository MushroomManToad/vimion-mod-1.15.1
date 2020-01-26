package mushroommantoad.mmpmod.proxy;

import mushroommantoad.mmpmod.entities.boss.vimionic_abomination.VimionicAbominationEntity;
import mushroommantoad.mmpmod.gui.client.tome.GuiTome;
import mushroommantoad.mmpmod.init.ModBlocks;
import mushroommantoad.mmpmod.network.SToCAbsorptionSpireParticlePacket;
import mushroommantoad.mmpmod.network.SToCExpionicTeleportParticlePacket;
import mushroommantoad.mmpmod.network.SendBookOpenPacket;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit() 
	{
		
	}
	
	@Override
	public void openVimionTomeGUI(ServerPlayerEntity sender, SendBookOpenPacket message) 
	{
		Minecraft.getInstance().displayGuiScreen(new GuiTome(sender, message.getBookData()));
	}
	
	@Override
	public void displaySpireParticles(ServerPlayerEntity sender, SToCAbsorptionSpireParticlePacket message) 
	{
		World worldIn = Minecraft.getInstance().world;
		AxisAlignedBB aabb = new AxisAlignedBB(message.sX + 5, message.sY + 5, message.sZ + 5, message.sX - 5, message.sY - 5, message.sZ - 5);
		for(LivingEntity livingentity : worldIn.getEntitiesWithinAABB(LivingEntity.class, aabb)) 
		{
			if(!(livingentity instanceof VimionicAbominationEntity) && livingentity.isAlive() && !livingentity.isInvulnerable())
			{
				BlockPos entityPos = livingentity.getPosition();
				BlockPos spirePos = new BlockPos(message.sX, message.sY, message.sZ);
				
				double xDif = entityPos.getX() - spirePos.getX();
				double yDif = spirePos.getY() - entityPos.getY();
				double zDif = entityPos.getZ() - spirePos.getZ();
				
				for(double i = 0; i < 16; i++)
				{
					worldIn.addParticle(ParticleTypes.ENCHANTED_HIT, spirePos.getX() + ((xDif / 16) * i) + 0.5, spirePos.getY() + ((yDif / 16) * i) + 2.0 - (i * 0.05), spirePos.getZ() + ((zDif / 16) * i) + 0.5, 0, 0, 0);
				}
			}
		}
	}
	
	@Override
	public void displayExpionicAbominationParticles(ServerPlayerEntity sender, SToCExpionicTeleportParticlePacket message) 
	{
		World worldIn = Minecraft.getInstance().world;
		
		for(double x = message.sX - 0.5; x <= message.sX + 0.5; x = x + Math.random() / 1.2)
			for(double y = message.sY; y <= message.sY + 3; y = y + Math.random() / 1.2)
				for(double z = message.sZ -0.5; z <= message.sZ + 0.5; z = z + Math.random() / 1.2)
				{
					if(message.color == 0) worldIn.addParticle(new BlockParticleData(ParticleTypes.FALLING_DUST, Blocks.YELLOW_SHULKER_BOX.getDefaultState()), x, y, z, 0.0D, 0.0D, 0.0D);
					if(message.color == 1) worldIn.addParticle(new BlockParticleData(ParticleTypes.FALLING_DUST, ModBlocks.nihilion_block.getDefaultState()), x, y, z, 0.0D, 0.0D, 0.0D);
					if(message.color == 2) worldIn.addParticle(new BlockParticleData(ParticleTypes.FALLING_DUST, ModBlocks.expion_block.getDefaultState()), x, y, z, 0.0D, 0.0D, 0.0D);
				}
	}
}
