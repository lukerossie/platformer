package androidApp;

import android.graphics.Rect;

public class MobilePlatform extends DrawableObject 
{
	private boolean start;
	private double factor;
	private boolean occupied;
	private Path p;
	private Rect defaultPos;
	
	MobilePlatform(Path _p, int dest[], int _map)
	{
		factor = 1.35;
		setSrc(HuikEngine.createSrcRect(5,4));
		setAdjusted(new Rect());
		setBounds(HuikEngine.createRect(dest[0],dest[1],factor));
		setMap(_map);
		setDest(HuikEngine.createRect(dest[0],dest[1]));
		p = _p;
		defaultPos = HuikEngine.createRect(dest[0],dest[1]);
	}
	protected void reset()
	{
		occupied = false;
		start = false;
		this.setDest(HuikEngine.copyRect(defaultPos));
		p.curDist = 0;
		p.curStep = 0;
		
	}
	public void move(int num)
	{
		{//boundaries
			Rect a = this.getBounds();
			Rect p = HuikEngine.player.getDest();
			Player player = HuikEngine.player;
			occupied = false;
			if(Rect.intersects(a,p))//set occupied here
			{
				int d2l = (p.right - a.left);
				int d2t = (p.bottom - a.top);
				int d2r = (a.right - p.left);
				int d2b = (a.bottom - p.top);
				if(d2l < d2t && d2l < d2r && d2l < d2b)
				{
					player.setX(a.left-69);
				}
				else if(d2t < d2l && d2t < d2r && d2t < d2b)
				{
					player.setY(a.top-69);
					occupied = true;
					HuikEngine.bounded = true;
				}
				else if(d2r < d2l && d2r < d2t && d2r < d2b)
				{
					player.setX(a.right);
				}
				else if(d2b < d2t && d2b < d2l && d2b < d2r)
				{
					player.setY(a.bottom);
					player.setYVel(0);
				}
			}
		}
		
		if(occupied) start = true;
		if(start)
		{
			if(p.curStep != p.steps)
			{
				for(int i = 0; i < num; i++)
				{
					if(p.curDist == Math.abs(p.distance[p.curStep])-1)
					{
						p.curStep++;
						p.curDist = 0;
						return;				
					}
					if(p.X[p.curStep] == true)
					{
						this.moveX((p.distance[p.curStep] < 0 ? -1 : 1));
						if(occupied) HuikEngine.player.moveSpriteX((p.distance[p.curStep] < 0 ? -1 : 1));
						p.curDist++;
					}
					else
					{
						this.moveY((p.distance[p.curStep] < 0 ? -1 : 1));
						if(occupied) HuikEngine.player.moveSpriteY((p.distance[p.curStep] < 0 ? -1 : 1));
						p.curDist++;
					}
				}
			}
			else if(p.curStep == p.steps)
			{
				start = false;
				p.curStep = 0;
				p.curDist = 0;
			}
		}
	}
	@Override
	public Rect getBounds()
	{
		return new Rect(this.getDest().left,(int)(this.getDest().top+(69/factor)),this.getDest().right,this.getDest().bottom);
	}
}
class Path
{
	int curDist;
	int curStep;
	int steps;
	int distance[];//[numSteps] -CAN BE NEGATIVE...(so its not really distance but ok)-
	boolean X[];//[numSteps], true if moving x axis false if moving y axis
	public Path(int _steps, int _distance[], boolean _X[])
	{
		steps = _steps;
		distance = _distance;
		X = _X;
		curDist = 0;
		curStep = 0;
	}
}
