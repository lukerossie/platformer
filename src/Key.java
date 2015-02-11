package androidApp;

import android.graphics.Paint;
import android.util.Log;

public class Key 
{
	private int alpha;
	private boolean increasing;
	private int size;
	public Paint keyPaint;
	public boolean finished;
	public StaticObject realKey;
	public boolean acquired;
	private boolean fDef;
	private double m;
	private int xp;
	private int yp;
	private int newx;
	private int newy;
	
	public Key()
	{
		fDef = false;
		keyPaint = new Paint();
		alpha = 0;
		finished = true;
		acquired = false;
		increasing = true;
		keyPaint.setAlpha(alpha);
	}
	public Key(StaticObject _realKey)
	{
		keyPaint = new Paint();
		alpha = 0;
		finished = true;
		increasing = true;
		keyPaint.setAlpha(alpha);
		realKey = _realKey;
	}
	public void reset()
	{
		fDef = false;
		alpha = 0;
		finished = true;
		acquired = false;
		increasing = true;
		realKey.reset();
	}
	protected int stepLine(int x,int y)
	{
		if(!fDef) 
		{
			m = (double)y/x;
			newx = x;
			fDef = true;
		}
		newx--;
		Log.d("x",Integer.toString(y));
	
		return (int)(m*newx);
	}
	protected void blimp()
	{
		for(int i = 0; i < 5; i++)
		{
			if(increasing && alpha < 255)
			{
				alpha++;
				finished = false;
			}
			else if(increasing && alpha == 255)
			{
				increasing = false;
				finished = false;
			}
			if(!increasing && alpha > 0)
			{
				alpha--;
				finished = false;
			}
			else if(!increasing && alpha == 0)
			{
				increasing = true;
				finished = true;
				break;
			}
			keyPaint.setAlpha(alpha);
		}
	}
	protected void move()
	{
		for(int i = 0; i < 5; i++)
		{
			if(!acquired)
			{
				newy = stepLine(realKey.getAdjusted().left,realKey.getAdjusted().top);
				realKey.overrideDR(newx,newy);
			}
			if(newx == 0 || newy == 0) acquired = true;
		}
		
	}
}
