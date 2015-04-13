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
import com.ml.bigshow.entity.End;
import com.ml.bigshow.entity.Slot;
import com.ml.bigshow.entity.Story;
import com.ml.bigshow.service.ImageUtils;
import com.ml.bigshow.service.LeanAsyncTask;
import com.ml.bigshow.service.PicassoService;
import com.ml.bigshow.ui.adapter.SelectionListAdapter;
import com.ml.bigshow.ui.clippic.ClipPictureActivity;
import com.ml.cd.holder.BaseViewHolder;
import com.ml.cd.holder.ListViewHolder;
import com.ml.cd.holder.ViewHolder;
import com.ml.cd.maker.EditViewMaker;

public class EditActivity extends BaseActivity {
	
	ViewHolder firstHolder;
	ViewHolder secondHolder;
	ListViewHolder<String> thirdHolder;

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
//	EditText contentTv;
//	ImageView contentIv;
//	EditText questionTv;

	SelectionListAdapter selectionAdapter;
	List<String> mData;
	
//	ListView mListView;
//	Button addBtn;

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
				slot.content = secondHolder.textView(0).getText().toString().trim();
				// 得到图片的路径
				slot.photo = ImageUtils.saveDrawable2Data(secondHolder.imageView(0)
						.getDrawable());
				slot.question = ((ViewHolder)(thirdHolder.header())).textView(0).getText().toString().trim();
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initData() {
		firstHolder = new ViewHolder();
		secondHolder = new ViewHolder();
		thirdHolder = new ListViewHolder(selectionAdapter, mData);
		
		mPagers.add(EditViewMaker.getFirstPage(mContext, firstHolder));
		mPagers.add(EditViewMaker.getSecondPage(mContext, secondHolder));
		mPagers.add(EditViewMaker.getThirdPage(mContext, thirdHolder));

		mPagerAdapter.notifyDataSetChanged();

		secondHolder.imageView(0).setOnClickListener(myClick);

		thirdHolder.imageView(0).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mData.size() < 4) {
					mData.add("1");
					selectionAdapter = new SelectionListAdapter(mContext, mData);
					thirdHolder.listView().setAdapter(selectionAdapter);
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
					secondHolder.imageView(0).setImageBitmap(bitmap);
				}
				break;
			}
		}

	};



}
