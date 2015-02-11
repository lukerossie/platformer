package androidApp;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;

public class Spike extends DrawableObject
{
	protected Spike(Rect _dest, int _map)
	{
		setSrc(new Rect(0,0,80,130));//int left, int top, int right, int bottom (X,Y,X,Y)
		setDest(_dest);
		setAdjusted(new Rect());
		setBounds(_dest);
		setMap(_map);
	}
	protected boolean checkHit()
	{
		if(Rect.intersects(getDest(), HuikEngine.player.getDest()))
		{	return true;
		}
		else return false;
	}
}
