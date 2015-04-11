package com.ml.bigshow.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ml.bigshow.BaseApplication;
import com.ml.bigshow.R;
import com.ml.bigshow.cd.ViewHolder;

public class EditViewHolder extends BaseViewHolder{
	
	public EditViewHolder() {
		super();
	}
	
	
	public static View getFirstPage(Context mContext, ViewHolder viewHolder) {

		int id_1 = 32901;
		int id_2 = 32902;

		RelativeLayout outer = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams outer_lp = new RelativeLayout.LayoutParams(
				-1, -1);
		outer.setLayoutParams(outer_lp);

		outer.setFocusable(true);
		outer.setFocusableInTouchMode(true);

		RelativeLayout inner = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams inner_lp = new RelativeLayout.LayoutParams(-2,
				-2);
		inner_lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		inner.setPadding(0, 0, 0, dp2px(80));
		inner.setLayoutParams(inner_lp);
		inner.setBackgroundColor(Color.WHITE);

		EditText text1 = new EditText(mContext);
		text1.setId(id_1);
		text1.setHint("标题");
		text1.setTextSize(dp2px(18));
		inner.addView(text1);

		ImageView image1 = new ImageView(mContext);
		inner_lp = new RelativeLayout.LayoutParams(dp2px(80), dp2px(80));
		inner_lp.addRule(RelativeLayout.BELOW, text1.getId());
		image1.setId(id_2);
		image1.setLayoutParams(inner_lp);
		image1.setImageResource(R.drawable.ic_chara);
		inner.addView(image1);

		EditText text2 = new EditText(mContext);
		inner_lp = new RelativeLayout.LayoutParams(-2, -2);
		inner_lp.addRule(RelativeLayout.ALIGN_BOTTOM, image1.getId());
		inner_lp.addRule(RelativeLayout.RIGHT_OF, image1.getId());
		text2.setHint("名字");
		text2.setTextSize(dp2px(14));
		inner_lp.setMargins(dp2px(16), 0, 0, 0);
		text2.setLayoutParams(inner_lp);
		inner.addView(text2);

		outer.addView(inner);
		
		////////////////////////////////
		ImageView[] imageViews = {image1};
		TextView[] textViews = {text1, text2};
		
		viewHolder.setImageViews(imageViews);
		viewHolder.setTextViews(textViews);
		viewHolder.setParentView(outer);
		
		return outer;
	}
	
	
	public static View getSecondPage(Context mContext, ViewHolder viewHolder) {

		int id_1 = 32901;

		ScrollView scroll = new ScrollView(mContext);

		RelativeLayout outer = new RelativeLayout(mContext);
		FrameLayout.LayoutParams outer_lp = new FrameLayout.LayoutParams(-1, -1);
		outer_lp.gravity = Gravity.CENTER;
		outer.setLayoutParams(outer_lp);
		outer.setFocusable(true);
		outer.setFocusableInTouchMode(true);

		RelativeLayout inner = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams inner_lp = new RelativeLayout.LayoutParams(
				-2, -2);
		inner_lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		inner.setPadding(0, 0, 0, dp2px(0));
		inner.setLayoutParams(inner_lp);
		inner.setBackgroundColor(Color.YELLOW);

		ImageView image1 = new ImageView(mContext);
		inner_lp = new RelativeLayout.LayoutParams(mWidth - dp2px(32), mWidth
				- dp2px(32));
		image1.setLayoutParams(inner_lp);
		image1.setId(id_1);
		image1.setBackgroundColor(Color.GRAY);
		inner.addView(image1);

		EditText text1 = new EditText(mContext);
		inner_lp = new RelativeLayout.LayoutParams(-2, -2);
		inner_lp.setMargins(dp2px(16), 0, 0, 0);
		inner_lp.addRule(RelativeLayout.BELOW, image1.getId());

		text1.setLayoutParams(inner_lp);
		text1.setHint("故事的内容");
		text1.setMaxLines(5);
		text1.setTextSize(dp2px(18));
		inner.addView(text1);
		
		outer.addView(inner);
		scroll.addView(outer);

		////////////////////////////////
		ImageView[] imageViews = {image1};
		TextView[] textViews = {text1};

		viewHolder.setImageViews(imageViews);
		viewHolder.setTextViews(textViews);
		viewHolder.setParentView(outer);

		return scroll;
	}
}
