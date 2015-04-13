package com.ml.cd.view;

import com.ml.bigshow.BaseApplication;

import android.content.Context;
import android.widget.TextView;

public class CTextView extends TextView{
	
	public static final int NORMAL = 0;
	public static final int SMALL = 1;
	public static final int LARGE = 2;
	public static final int SMALLER = 3;
	public static final int LARGER = 4;

	public CTextView(Context context) {
		super(context);
		init(0);
	}
	
	public CTextView(Context context, int type){
		super(context);
		init(type);
	}
	
	public void init(int type){
		
		switch(type){
		case NORMAL:
			createNormalText();
			break;
		case SMALL:
			createSmallText();
			break;
		case LARGE:
			createLargeText();
			break;
		case SMALLER:
			createSmallerText();
			break;
		case LARGER:
			createLargerText();
			break;
		default:
			createNormalText();
			break;
		}
	}

	private void createNormalText() {
		this.setTextSize(dp2px(16));
	}

	private void createSmallText() {
		this.setTextSize(dp2px(14));
	}
	
	private void createLargeText() {
		this.setTextSize(dp2px(20));
	}

	private void createSmallerText() {
		this.setTextSize(dp2px(12));
	}

	private void createLargerText() {
		this.setTextSize(dp2px(24));
	}

	
	public static int dp2px(float dpValue) {  
        final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    } 

}
