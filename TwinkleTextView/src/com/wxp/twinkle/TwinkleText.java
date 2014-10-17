package com.wxp.twinkle;
import android.widget.TextView;
import android.content.Context;
import android.util.*;
import android.graphics.*;
public class TwinkleText extends TextView
{
	private String text;
	private Paint mPaint;
	public TwinkleText(Context context){
		super(context);
		init();
	}
	public TwinkleText(Context context,AttributeSet attrs){
		super(context,attrs);
		init();
	}

	public void init(){
		this.text=getText().toString();
		Log.e("wxp","text="+text);
		mPaint=new Paint();
		mPaint.setColor(Color.RED);
		mPaint.setTextSize(50);
		mPaint.setAntiAlias(true);
		mPaint.setTextAlign(Paint.Align.CENTER);
	}
	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO: Implement this method
	   // super.onDraw(canvas);
     	canvas.drawLine(0,0,100,100,mPaint);
		canvas.drawText(this.text,0,0,mPaint);
		super.onDraw(canvas);
	}
	
	
}
