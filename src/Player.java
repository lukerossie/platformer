package androidApp;

import android.graphics.Rect;
import android.util.Log;

public class Player 
{	
	private Rect adjusted;
	private Rect dest;
	private int gravity;
	private Rect src;
	public int XVel;
	private int YVel;
	private int width;
	private int height;
	private Rect walk[];
	private Rect state[];
	private int frame;
	private int _state;
	public int runSpeed;
	
	protected Player(Rect _dest, Rect _src)
	{
		width = 72;
		height = 91;//852
		frame = 20;
		_state = 100;
		runSpeed = 7;
		
		walk = new Rect[25];
		{
			walk[0] = new Rect(852+(0*width), 2,  852+(0*width)+width, 2+height);//ltrb
			walk[1] = new Rect(852+(1*width), 2,  852+(1*width)+width, 2+height);//ltrb
			walk[2] = new Rect(852+(2*width), 0,  852+(2*width)+width, height);//ltrb
			walk[3] = new Rect(852+(0*width), 99, 852+(0*width)+width, 99+height);//ltrb
			walk[4] = new Rect(852+(1*width), 99, 852+(1*width)+width, 99+height);//ltrb
			walk[5] = new Rect(852+(2*width), 99, 852+(2*width)+width, 99+height);//ltrb
			
			walk[11] = new Rect(855+(0*width), 293, 855+(0*width)+width, 293+height);//inverted walking 0-5
			walk[12] = new Rect(855+(1*width), 293, 855+(1*width)+width, 293+height);
			walk[13] = new Rect(855+(2*width), 293, 855+(2*width)+width, 293+height);
			walk[14] = new Rect(857+(0*width), 395, 857+(0*width)+width, 395+height);
			walk[15] = new Rect(857+(1*width), 395, 857+(1*width)+width, 395+height);//top is 292
			walk[16] = new Rect(857+(2*width), 392, 857+(2*width)+width, 392+height);
		}
		state = new Rect[30];
		{
			state[0] = new Rect(1290, 93, 1290+width, 93+height);
			state[1] = new Rect(1287, 194, 1287+width, 194+height);
			state[6] = new Rect(852+(3*73),852+(3*73)+width,0,0);//ltrb
			state[7] = new Rect(852+(4*73),852+(4*73)+width,0,0);//ltrb
			state[8] = new Rect(852+(3*73),98,852+(3*73)+width,98+height);//ltrb
			state[9] = new Rect(852+(5*73),852+(5*73)+width,0,0);//ltrb
			state[10] = new Rect(852+(4*73),98,852+(4*73)+width,98+height);//ltrb
			state[20] = new Rect(852+67,196,852+67+width-3,196+height);
			state[21] = new Rect(989,197,989+width,197+height);
		}
		adjusted = new Rect();
		XVel = 0;//move speed
		YVel = 0;//initial yvelocity - inconsequential (changed by gravity)
		src = state[20];
		dest = _dest;
		gravity = 10;
	}
	protected void animate(int xco, int sWidth)
	{
		if(HuikEngine.getBounded())
		{
			if(xco>sWidth/2)//0-5
			{//the images need to be inverted for left facing direction
				if(frame > 5)
				{
					frame = 0;
					_state = 100;
					src = walk[frame];
				}
				else src = walk[frame++];
			}
			else if(xco<sWidth/2)//11-16
			{
				if(frame > 16 || frame < 11)
				{
					frame = 11;
					_state = 100;
					src = walk[frame];
				}
				else src = walk[frame++];
			}
		}
		if(!HuikEngine.getBounded()) //if jumping
		{
			if(xco>sWidth/2)
			{
				_state = 0;
				src = state[_state];
			}
			if(xco<sWidth/2)
			{
				_state = 1;
				src = state[_state];
			}
		}
	}
	protected Rect DR(int sWidth, int sHeight)
	{
		if(dest.left >= (sWidth/2)-HuikEngine.tileHalved(false))//ltrb
		{
			adjusted.left = (sWidth/2)-HuikEngine.tileHalved(false);
			adjusted.right = (sWidth/2)+HuikEngine.tileHalved(true);
		}
		else
		{
			adjusted.left = dest.left;
			adjusted.right = dest.right;
		}
		if(dest.top+HuikEngine.localFloor <= (sHeight/2)-HuikEngine.tileHalved(false))
		{
			adjusted.top = (sHeight/2)-HuikEngine.tileHalved(false);
			adjusted.bottom = (sHeight/2)+HuikEngine.tileHalved(true);
		}
		else
		{
			adjusted.top = dest.top+HuikEngine.localFloor; 
			adjusted.bottom = dest.bottom+HuikEngine.localFloor;
		}
		return adjusted;
	}
	protected Rect getAdjusted() 
	{
		return adjusted;
	}
	protected Rect getDest() 
	{
		return dest;
	}
	protected int getGravity() 
	{
		return gravity;
	}
	protected Rect getSrc() 
	{
		return src;
	}
	protected int getXVel() 
	{
		return XVel;
	}
	protected int getYVel() 
	{
		return YVel;
	}
	protected void groundState(int xco, int sWidth)
	{
		if(HuikEngine.getBounded() && (_state == 1 || _state == 0))
		{
			if(xco<sWidth/2)
				src = state[21];
			if(xco>sWidth/2)
				src = state[20];
		}
	}
	protected void standState(int xco, int sWidth)
	{
		if(HuikEngine.getBounded())
		{
			if(xco<sWidth/2)
				src = state[21];
			if(xco>sWidth/2)
				src = state[20];
		}
	}
	protected void jumpLeft(Player player)//placeholder
	{
		player.YVel-=30;
	}
	protected void jumpRight(Player player)//placeholder
	{
		player.YVel-=30;
	}
	protected void moveSpriteX(int value)
	{
		for(int i = 0; i < Math.abs(value); i++)
		{
			if(!hittingAnything(new Player(new Rect(HuikEngine.player.dest.left+((value>0) ? 1 : -1),HuikEngine.player.dest.top,
					HuikEngine.player.dest.right+((value>0) ? 1 : -1),HuikEngine.player.dest.bottom),new Rect(0,0,0,0))))
			{
				dest.left += value == 0 ? 0 : ((value>0) ? 1 : -1);
				dest.right = dest.left + HuikEngine.getTileSize();
			}
		}
	}
	protected void moveSpriteY(int value)
	{
		for(int i = 0; i < Math.abs(value); i++)
		{
			if(!hittingAnything(new Player(new Rect(HuikEngine.player.dest.left,HuikEngine.player.dest.top+((value>0) ? 1 : -1),
					HuikEngine.player.dest.right,HuikEngine.player.dest.bottom+((value>0) ? 1 : -1)),new Rect(0,0,0,0))))
			{
				dest.top += value == 0 ? 0 : ((value>0) ? 1 : -1);
				dest.bottom = dest.top + HuikEngine.getTileSize();
			}
		}

	}	
	protected boolean hittingAnything(Player player)
	{
		for(int i = 0; i < HuikEngine.NUMOBJECTS; i++) 
		{
			StaticObject object[] = ObjInfo.getObject(i);
			for(int a = 0; a < ObjInfo.getAmount(i); a++)
			{
				if(object[a].veryClose(HuikEngine.player) && HuikEngine.isHere(object[a]))
					if(HuikEngine.bordersIntersect(object[a].getDest(), player.getDest()))
					{
						return true;
					}
			}
		}
		return false;
	}
	protected void setAdjusted(Rect adjusted) 
	{
		this.adjusted = adjusted;
	}
	protected void setDest(Rect dest) 
	{
		this.dest = dest;
	}
	protected void setGravity(int gravity) 
	{
		this.gravity = gravity;
	}
	protected void setSrc(Rect src) 
	{
		this.src = src;
	}
	protected void setX(int value)
	{
		dest.left = value;
		dest.right = value + HuikEngine.getTileSize();
	}
	protected void setXVel(int xVel) 
	{
		XVel = xVel;
	}
	protected void setY(int value)
	{
		dest.top = value;
		dest.bottom = value + HuikEngine.getTileSize();
	}
	protected void setYVel(int yVel)
	{
		YVel = yVel;
	}
}
