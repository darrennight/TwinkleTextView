package com.wxp.twinkle;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
{
	TwinkleTextView ttv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	/*	ttv=(TwinkleTextView)findViewById(R.id.unlock);
		ttv.startShow(true);*/
    }
}
