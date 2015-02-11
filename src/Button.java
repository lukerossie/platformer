package androidApp;

import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

public class Button 
{
	public static VectorB oldDown = new VectorB();
	public static VectorP oldPoint = new VectorP();
	public int id;
	public boolean released;
	public boolean pressed;
	public static boolean unPoint = true;
	
	public Button(int id)
	{
		this.id = id;
		released = false;
		pressed = false;
	}
	public Button(boolean _released, boolean _pressed)
	{
		released = _released;
		pressed = _pressed;
	}
	public boolean isReleased() 
	{
		return released;
	}
	public void setReleased(boolean released) 
	{
		this.released = released;
	}
	public boolean isPressed() 
	{
		return pressed;
	}
	public void setPressed(boolean pressed) 
	{
		this.pressed = pressed;
	}
	public Button checkState(Rect buttonRect)
	{
		PointF oldPointC = oldPoint.get(id); 
		Boolean oldDownC = oldDown.get(id);
		released = false;
		pressed = false;
		if(HuikEngine.tDown) 
			if(HuikEngine.tX > buttonRect.left && HuikEngine.tX < buttonRect.right && HuikEngine.tY > buttonRect.top && HuikEngine.tY < buttonRect.bottom)
				pressed = true;
		if(!unPoint)
		{
			Log.d(Boolean.toString(HuikEngine.tDown), Boolean.toString(oldDownC));
			if((HuikEngine.tDown == false && oldDownC == true))
			{
				Log.d("unPoint", "asd2");
				if(oldPointC.x > buttonRect.left && oldPointC.x < buttonRect.right && oldPointC.y > buttonRect.top && oldPointC.y < buttonRect.bottom)
				{
					released = true;
				}
			}
			
		}
		if(HuikEngine.tDown) 
		{
			oldPoint.set(id, new PointF(HuikEngine.tX,HuikEngine.tY));
			unPoint = false;
		}
		oldDown.set(id, Boolean.valueOf(HuikEngine.tDown));
		return new Button(released, pressed);
	}
}


