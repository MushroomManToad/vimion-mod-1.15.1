package mushroommantoad.mmpmod.items.util;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class VimionFoods 
{
	public static final Food VIMION_BERRY = (new Food.Builder()).hunger(2).saturation(3.0F).build();
	public static final Food NECRION_BERRY = (new Food.Builder()).hunger(1).saturation(1.0F).effect(new EffectInstance(Effects.WITHER, 40, 4), 0.2F).build();
	public static final Food SOLARION_BERRY = (new Food.Builder()).hunger(2).saturation(0.3F).effect(new EffectInstance(Effects.NIGHT_VISION, 200, 0), 1.0F).effect(new EffectInstance(Effects.FIRE_RESISTANCE, 10000, 1), 0.05F).build();
	public static final Food NIHILION_BERRY = (new Food.Builder()).hunger(1).saturation(0.1F).effect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 2), 0.1F).build();
	public static final Food EXPION_BERRY = (new Food.Builder()).hunger(5).saturation(0.2F).build();
}
