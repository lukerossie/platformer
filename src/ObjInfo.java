package androidApp;

import android.graphics.Rect;


public class ObjInfo
{
	public static int gsBlockN = 0;
	public static int lgBlockN = 0;
	public static int rgBlockN = 0; 
	public static int crossWoodBlockN = 0; 
	
	public static StaticObject[] getObject(int objectId)
	{
		switch(objectId)
		{
		case HuikEngine.GSBLOCK:
			return StaticObject.gsBlock;
		case HuikEngine.LGBLOCK:
			return StaticObject.lgBlock;
		case HuikEngine.RGBLOCK:
			return StaticObject.rgBlock;
		case HuikEngine.CROSSBLOCK:
			return StaticObject.crossWoodBlock;
			default:
				return null;
		}	
	}
	public static void setObject(StaticObject object[], int objectId)
	{
		switch(objectId)
		{
		case HuikEngine.GSBLOCK:
			StaticObject.gsBlock = object;
			break;
		case HuikEngine.LGBLOCK:
			StaticObject.lgBlock = object;
			break;
		case HuikEngine.RGBLOCK:
			StaticObject.rgBlock = object;
			break;
		case HuikEngine.CROSSBLOCK:
			StaticObject.crossWoodBlock = object;
			break;
		}	
	}
	public static Rect getSrc(int objectId)
	{
		switch(objectId)
		{
		case HuikEngine.GSBLOCK:
			return HuikEngine.gsBlockSrc;
		case HuikEngine.LGBLOCK:
			return HuikEngine.lgBlockSrc;
		case HuikEngine.RGBLOCK:
			return HuikEngine.rgBlockSrc;
		case HuikEngine.CROSSBLOCK:
			return HuikEngine.crossWoodBlockSrc;
			default:
				return null;
		}
	}
	public static int getAmount(int objectId)
	{
		switch(objectId)
		{
		case HuikEngine.GSBLOCK:
			return gsBlockN;
		case HuikEngine.LGBLOCK:
			return lgBlockN;
		case HuikEngine.RGBLOCK:
			return rgBlockN;
		case HuikEngine.CROSSBLOCK:
			return crossWoodBlockN;
			default:
				return 42;
		}	
	}
	public static void increment(int objectId)
	{
		switch(objectId)
		{
		case HuikEngine.GSBLOCK:
			gsBlockN++;
			break;
		case HuikEngine.LGBLOCK:
			lgBlockN++;
			break;
		case HuikEngine.RGBLOCK:
			rgBlockN++;
			break;
		case HuikEngine.CROSSBLOCK:
			crossWoodBlockN++;
			break;
		}	
	}
}
