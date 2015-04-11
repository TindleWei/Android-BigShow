package com.ml.bigshow.cd;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {

	private static final Object[] EMPTY = new Object[0];
	
	public View parentView;

	public ImageView[] imageViews;

	public TextView[] textViews;

	public ViewHolder() {

	}

	public ImageView imageView(final int childViewIndex) {
		return imageViews[childViewIndex];
	}

	public TextView textView(final int childViewIndex) {
		return (TextView) textViews[childViewIndex];
	}

	public Button button(final int childViewIndex) {
		return (Button) textViews[childViewIndex];
	}

	public EditText editText(final int childViewIndex) {
		return (EditText) textViews[childViewIndex];
	}

	public void setParentView(View view) {
		this.parentView = view;
	}

	public void setImageViews(final Object[] items) {
		if (items != null)
			this.imageViews = (ImageView[]) items;
		else
			this.imageViews = (ImageView[]) EMPTY;
	}

	public void setTextViews(final Object[] items) {
		if (items != null)
			this.textViews = (TextView[]) items;
		else
			this.textViews = (TextView[]) EMPTY;
	}

}
