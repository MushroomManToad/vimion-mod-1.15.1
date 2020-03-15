package mushroommantoad.mmpmod.entities.spectral.soul;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mushroommantoad.mmpmod.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

@OnlyIn(Dist.CLIENT)
public class SpectralSoulRenderer extends MobRenderer<SpectralSoulEntity, SpectralSoulModel>
{
	LivingEntity intEnt;
	EntityModel<LivingEntity> intMod;
	
	public SpectralSoulRenderer(EntityRendererManager manager) 
	{
		super(manager, new SpectralSoulModel(), 0f);
	}

	@Override
	public ResourceLocation getEntityTexture(SpectralSoulEntity entity) 
	{
		return ModEntities.location("textures/entity/spectral/soul.png");
	}
	
	@Override
	public void render(SpectralSoulEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) 
	{
		if(intEnt == null) intEnt = this.getIntEnt(entityIn);	
		if(intMod == null) intMod = this.getInternalModel(entityIn);	
		
		if(entityIn.ticksExisted > 1)
		{
			//System.out.println(entityIn.getRender() + ", " + intEnt.getEntityString());
			if(!entityIn.getRender().equalsIgnoreCase(intEnt.getEntityString())) 
			{
				intEnt = this.getIntEnt(entityIn);
				intMod = this.getInternalModel(entityIn);	
			}
		}
		
		matrixStackIn.push();
		this.entityModel.swingProgress = this.getSwingProgress(entityIn, partialTicks);

		boolean shouldSit = entityIn.isPassenger() && (entityIn.getRidingEntity() != null && entityIn.getRidingEntity().shouldRiderSit());
		this.entityModel.isSitting = shouldSit;
		this.entityModel.isChild = entityIn.isChild();
		float f = MathHelper.interpolateAngle(partialTicks, entityIn.prevRenderYawOffset, entityIn.renderYawOffset);
		float f1 = MathHelper.interpolateAngle(partialTicks, entityIn.prevRotationYawHead, entityIn.rotationYawHead);
		float f2 = f1 - f;
		if (shouldSit && entityIn.getRidingEntity() instanceof LivingEntity) 
		{
			LivingEntity livingentity = (LivingEntity)entityIn.getRidingEntity();
			f = MathHelper.interpolateAngle(partialTicks, livingentity.prevRenderYawOffset, livingentity.renderYawOffset);
			f2 = f1 - f;
			float f3 = MathHelper.wrapDegrees(f2);
			if (f3 < -85.0F){f3 = -85.0F;}

			if (f3 >= 85.0F) {f3 = 85.0F;}

			f = f1 - f3;
			if (f3 * f3 > 2500.0F) {f += f3 * 0.2F;}

			f2 = f1 - f;
		}

		float f6 = MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch);
		if (entityIn.getPose() == Pose.SLEEPING) {
			Direction direction = entityIn.getBedDirection();
			if (direction != null) {
				float f4 = entityIn.getEyeHeight(Pose.STANDING) - 0.1F;
				matrixStackIn.translate((double)((float)(-direction.getXOffset()) * f4), 0.0D, (double)((float)(-direction.getZOffset()) * f4));
			}
		}

		float f7 = this.handleRotationFloat(entityIn, partialTicks);
		this.applyRotations(entityIn, matrixStackIn, f7, f, partialTicks);
		matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
		this.preRenderCallback(entityIn, matrixStackIn, partialTicks);
		matrixStackIn.translate(0.0D, (double)-1.501F, 0.0D);
		float f8 = 0.0F;
		float f5 = 0.0F;
		if (!shouldSit && entityIn.isAlive()) {
			f8 = MathHelper.lerp(partialTicks, entityIn.prevLimbSwingAmount, entityIn.limbSwingAmount);
			f5 = entityIn.limbSwing - entityIn.limbSwingAmount * (1.0F - partialTicks);
			if (entityIn.isChild()) {f5 *= 3.0F;}

			if (f8 > 1.0F) {f8 = 1.0F;}
		}

		intMod.setLivingAnimations(intEnt, f5, f8, partialTicks);
		intMod.setRotationAngles(intEnt, f5, f8, f7, f2, f6);
		boolean flag = this.isVisible(entityIn);
		boolean flag1 = !flag && !entityIn.isInvisibleToPlayer(Minecraft.getInstance().player);
		RenderType rendertype = this.func_230042_a_(entityIn, flag, flag1);
		if (rendertype != null) {
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(rendertype);
			int i = getPackedOverlay(entityIn, this.getOverlayProgress(entityIn, partialTicks));
			intMod.render(matrixStackIn, ivertexbuilder, packedLightIn, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
		}

		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	public EntityModel<LivingEntity> getInternalModel(SpectralSoulEntity entityIn)
	{
		for (EntityRenderer<?> renderer : Minecraft.getInstance().getRenderManager().renderers.values()) 
		{
			if(renderer == Minecraft.getInstance().getRenderManager().getRenderer(intEnt))
			{
				@SuppressWarnings("unchecked")
				EntityModel<LivingEntity> m = (EntityModel<LivingEntity>) ((LivingRenderer<?, ?>)renderer).getEntityModel();
				return m;
			}
		}
		return null;
	}
	
	public LivingEntity getIntEnt(SpectralSoulEntity entity)
	{
		String s = entity.getRender();
		for(EntityType<?> t : ForgeRegistries.ENTITIES)
		{
			if(s.equalsIgnoreCase(t.getRegistryName().toString()))
			{
				return (LivingEntity) t.create(entity.world);
			}
		}
		return new CreeperEntity(EntityType.CREEPER, entity.world);
	}
	
	@Override
	protected boolean isVisible(SpectralSoulEntity entity) 
	{
		return false;
	}
}
