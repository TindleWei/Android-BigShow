package com.ml.bigshow.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ml.bigshow.BaseApplication;
import com.ml.bigshow.R;
import com.ml.bigshow.cc.ViewHolder;

public class EditViewHolder {
	
	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dp2px(float dpValue) {  
        final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    } 
	
	public static View getFirstPage(Context mContext, ViewHolder viewHolder) {

		int id_1 = 32901;
		int id_2 = 32902;

		RelativeLayout layout_outer = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams out_lp = new RelativeLayout.LayoutParams(
				-1, -1);
		layout_outer.setLayoutParams(out_lp);

		layout_outer.setFocusable(true);
		layout_outer.setFocusableInTouchMode(true);

		RelativeLayout rlayout_1 = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(-2,
				-2);
		rl_lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		rlayout_1.setPadding(0, 0, 0, dp2px(80));
		rlayout_1.setLayoutParams(rl_lp);
		rlayout_1.setBackgroundColor(Color.WHITE);

		EditText tv_title = new EditText(mContext);
		tv_title.setId(id_1);
		tv_title.setHint("标题");
		tv_title.setTextSize(dp2px(18));
		rlayout_1.addView(tv_title);

		ImageView iv_charactar = new ImageView(mContext);
		rl_lp = new RelativeLayout.LayoutParams(dp2px(80), dp2px(80));
		rl_lp.addRule(RelativeLayout.BELOW, tv_title.getId());
		iv_charactar.setId(id_2);
		iv_charactar.setLayoutParams(rl_lp);
		iv_charactar.setImageResource(R.drawable.ic_chara);
		// iv_charactar.setBackgroundColor(Color.GRAY);
		rlayout_1.addView(iv_charactar);

		EditText tv_charactar = new EditText(mContext);
		rl_lp = new RelativeLayout.LayoutParams(-2, -2);
		rl_lp.addRule(RelativeLayout.ALIGN_BOTTOM, iv_charactar.getId());
		rl_lp.addRule(RelativeLayout.RIGHT_OF, iv_charactar.getId());
		tv_charactar.setHint("名字");
		tv_charactar.setTextSize(dp2px(14));
		rl_lp.setMargins(dp2px(16), 0, 0, 0);
		tv_charactar.setLayoutParams(rl_lp);
		rlayout_1.addView(tv_charactar);

		layout_outer.addView(rlayout_1);
		
		////////////////////////////////
		viewHolder.setParentView(layout_outer);
		ImageView[] imageViews = {iv_charactar};
		TextView[] textViews = {tv_title, tv_charactar};
		viewHolder.setImageViews(imageViews);
		viewHolder.setTextViews(textViews);
//		titleTv = tv_title;
//		nameTv = tv_charactar;

		return layout_outer;
	}

}
