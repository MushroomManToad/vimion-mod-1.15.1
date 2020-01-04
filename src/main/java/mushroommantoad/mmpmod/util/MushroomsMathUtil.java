package mushroommantoad.mmpmod.util;

public class MushroomsMathUtil 
{
	public static int StaticMinusPlus()
	{
		return Math.random() < 0.5 ? 1 : -1;
	}
	
	public int MinusPlus()
	{
		return Math.random() < 0.5 ? 1 : -1;
	}
}
