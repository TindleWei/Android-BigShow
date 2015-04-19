package com.ml.bigshow.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;

import com.ml.bigshow.R;
import com.ml.bigshow.entity.End;
import com.ml.bigshow.ui.EditActivity;
import com.ml.bigshow.widget.aadapter.AmazeAdapter;

@SuppressLint("UseSparseArrays") @SuppressWarnings("rawtypes")
public class SelectionListAdapter extends AmazeAdapter<End> {
	
	public int index = 0;

	@SuppressWarnings("unchecked")
	public SelectionListAdapter(Context context, List<End> items) {
		super(context, R.layout.listitem_selection, items);
		// setItems(items);
	}

	@Override
	protected int[] getChildViewIds() {
		return new int[] { R.id.tv_num, R.id.et_content, R.id.tv_end,
				R.id.iv_delete };
	}

	@Override
	protected void update(final int position, final End item) {

		if (position == 0) {
			setText(0, "A.");
		} else if (position == 1) {
			setText(0, "B.");
		} else if (position == 2) {
			setText(0, "C.");
		} else {
			setText(0, "D.");
		}
		
		final EditText edit1 = (EditText)textView(1);
		
		edit1.clearFocus();
        if(index!= -1 && index == position) {
        	edit1.requestFocus();
        }

        edit1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					index = position;
				}
				return false;
			}
		});
		
        edit1.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable editable) {
            	item.content = edit1.getText().toString();
            	
            }

            public void beforeTextChanged(CharSequence text, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence text, int start, int before, int count) {
            	
            }

    });
		
        edit1.setText(item.content);

		textView(2).setText("?");

		imageView(3).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//需要改EditActivity的List<End>
				EditActivity.endList.remove(position);
				setItems(EditActivity.endList);	
			}
		});

	}

}
