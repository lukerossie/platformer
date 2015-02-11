package androidApp;

import android.graphics.Rect;
import android.util.Log;

public class StaticObject extends DrawableObject
{
	public static StaticObject gsBlock[];
	public static StaticObject lgBlock[];
	public static StaticObject rgBlock[];
	public static StaticObject doorBottom0;
	public static StaticObject doorTop0;
	public static StaticObject crossWoodBlock[];
	public static StaticObject greenKey;
	
	protected StaticObject(Rect dest, Rect src, Rect bounds, int map) 
	{
		setSrc(src);//int left, int top, int right, int bottom (X,Y,X,Y)
		setDest(dest);
		setAdjusted(new Rect());
		setBounds(bounds);
		setMap(map);
		originalDest = HuikEngine.copyRect(dest);
	}
	
	protected static void addBlock(Rect dest, Rect bounds, int map, int objectId)
	{
		Rect src = ObjInfo.getSrc(objectId);
		ObjInfo.increment(objectId);
		StaticObject theObject[] = ObjInfo.getObject(objectId);
		if(theObject == null)
		{ 
			theObject = new StaticObject[1];
			theObject[0] = new StaticObject(dest, src, bounds, map);
			ObjInfo.setObject(theObject, objectId);
		}
		else 
		{
			StaticObject temp[] = new StaticObject[ObjInfo.getAmount(objectId)];
			for(int i = 0; i < ObjInfo.getAmount(objectId)-1; i++)
				temp[i] = theObject[i];
			temp[ObjInfo.getAmount(objectId)-1] = new StaticObject(dest,src,bounds,map);
			theObject = temp;
			ObjInfo.setObject(theObject, objectId);
		}
	}

	protected static void addBlock(Rect dest, int map, int objectId)
	{
		Rect bounds = dest;
		Rect src = ObjInfo.getSrc(objectId);
		ObjInfo.increment(objectId);
		StaticObject theObject[] = ObjInfo.getObject(objectId);
		if(theObject == null)
		{ 
			theObject = new StaticObject[1];
			theObject[0] = new StaticObject(dest, src, bounds, map);
			ObjInfo.setObject(theObject, objectId);
		}
		else 
		{
			StaticObject temp[] = new StaticObject[ObjInfo.getAmount(objectId)];
			for(int i = 0; i < ObjInfo.getAmount(objectId)-1; i++)
				temp[i] = theObject[i];
			temp[ObjInfo.getAmount(objectId)-1] = new StaticObject(dest,src,bounds,map);
			theObject = temp;
			ObjInfo.setObject(theObject, objectId);
		}
	}
}
