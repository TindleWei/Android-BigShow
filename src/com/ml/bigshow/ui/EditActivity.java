package com.ml.bigshow.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ml.bigshow.BaseActivity;
import com.ml.bigshow.R;
import com.ml.bigshow.cc.ViewHolder;
import com.ml.bigshow.entity.End;
import com.ml.bigshow.entity.Slot;
import com.ml.bigshow.entity.Story;
import com.ml.bigshow.service.ImageUtils;
import com.ml.bigshow.service.LeanAsyncTask;
import com.ml.bigshow.service.PicassoService;
import com.ml.bigshow.ui.adapter.SelectionListAdapter;
import com.ml.bigshow.ui.clippic.ClipPictureActivity;

public class EditActivity extends BaseActivity {
	
	ViewHolder firstHolder;
	ViewHolder secondHolder;
	ViewHolder thirdHolder;

	// Data Part
	public Story story;
	public Slot slot;
	//偷懒，做成全局的, Adapter调用
	public static List<End> endList;

	private ViewPager view_pager;
	private List<View> mPagers;
	private PagerAdapter mPagerAdapter;
	private int choosenPosition = 0;

	private static final int INTENT_REQUEST_CODE_ALBUM = 21;

//	EditText titleTv;
//	EditText nameTv;
//	ImageView avatarIv;
	EditText contentTv;
	ImageView contentIv;
	EditText questionTv;

	public String page; // slot 的 page

