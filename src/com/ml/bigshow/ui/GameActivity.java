package com.ml.bigshow.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ml.bigshow.BaseActivity;
import com.ml.bigshow.R;

public class GameActivity extends BaseActivity implements OnClickListener {

	ImageView iv_character;
	TextView tv_title, tv_character;
	TextView tv_content;
	ImageView iv_slot;
	ImageView iv_next;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		initViews();
		initData();
	}

	private void initViews() {
		iv_character = $(R.id.iv_character);
		tv_title = $(R.id.tv_title);
		tv_character = $(R.id.tv_character);
		tv_content = $(R.id.tv_content);
		iv_slot = $(R.id.iv_slot);
		iv_next = $(R.id.iv_next);
	}

	private void initData() {
		iv_next.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_next:

			startActivity(GameNextActivity.class);
			this.finish();

			break;
		}

	}

}
