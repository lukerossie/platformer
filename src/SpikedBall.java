package androidApp;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class SpikedBall extends DrawableObject
{
	public Matrix matrix;
	public int counter;
	public Rect tranRect;
	private Point origin;
	
	protected SpikedBall(Rect _dest, int _map) 
	{
		setSrc(new Rect(0,0,80,130));//int left, int top, int right, int bottom (X,Y,X,Y)
		setDest(_dest);
		setAdjusted(new Rect());
		setBounds(_dest);
		setMap(_map);
		origin = new Point();
		origin.x = _dest.left + 36;
		origin.y = _dest.top + 120;
		matrix = new Matrix();
	}
	protected void reset()
	{
		counter = 0;
	}
	protected Matrix rotate()
	{
		matrix.reset();
		matrix.setRotate(counter, 36, 120);
		matrix.postTranslate(DR(HuikEngine.player, HuikEngine.width, HuikEngine.height).left, DR(HuikEngine.player, HuikEngine.width, HuikEngine.height).top);
		counter++;
		if(counter == 360)
			counter = 1;
		return matrix;
	}
	protected boolean checkHit()
	{
		if(Rect.intersects(mpts(getCurrentPos(85),20), HuikEngine.player.getDest()) || Rect.intersects(mpts(getCurrentPos(35),10), HuikEngine.player.getDest()))
		{	return true;
		}
		else return false;
	}
	protected Rect mpts(Point p, int lengthToSides)//middlePointToSquare
	{
		Rect r = new Rect(p.x - lengthToSides, p.y - lengthToSides, p.x + lengthToSides, p.y + lengthToSides);
		return r;
	}
	protected Point getCurrentPos(int radius)
	{
		return new Point((int)(origin.x + radius * Math.cos((counter+90)*(Math.PI/180))*-1), (int)(origin.y + radius * Math.sin((counter+90)*(Math.PI/180))*-1));
	}
}
/*		matrix.reset();
		matrix.setRotate(counter, 36, 120);
		matrix.postTranslate(DR(HuikEngine.player, HuikEngine.width, HuikEngine.height).left, DR(HuikEngine.player, HuikEngine.width, HuikEngine.height).top);
		counter++;
		if(counter == 360)
			counter = 1;
		return matrix;
		*/
