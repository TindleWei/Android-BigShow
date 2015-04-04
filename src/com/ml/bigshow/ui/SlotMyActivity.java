package com.ml.bigshow.ui;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.ml.bigshow.BaseActivity;
import com.ml.bigshow.R;
import com.ml.bigshow.ui.adapter.FindListAdapter;
import com.ml.bigshow.widget.xlist.XListView;
import com.ml.bigshow.widget.xlist.XListView.IXListViewListener;

public class SlotMyActivity extends BaseActivity implements OnClickListener,
		IXListViewListener {

	private TextView tv_updated;
	private TextView tv_not_update;
	private ViewPager view_pager;
	private List<View> mPagers;
	private PagerAdapter mPagerAdapter;

	private XListView serverListView, nativeListView;
	private ArrayList<String> serverList, nativeList;
	private FindListAdapter serverAdapter, nativeAdapter;
	private int choosenItem = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myslots);

		initViews();
		initData();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem done = menu.add(0, 32901, 0, "Upload");
		done.setIcon(this.getResources().getDrawable(
				R.drawable.ic_pulldown));
		done.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case 32901:
			gotoNewStory();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void gotoNewStory(){
		startActivity(EditActivity.class);
	}

	private void initViews() {
		tv_updated = $(R.id.tv_updated);
		tv_not_update = $(R.id.tv_not_update);
		view_pager = $(R.id.view_pager);

		tv_updated.setOnClickListener(this);
		tv_not_update.setOnClickListener(this);

		serverListView = new XListView(this);
		nativeListView = new XListView(this);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		serverListView.setLayoutParams(lp);
		nativeListView.setLayoutParams(lp);
		serverListView.setXListViewListener(this);
		nativeListView.setXListViewListener(this);
		serverListView.setPullRefreshEnable(true);
		serverListView.setPullLoadEnable(false);
		nativeListView.setPullRefreshEnable(true);
		nativeListView.setPullLoadEnable(false);
		serverListView.setOnItemClickListener(mItemClickListener);
		nativeListView.setOnItemClickListener(mItemClickListener);

		mPagers = new ArrayList<View>();
		mPagers.add(serverListView);
		mPagers.add(nativeListView);

		mPagerAdapter = new PagerAdapter() {

			@Override
			public int getCount() {
				return mPagers.size();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public Object instantiateItem(View v, final int position) {
				((ViewPager) v).addView(mPagers.get(position));
				return mPagers.get(position);
			}

			@Override
			public void destroyItem(View v, int pos, Object obj) {
				((ViewPager) v).removeView(mPagers.get(pos));
			}
		};
		view_pager.setAdapter(mPagerAdapter);
		view_pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					choosenItem = 0;
					tv_updated.setBackgroundColor(Color.WHITE);
					tv_not_update.setBackgroundColor(Color.GRAY);
					break;

				case 1:
					choosenItem = 1;
					tv_updated.setBackgroundColor(Color.GRAY);
					tv_not_update.setBackgroundColor(Color.WHITE);
					break;
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

	}

	private void initData() {

		startProgressDialog(null);
		fetchServerList();

	}

	private void fetchServerList() {

		serverList = new ArrayList<String>();
		serverList.add("0");
		serverList.add("1");
		serverList.add("2");
		serverList.add("3");

		serverAdapter = new FindListAdapter(mContext, serverList);
		serverListView.setAdapter(serverAdapter);
		serverAdapter.notifyDataSetChanged();

	}

	private void getNativeList() {

		nativeList = new ArrayList<String>();
		nativeList.add("0");
		nativeList.add("1");
		nativeList.add("2");

		nativeAdapter = new FindListAdapter(mContext, nativeList);
		nativeListView.setAdapter(nativeAdapter);
		nativeAdapter.notifyDataSetChanged();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_updated:
			choosenItem = 0;
			view_pager.setCurrentItem(choosenItem);
			tv_updated.setBackgroundColor(Color.WHITE);
			tv_not_update.setBackgroundColor(Color.GRAY);

			break;

		case R.id.tv_not_update:
			choosenItem = 1;
			view_pager.setCurrentItem(choosenItem);
			tv_updated.setBackgroundColor(Color.GRAY);
			tv_not_update.setBackgroundColor(Color.WHITE);

			break;
		}
	}

	OnItemClickListener mItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Log.e("OnItemClick", id + "");
		}
	};

	@Override
	public void onRefresh() {
		if (choosenItem == 0) {
			fetchServerList();
		} else {
			getNativeList();
		}
	}

	@Override
	public void onLoadMore() {

	}
}
