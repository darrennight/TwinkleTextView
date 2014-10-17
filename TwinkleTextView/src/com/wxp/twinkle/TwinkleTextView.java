package com.wxp.twinkle;
import android.view.View;
import android.content.Context;
import android.util.*;
import android.graphics.*;
import android.graphics.Paint.FontMetrics;
import android.graphics.Shader.TileMode;
import android.os.*;
public class TwinkleTextView extends View
{
    private	Paint mPaint;
	private long mLastTime=System.currentTimeMillis();
	private float mDX=500;
    private	boolean start=false;
	
	private Shader mShader;
	
	float startX=200;
	float startY=200;
	//int offSet=1;
	public TwinkleTextView(Context context){
		super(context);
		init();
	}
	
	public TwinkleTextView(Context context,AttributeSet attrs){
		super(context,attrs);
		init();
	}
	
	public void init(){
		mPaint=new Paint();
		mPaint.setColor(Color.WHITE);
		mPaint.setAntiAlias(true);
		mShader=new LinearGradient(0,0,200,0,new int[] { 0xff321d29,0xff76415f, Color.WHITE}, new float[] { 0, 0.4f, 0.8f},TileMode.MIRROR);
	    mPaint.setShader(mShader);
		
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		long nowTime=System.currentTimeMillis();
		float duration=(nowTime-mLastTime)/6.0f;
		mDX+=duration;
		Log.e("wxp","mDex="+mDX);
		Matrix matrix=new Matrix();
		if(start){
			/*
			*使matrix平移
			*因为一开始设置了shader的TileMode为Mirror
		    *TileMode.Mirror这个模式是这样说的:repeat the shader's image horizontally and vertically, alternating mirror images so that adjacent images always seam
	    	*意思就是不断重复 并且相邻的两个之间会有一条夹缝
			*/
			matrix.setTranslate(mDX,0);
			/*最后的效果就是看起来是一个光斑不断的在文字上循环*/
		}else{
			matrix.setTranslate(0,0);
		}
		mPaint.setTextSize(50);
		mPaint.setTextAlign(Paint.Align.CENTER);
		/*FontMetrics fm=mPaint.getFontMetrics();
		FontMetrics是用于测量文字基准线以及实际的上下高度的类*/
		mShader.setLocalMatrix(matrix);
		canvas.drawText("滑动来解锁",startX,startY,mPaint);
		mLastTime=nowTime;
	}

	public void startShow(boolean start){
		this.start=start;
		new Handler().post(new Runnable(){
			public void run(){
				new Handler().postDelayed(this,1);
				invalidate();
			}
		});
		
	}
	
	
}
