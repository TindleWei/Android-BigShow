package com.ml.bigshow.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.ml.bigshow.BaseActivity;
import com.ml.bigshow.R;
import com.ml.bigshow.ui.adapter.SlotListAdapter;
import com.ml.bigshow.widget.xlist.XListView;
import com.ml.bigshow.widget.xlist.XListView.IXListViewListener;

public class SlotListActivity extends BaseActivity implements
		IXListViewListener {

	XListView mListView;
	SlotListAdapter mAdapter;
	ArrayList<String> mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slotlist);

		initViews();
		initData();
	}

	private void initViews() {
		mListView = $(R.id.listView);

		mListView.setXListViewListener(this);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(false);

		View headView = LayoutInflater.from(mContext).inflate(
				R.layout.header_slotlist, null);
		ImageView head_iv_character = $(headView, R.id.iv_character);
		TextView head_tv_character = $(headView, R.id.tv_character);
		TextView head_tv_title = $(headView, R.id.tv_title);
		mListView.addHeaderView(headView);

		LinearLayout footView = new LinearLayout(mContext);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		footView.setLayoutParams(lp);
		TextView foot_tv_add = new TextView(mContext);
		foot_tv_add.setLayoutParams(lp);
		foot_tv_add.setGravity(Gravity.CENTER);
		foot_tv_add.setPadding(20, 20, 20, 20);
		foot_tv_add.setText(" + ");
		foot_tv_add.setTextSize(24);
		footView.addView(foot_tv_add);
		mListView.addFooterView(footView);

	}

	private void initData() {

		mList = new ArrayList<String>();
		mAdapter = new SlotListAdapter(mContext, mList);
		mListView.setAdapter(mAdapter);

		getSlotList();
	}

	private void getSlotList() {
		mList = new ArrayList<String>();
		mList.add("0");
		mList.add("0");
		mList.add("0");
		mList.add("0");
		mList.add("0");

		mAdapter = new SlotListAdapter(mContext, mList);
		mListView.setAdapter(mAdapter);

	}

	@Override
	public void onRefresh() {
		getSlotList();

	}

	@Override
	public void onLoadMore() {

	}

}
