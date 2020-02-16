package mushroommantoad.mmpmod.util;

public class MushroomsUtil 
{
	public static int StaticMinusPlus()
	{
		return Math.random() < 0.5 ? 1 : -1;
	}
	
	public int MinusPlus()
	{
		return Math.random() < 0.5 ? 1 : -1;
	}
	
	public static String nameFixer(String input)
	{
		String output = input;
		char[] chars = output.toCharArray();
		int colon = 0;
		for(int i = 0; i < chars.length; i++){if(chars[i] == ':'){colon = i;}}
		char[] holding = new char[chars.length - (colon + 1)];
		for(int i = colon + 1; i < chars.length; i++){holding[i - (colon + 1)] = chars[i];}
		holding[0] = Character.toUpperCase(holding[0]);
		while(containsChar(holding, '_'))
		{
			int i = getNextCharInstance(holding, '_');
			if(i == -1) break;
			holding[i] = ' ';
			holding[i + 1] = Character.toUpperCase(holding[i + 1]);			
		}
		output = String.valueOf(holding);
		return output;
	}
	
	public static boolean containsChar(char[] input, char...chars)
	{
		for(char ch : input)
		{
			for(char cha : chars)
			{
				if(ch == cha) return true;
			}
		}
		return false;
	}
	
	public static int getNextCharInstance(char[] input, char...chars)
	{
		for(int i = 0; i < input.length; i++)
		{
			for(char ch : chars)
			{
				if(input[i] == ch) return i;
			}
		}
		return -1;
	}
}
