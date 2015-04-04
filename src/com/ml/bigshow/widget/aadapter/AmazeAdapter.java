package com.ml.bigshow.widget.aadapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;

public abstract class AmazeAdapter <V> extends SingleTypeAdapter<V>{
	
	/**
	 *  这个是我写的单个BaseAdapter的父类
	 */
	public AmazeAdapter(final Context context, final int layoutId,
			final List<V> items) {
		super(LayoutInflater.from(context), layoutId);
		setItems(items);
	}


}
