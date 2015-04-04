package com.ml.bigshow.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class SimpleListItem {

	private ViewGroup viewgroup;

	private ImageView[] images;

	private TextView[] texts;

	/* 获取屏幕长，宽 */
	protected int mHeight, mWidth;

	private Context mContext = null;

	private int mType;

	public SimpleListItem(Context context, int type) {
		mContext = context;
		mType = type;
		initTypeView();

	}

	private void initTypeView() {

		DisplayMetrics metric = new DisplayMetrics();

		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		mHeight = metric.heightPixels;
		mWidth = metric.widthPixels;
	}

	public View getContentView() {

		if (mType == 1) {
			// 纯文字
			return getType1Page();
		} else
			return null;

	}

	public View getType1Page() {

		int id_left = 32902;
		int id_middle = 32903;
		int id_right = 32904;

		// 三个TextView
		RelativeLayout.LayoutParams lp;

		RelativeLayout layout_main = new RelativeLayout(mContext);
		RelativeLayout layout_left = new RelativeLayout(mContext);
		RelativeLayout layout_middle = new RelativeLayout(mContext);
		RelativeLayout layout_right = new RelativeLayout(mContext);

		lp = new RelativeLayout.LayoutParams(-1, -2);
		layout_main.setLayoutParams(lp);
		layout_main.setPadding(dp2px(16), dp2px(16), dp2px(16), dp2px(16));
		layout_main.setMinimumHeight(dp2px(60));

		lp = new RelativeLayout.LayoutParams(-2, -2);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		layout_left.setLayoutParams(lp);
		layout_left.setId(id_left);

		lp = new RelativeLayout.LayoutParams(-2, -2);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		layout_right.setLayoutParams(lp);
		layout_right.setId(id_right);

		lp = new RelativeLayout.LayoutParams(-1, -2);
		lp.addRule(RelativeLayout.RIGHT_OF, layout_left.getId());
		lp.addRule(RelativeLayout.LEFT_OF, layout_right.getId());
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		layout_middle.setLayoutParams(lp);
		layout_middle.setId(id_middle);

		// ////////////////////////////////////////////////////////
		// 接下来写子控件
		// ////////////////////////////////////////////////////////

		// 1. or A.
		TextView text_left = new TextView(mContext);
		lp = new RelativeLayout.LayoutParams(-2, -2);
		text_left.setLayoutParams(lp);
		text_left.setTextSize(34);
		layout_left.addView(text_left);

		TextView text_mid1 = new EditText(mContext);
		lp = new RelativeLayout.LayoutParams(-2, -2);
		text_mid1.setLayoutParams(lp);
		text_mid1.setTextSize(14);
		text_mid1.setHint("slot to end");
		text_mid1.setMaxHeight(2);
		layout_middle.addView(text_left);

		TextView text_right1 = new Button(mContext);
		lp = new RelativeLayout.LayoutParams(-2, -2);
		text_right1.setPadding(dp2px(8), dp2px(8), dp2px(8), dp2px(8));
		text_right1.setText("End");
		text_right1.setBackgroundColor(Color.RED);
		text_right1.setTextColor(Color.WHITE);
		layout_right.addView(text_right1);

		// ////////////////////////////////////////////////////////
		// 闭合布局
		// ////////////////////////////////////////////////////////

		layout_main.addView(layout_left);
		layout_main.addView(layout_right);
		layout_main.addView(layout_middle);
		
		List<View> textList = new ArrayList<View>();
		textList.add(text_left);
		textList.add(text_mid1);
		textList.add(text_right1);
		
		texts = (TextView[]) textList.toArray();
		viewgroup = layout_main;

		return layout_main;
	}
	
	/**
	 * 
	 */
	public TextView[] getTexts(){
		return texts;
	}
	
	public ViewGroup getMainView(){
		return viewgroup;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public int dp2px(float dpValue) {
		final float scale = mContext.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

}
