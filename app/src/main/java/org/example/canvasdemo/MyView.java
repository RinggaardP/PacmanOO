package org.example.canvasdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MyView extends View{

	Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.ghost_80);
	Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pacman);
	Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.gold_coin);
    //The coordinates for our dear pacman: (0,0) is the top-left corner
	int pacx = 0;
    int pacy = 0;
	int ghostx = 200;
	int ghosty = 200;
	Canvas canvas;
    int h,w;//used for storing our height and width
	GoldCoin goldCoin;
	ArrayList<GoldCoin> coins = new ArrayList<GoldCoin>();
    boolean newGame = true;
    int score = 0;

	public void setGoldCoin(GoldCoin g) {
		goldCoin = g;
	}

	public void moveRight(int x)
    {
    	//still within our boundaries?
    	if (pacx+x+bitmap.getWidth()<w)
    		pacx=pacx+x;
    	invalidate(); //redraw everything - this ensures onDraw() is called.
    }
	public void moveLeft(int x)
	{
		//still within our boundaries?
		if (pacx>0)
			pacx=pacx-x;
		invalidate(); //redraw everything - this ensures onDraw() is called.
	}
	public void moveUp(int y)
	{
		//still within our boundaries?
		if (pacy>0)
			pacy=pacy-y;
		invalidate(); //redraw everything - this ensures onDraw() is called.
	}
	public void moveDown(int y)
	{
		//still within our boundaries?
		if (pacy+y+bitmap.getHeight()<h)
			pacy=pacy+y;
		invalidate(); //redraw everything - this ensures onDraw() is called.
	}


	public void moveRightG(int x)
	{
		//still within our boundaries?
		if (ghostx+x+bitmap3.getWidth()< w)
		{ghostx=ghostx-x;}
		invalidate();
	}
	public void moveLeftG(int x)
	{
		//still within our boundaries?
		if (ghostx>0)
		{ghostx=ghostx-x;}
		invalidate();
	}
	public void moveUpG(int y)
	{
		//still within our boundaries?
		if (ghosty>0)
		{ghosty=ghosty-y;}
		invalidate();
	}
	public void moveDownG(int y)
	{
		//still within our boundaries?
		if (ghosty+y+bitmap3.getHeight()< h)
		{ghosty=ghosty+y;}
		invalidate();
	}


	/* The next 3 constructors are needed for the Android view system,
	when we have a custom view.
	 */
	public MyView(Context context) {
		super(context);
		
	}
	
	public MyView(Context context, AttributeSet attrs) {
		super(context,attrs);
	}
	
	
	public MyView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context,attrs,defStyleAttr);
	}

	//In the onDraw we put all our code that should be
	//drawn whenever we update the screen.
	@Override
	protected void onDraw(Canvas canvas) {  
		//Here we get the height and weight
		h = canvas.getHeight();
		w = canvas.getWidth();
		System.out.println("h = "+h+", w = "+w);
		//Making a new paint object
		Paint paint = new Paint();

		this.canvas = canvas;
		//setting the color
		paint.setColor(Color.RED);
		canvas.drawColor(Color.WHITE); //clear entire canvas to white color
		//drawing a line from (0,0) -> (300,200)
		//canvas.drawLine(0,0,300,200,paint);
		paint.setColor(Color.GREEN);
		//canvas.drawLine(0,200,300,0,paint);
		
		//setting the color using the format: Transparency, Red, Green, Blue
		paint.setColor(0xff000099);
		
		//drawing a circle with radius 20, and center in (100,100) 
		//canvas.drawCircle(100,100,20,paint);


		canvas.drawBitmap(bitmap, pacx, pacy, paint);
		canvas.drawBitmap(bitmap3, ghostx, ghosty, paint);

		if (newGame)  {
			createCoins();
		 newGame= false;
		}

		for(GoldCoin g : coins) {
			if(calcDist(g)>40 && !g.isTaken()) {
				canvas.drawBitmap(bitmap2, g.getCoinx(), g.getCoiny(), paint);
		} else {
				if (!g.isTaken())
				{
					g.setTaken(true);
					score ++;
				}
		}

			System.out.println(g.isTaken());
		} // for loop ends

		super.onDraw(canvas);
		System.out.println(score);

	}

	public double calcDist(GoldCoin c) {
		double distance = Math.hypot((pacx + (bitmap.getWidth()/2))- (c.getCoinx() + (bitmap2.getWidth()/2)), (pacy + (bitmap.getHeight()/2))- (c.getCoiny() + ((bitmap2.getHeight()/2))));
		return distance;
	}

	public double calcDist2() {
		double distance = Math.hypot((pacx + (bitmap.getWidth()/2))- (ghostx + (bitmap3.getWidth()/2)), (pacy + (bitmap.getHeight()/2))- (ghosty + ((bitmap3.getHeight()/2))));
		return distance;
	}

	public ArrayList<GoldCoin> getCoins() {
		return coins;
	}

	public void createCoins() {

		Random rnd = new Random();
		rnd.setSeed(System.currentTimeMillis());

		for(int i = 0; i < 10;i++) {
			int nh = rnd.nextInt(h-60) + 1;
			int nw = rnd.nextInt(w-60) + 1;

			System.out.println(nh + ", " + nw);

			GoldCoin c = new GoldCoin(nw,nh,40,40, false);

			setGoldCoin(c);

			coins.add(c);

		}
	}
	public int getH() {return h;}
	public int getW() {return w;}

	public Canvas getCanvas() {
		return canvas;
	}

	public int getScore() {
        return score;
    }
    public void resetScore() {
		score = 0;
	}


}
