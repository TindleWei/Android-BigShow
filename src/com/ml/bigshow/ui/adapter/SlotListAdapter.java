package com.ml.bigshow.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;


import com.ml.bigshow.R;
import com.ml.bigshow.widget.aadapter.AmazeAdapter;

@SuppressWarnings("rawtypes")
public class SlotListAdapter extends AmazeAdapter<String> {

	@SuppressWarnings("unchecked")
	public SlotListAdapter(Context context, List<String> items) {
		super(context, R.layout.listitem_slotlist, items);
		// setItems(items);
	}

	@Override
	protected int[] getChildViewIds() {
		return new int[] { R.id.tv_num, R.id.tv_descrip, R.id.tv_status, R.id.iv_edit, R.id.iv_delete, R.id.iv_add };
	}

	@Override
	protected void update(int position, String item) {
		
		
	}

}
