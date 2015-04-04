package com.ml.bigshow.ui.adapter;


import java.util.List;

import android.content.Context;


import com.ml.bigshow.R;
import com.ml.bigshow.widget.aadapter.AmazeAdapter;

@SuppressWarnings("rawtypes")
public class SelectionListAdapter extends AmazeAdapter<String> {

	@SuppressWarnings("unchecked")
	public SelectionListAdapter(Context context, List<String> items) {
		super(context, R.layout.listitem_selection, items);
		// setItems(items);
	}

	@Override
	protected int[] getChildViewIds() {
		return new int[] { R.id.tv_num, R.id.tv_content, R.id.tv_end};
	}

	@Override
	protected void update(int position, String item) {
		
		if(position==0){
			setText(0, "A.");
			
		}else if(position==1){
			setText(0, "B.");
		}else if(position==2){
			setText(0, "C.");
		}else{
			setText(0, "D.");
		}
		
		
	}

}

