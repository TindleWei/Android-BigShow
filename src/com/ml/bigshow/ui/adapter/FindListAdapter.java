package com.ml.bigshow.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;


import com.ml.bigshow.R;
import com.ml.bigshow.widget.aadapter.AmazeAdapter;

@SuppressWarnings("rawtypes")
public class FindListAdapter extends AmazeAdapter<String> {

	@SuppressWarnings("unchecked")
	public FindListAdapter(Context context, List<String> items) {
		super(context, R.layout.listitem_findlist, items);
		// setItems(items);
	}

	@Override
	protected int[] getChildViewIds() {
		return new int[] { R.id.iv_character, R.id.tv_character, R.id.tv_title, R.id.tv_author };
	}

	@Override
	protected void update(int position, String item) {
		// TODO Auto-generated method stub
		
	}

}
