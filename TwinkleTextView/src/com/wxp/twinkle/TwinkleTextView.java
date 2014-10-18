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
	private int width;
    private	Paint mPaint;
	private long mLastTime=System.currentTimeMillis();
	private float mDX=500;
    private	boolean start=false;
	private Shader mShader;
	
	float startX=200;
	float startY=200;
	
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
		mPaint.setColor(Color.RED);
		mPaint.setAntiAlias(true);
		mShader=new LinearGradient(0,0,250,0,new int[] {0xff321d29,0xff321d29,  Color.WHITE}, new float[] {  0, 0.4f, 0.8f},TileMode.MIRROR);
		/**
		 * 第五个参数与第六个数组相对应：
		 * int数组的第一位的数值表示float数组第一位到第二位之间的颜色
		 * int数组的第二位的数值表示float数组第二位到第三位之间的颜色
		 * int数组的第三位就是缝隙的颜色
		 * float数组的范范围是0~1		 */
	    mPaint.setShader(mShader);
		mPaint.setTextSize(50);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		this.width=w;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		long nowTime=System.currentTimeMillis();
		float duration=(nowTime-mLastTime)/6.0f;
		mDX+=duration;

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
		if(mDX>this.width*100){
	    	   mDX=500;
	       }

		mPaint.setTextAlign(Paint.Align.CENTER);
		FontMetrics fm=mPaint.getFontMetrics();
		/*FontMetrics是用于测量文字基准线以及实际的上下高度的类*/
		mShader.setLocalMatrix(matrix);
		canvas.save();
		canvas.drawText("滑动来解锁",startX,-fm.top/5*4,mPaint);
		canvas.restore();
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