	boolean isStoryChanged = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);

		// order是一定存在的
		page = (String) getIntent().getStringExtra("page");
		// 如果存在，传入Story
		story = (Story) getIntent().getSerializableExtra("Story");

		if(story!=null){
			slot = story.slots().get(Integer.valueOf(page));
			endList = slot.ends();
		}else{
			slot = new Slot();
			endList = new ArrayList<End>();
		}

		initViews();
		initData();
	}

	@Override
	protected void onPause() {
		super.onPause();

		new LeanAsyncTask(mContext) {

			@Override
			protected void doInBack() throws Exception {

				if (story == null) {
					// 新建Story
					story = new Story();
					story.title = firstHolder.textView(0).getText().toString().trim();
					story.cName = firstHolder.textView(1).getText().toString().trim();
					story.cAvatar = ImageUtils.saveDrawable2Data(firstHolder.imageView(0)
							.getDrawable());
					// story.uid
					// story.uName
					// story.uAvatar
					story.status = "-1";
					story.hotScore = "0";
					story.save();
				}

				if (isStoryChanged) {
					// 更新Story
					story.title = firstHolder.textView(0).getText().toString().trim();
					story.cName = firstHolder.textView(1).getText().toString().trim();
					story.cAvatar = ImageUtils.saveDrawable2Data(firstHolder.imageView(0)
							.getDrawable());
					// story.uid
					// story.uName
					// story.uAvatar
					story.status = "-1";
					story.save();
				}

				// 这里得测试一下，getMany()返回的值是多少
				if (story.slots() != null) {
					slot = story.slots().get(Integer.valueOf(page));
				}
				if (slot == null)
					slot = new Slot();

				// 在这里面存slot数据
				slot.page = page;
				slot.content = contentTv.getText().toString().trim();
				// 得到图片的路径
				slot.photo = ImageUtils.saveDrawable2Data(contentIv
						.getDrawable());
				slot.question = questionTv.getText().toString().trim();
				slot.fromStory = story;
				slot.save();

				// 还有End数据
				for (End end : endList) {
					end.save();
				}
			}

			@Override
			protected void onPost(Exception e) {

			}
		};

	}

	private void initViews() {

		view_pager = $(R.id.view_pager);

		mPagers = new ArrayList<View>();

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
				choosenPosition = position;
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
		firstHolder = new ViewHolder();
		secondHolder = new ViewHolder();
		thirdHolder = new ViewHolder();
		
		mPagers.add(EditViewHolder.getFirstPage(mContext, firstHolder));
		mPagers.add(getSecondPage());
		mPagers.add(getThirdPage());

		mPagerAdapter.notifyDataSetChanged();

		contentIv.setOnClickListener(myClick);

		addBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mData.size() < 4) {
					mData.add("1");
					selectionAdapter = new SelectionListAdapter(mContext, mData);
					mListView.setAdapter(selectionAdapter);
				}

			}
		});

		if (story != null) {
			firstHolder.textView(0).setText(story.title);
			firstHolder.textView(1).setText(story.cName);
			PicassoService.setSquarePhoto(story.cAvatar, firstHolder.imageView(0));

		}

	}

	View.OnClickListener myClick = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			startActivityForResult(new Intent(mContext,
					ClipPictureActivity.class), INTENT_REQUEST_CODE_ALBUM);
		};

	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case INTENT_REQUEST_CODE_ALBUM:

				Bitmap bitmap = null;
				Intent in = data;
				if (in != null) {
					byte[] bis = in.getByteArrayExtra("bitmap");
					bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
				}
				if (bitmap == null) {
					Log.e("11", "bitmap is NULL !");
				} else {
					contentIv.setImageBitmap(bitmap);
				}
				break;
			}
		}

	};

	

	public View getSecondPage() {

		int id_1 = 32901;

		ScrollView scroll = new ScrollView(mContext);

		RelativeLayout layout_outer = new RelativeLayout(mContext);
		FrameLayout.LayoutParams out_lp = new FrameLayout.LayoutParams(-1, -1);
		out_lp.gravity = Gravity.CENTER;
		layout_outer.setLayoutParams(out_lp);
		layout_outer.setFocusable(true);
		layout_outer.setFocusableInTouchMode(true);

		RelativeLayout rlayout_2 = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams rl_lp2 = new RelativeLayout.LayoutParams(
				-2, -2);
		rl_lp2.addRule(RelativeLayout.CENTER_IN_PARENT);
		rlayout_2.setPadding(0, 0, 0, dp2px(0));
		rlayout_2.setLayoutParams(rl_lp2);
		rlayout_2.setBackgroundColor(Color.YELLOW);

		ImageView iv_story = new ImageView(mContext);
		rl_lp2 = new RelativeLayout.LayoutParams(mWidth - dp2px(32), mWidth
				- dp2px(32));
		iv_story.setLayoutParams(rl_lp2);
		iv_story.setId(id_1);
		iv_story.setBackgroundColor(Color.GRAY);
		rlayout_2.addView(iv_story);

		EditText tv_content = new EditText(mContext);
		rl_lp2 = new RelativeLayout.LayoutParams(-2, -2);
		rl_lp2.setMargins(dp2px(16), 0, 0, 0);
		rl_lp2.addRule(RelativeLayout.BELOW, iv_story.getId());

		tv_content.setLayoutParams(rl_lp2);
		tv_content.setHint("故事的内容");
		tv_content.setMaxLines(5);
		tv_content.setTextSize(dp2px(18));
		layout_outer.addView(rlayout_2);

		rlayout_2.addView(tv_content);

		// outlet
		contentTv = tv_content;
		contentIv = iv_story;

		scroll.addView(layout_outer);

		return scroll;
	}

	SelectionListAdapter selectionAdapter;
	List<String> mData;
	ListView mListView;
	Button addBtn;
	ViewGroup page3;

	public View getThirdPage() {

		int id_1 = 32901;

		RelativeLayout layout_outer = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams r_lp = new RelativeLayout.LayoutParams(-1,
				-1);
		layout_outer.setLayoutParams(r_lp);

		//
		// bottomView
		//
		Button footerBtn = new Button(mContext);
		r_lp = new RelativeLayout.LayoutParams(-1, -2);
		r_lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		footerBtn.setId(id_1);
		footerBtn.setMinimumHeight(dp2px(60));
		footerBtn.setPadding(dp2px(16), dp2px(8), dp2px(16), dp2px(8));
		footerBtn.setLayoutParams(r_lp);
		footerBtn.setText("+");
		footerBtn.setTextSize(20);
		footerBtn.setTextColor(Color.WHITE);
		footerBtn.setBackgroundColor(Color.RED);

		layout_outer.addView(footerBtn);

		AbsListView.LayoutParams vg_lp = new AbsListView.LayoutParams(-1, -2);
		//
		// headerView
		//
		RelativeLayout headerLayout = new RelativeLayout(mContext);
		vg_lp = new AbsListView.LayoutParams(-1, -2);
		headerLayout.setMinimumHeight(dp2px(120));
		headerLayout.setPadding(dp2px(16), dp2px(8), dp2px(16), dp2px(8));
		headerLayout.setLayoutParams(vg_lp);

		EditText et_question = new EditText(mContext);
		r_lp = new RelativeLayout.LayoutParams(-2, -2);
		r_lp.addRule(RelativeLayout.CENTER_VERTICAL);
		et_question.setHint("问题来了");
		et_question.setLayoutParams(r_lp);

		headerLayout.addView(et_question);

		mListView = new ListView(mContext);
		r_lp = new RelativeLayout.LayoutParams(-1, -1);
		r_lp.addRule(RelativeLayout.ABOVE, footerBtn.getId());
		r_lp.setMargins(0, 0, 0, dp2px(16));
		mListView.setLayoutParams(r_lp);
		mListView.addHeaderView(headerLayout);

		mData = new ArrayList<String>();
		mData.add("0");
		mData.add("1");
		selectionAdapter = new SelectionListAdapter(mContext, mData);
		mListView.setAdapter(selectionAdapter);

		layout_outer.addView(mListView);

		// outlet
		addBtn = footerBtn;
		page3 = layout_outer;

		// scroll_view.requestLayout();
		// scroll_view.postInvalidate();

		return layout_outer;
	}
}
