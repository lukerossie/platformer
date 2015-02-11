package androidApp;

import android.graphics.Rect;

public abstract class DrawableObject
{
	private int map;
	private Rect adjusted;
	private Rect bounds;
	private Rect dest;
	private Rect src;
	boolean override;
	private int overrideX;
	private int overrideY;
	public Rect originalDest;
	
	protected Rect DR(Player player, int sWidth, int sHeight)
	{
		if(!override)
		{
			if(player.getDest().left >= (sWidth/2)-HuikEngine.tileHalved(false))//ltrb
			{
				adjusted.left = dest.left-(player.getDest().left-((sWidth/2)-HuikEngine.tileHalved(false)));
				adjusted.right = dest.right-(player.getDest().right-((sWidth/2)+HuikEngine.tileHalved(true)));
			}
			else
			{
				adjusted.left = dest.left;
				adjusted.right = dest.right;
			}
			if(player.getDest().top+HuikEngine.localFloor <= (sHeight/2)-HuikEngine.tileHalved(false))
			{
				adjusted.top = dest.top+HuikEngine.localFloor-(player.getDest().top+HuikEngine.localFloor-((sHeight/2)-HuikEngine.tileHalved(false)));
				adjusted.bottom = dest.bottom+HuikEngine.localFloor-(player.getDest().bottom+HuikEngine.localFloor-((sHeight/2)+HuikEngine.tileHalved(true)));
			}
			else
			{
				adjusted.top = dest.top+HuikEngine.localFloor; 
				adjusted.bottom = dest.bottom+HuikEngine.localFloor;
			}
			return adjusted;
		}
		if(override)
		{
			return new Rect(overrideX, overrideY, overrideX+HuikEngine.getTileSize(),overrideY+HuikEngine.getTileSize());//ltrb
		}
		return adjusted;
	}
	protected void reset()
	{
		this.setDest(HuikEngine.copyRect(originalDest));
		override = false;
	}
	protected Rect getAdjusted() 
	{
		return adjusted;
	}
	protected Rect getBounds() 
	{
		return bounds;
	}
	protected Rect getDest() 
	{
		return dest;
	}
	public int getMap() 
	{
		return map;
	}
	protected Rect getSrc() 
	{
		return src;
	}
	protected boolean inRange(Player player, int width)
	{
		if(Math.abs(this.dest.left - player.getDest().left) < width) return true;
		else return false;
	}
	protected boolean veryClose(Player player)
	{
		if(Math.abs(this.dest.left - player.getDest().left) < 140) return true;//could optimize for y axis also
		else return false;
	}
	protected void setAdjusted(Rect adjusted) 
	{
		this.adjusted = adjusted;
	}
	protected void setBounds(Rect bounds) 
	{
		this.bounds = bounds;
	}
	protected void setDest(Rect dest) 
	{
		this.dest = dest;
	}
	public void setMap(int map) 
	{
		this.map = map;
	}
	protected void setSrc(Rect src) 
	{
		this.src = src;
	}
	protected void moveX(int value)
	{
		dest.left += value;
		dest.right = dest.left + HuikEngine.getTileSize();
	}
	protected void moveY(int value)
	{
		dest.top += value;
		dest.bottom = dest.top + HuikEngine.getTileSize();
	}	
	protected void setX(int value)
	{
		dest.left = value;
		dest.right = dest.left + HuikEngine.getTileSize();
	}
	protected void setY(int value)
	{
		dest.top = value;
		dest.bottom = dest.top + HuikEngine.getTileSize();
	}	
	protected void overrideDR(int x, int y)
	{
		override = true;
		overrideX = x;
		overrideY = y;
	}
}
