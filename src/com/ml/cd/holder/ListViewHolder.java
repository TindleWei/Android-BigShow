package com.ml.cd.holder;

import java.util.List;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class ListViewHolder<T> extends ViewHolder{
	
	private List<T> mData;

	private BaseAdapter mAdapter;
	
	private ListView mListView;	
	
	private BaseViewHolder mHeader;
	
	private BaseViewHolder mFooter;
	
	private View mContainer;
	
	public ListViewHolder(BaseAdapter adapter, List<T> data){
		this.mAdapter = adapter;
		this.mData = data;
	}
	
	public ListView listView(){
		return mListView;
	}
	
	public void setListView(ListView listView){
		this.mListView = listView;
	}
	
	public BaseAdapter adapter(){
		return mAdapter;
	}
	
	public BaseAdapter setAdapter(){
		return mAdapter;
	}
	
	public List<T> data(){
		return mData;
	}
	
	public void setData(List<T> data){
		this.mData = data;
	}
	
	public BaseViewHolder header(){
		return mHeader;
	}
	
	public void setHeader(BaseViewHolder view){
		this.mHeader = view;
	}
	
	public BaseViewHolder footer(){
		return mFooter;
	}
	
	public void setFooter(BaseViewHolder view){
		this.mFooter = view;
	}
	
	public View container(){
		return mContainer;
	}
	
	public void setContainer(View view){
		this.mContainer = view;
	}

	
	
}
