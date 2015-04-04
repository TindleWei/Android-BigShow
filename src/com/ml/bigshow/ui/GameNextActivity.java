package com.ml.bigshow.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ml.bigshow.BaseActivity;
import com.ml.bigshow.R;

public class GameNextActivity extends BaseActivity implements OnClickListener {

	TextView tv_question;
	Button btn_a, btn_b, btn_c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_next);

		initViews();
		initData();
	}

	private void initViews() {
		tv_question = $(R.id.tv_question);
		btn_a = $(R.id.btn_a);
		btn_b = $(R.id.btn_b);
		btn_c = $(R.id.btn_c);
	}

	private void initData() {
		btn_a.setOnClickListener(this);
		btn_b.setOnClickListener(this);
		btn_c.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_a:
			startActivity(EndActivity.class);
			this.finish();
			break;
			
		case R.id.btn_b:
			startActivity(EndActivity.class);
			this.finish();
			break;
			
		case R.id.btn_c:
			startActivity(EndActivity.class);
			this.finish();
			break;
		}

	}

}
