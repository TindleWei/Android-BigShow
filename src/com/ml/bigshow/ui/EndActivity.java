package com.ml.bigshow.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ml.bigshow.BaseActivity;
import com.ml.bigshow.R;

public class EndActivity extends BaseActivity implements OnClickListener {

	TextView tv_title;
	ImageView iv_center;
	TextView tv_descrip;
	ImageView iv_back;
	ImageView iv_next;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end);

		initViews();
		initData();
	}

	private void initViews() {
		tv_title = $(R.id.tv_title);
		iv_center = $(R.id.iv_center);
		tv_descrip = $(R.id.tv_descrip);
		iv_back = $(R.id.iv_back);
		iv_next = $(R.id.iv_next);
	}

	private void initData() {
		iv_back.setOnClickListener(this);
		iv_next.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:

			startActivity(MainActivity.class);
			break;

		case R.id.iv_next:
			
			startActivity(GameActivity.class);
			this.finish();
			break;
		}
	}
}
