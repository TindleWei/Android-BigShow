package com.ml.bigshow.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ml.bigshow.BaseActivity;
import com.ml.bigshow.R;
import com.ml.bigshow.entity.User;

public class MainActivity extends BaseActivity implements OnClickListener{
	
	TextView tv_name;
	Button btn_my, btn_find;
	Button btn_start;
	ImageView iv_character;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initViews();
		initData();
	}

	private void initViews() {
		tv_name = $(R.id.tv_name); 
		btn_my = $(R.id.btn_my);
		btn_find = $(R.id.btn_find);
		btn_start = $(R.id.btn_start);
		iv_character = $(R.id.iv_character);
	}

	private void initData() {
		
		btn_find.setOnClickListener(this);
		btn_my.setOnClickListener(this);
		btn_start.setOnClickListener(this);
		
		User.getUser();
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_start:
			
			startActivity(GameActivity.class);
			break;
			
		case R.id.btn_my:
			startActivity(SlotMyActivity.class);
			break;
			
		case R.id.btn_find:
			startActivity(FindListActivity.class);
			break;
		}
		
	}

}
