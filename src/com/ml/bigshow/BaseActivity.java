package com.ml.bigshow;

import java.io.Serializable;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;

public class BaseActivity extends FragmentActivity {

	protected Context mContext;
	protected Activity mActivity;

	/* http请求加载进度轮 */
	protected MyProgressDialog progressDialog;

	/* 获取屏幕长，宽 */
	protected int mHeight, mWidth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mActivity = this;

		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mHeight = metric.heightPixels;
		mWidth = metric.widthPixels;
	}

	/**
	 * 启动进度加载Dialog
	 */
	public void startProgressDialog(String msg) {
		if (progressDialog == null) {
			progressDialog = MyProgressDialog.createDialog(this);
			progressDialog.setCancelable(true);
			progressDialog.setMessage(msg);
		}
		progressDialog.show();
	}

	/**
	 * 关闭进度加载Dialog
	 */
	public void stopProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	
	/**
	 * 设置ActionBar
	 */
	public void setActionBar(String title) {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
//		actionBar.setBackgroundDrawable()(
//				getResources().getColor(Color.GREEN));
		actionBar.setLogo(R.drawable.ic_launcher);
		actionBar.setTitle(title);
	}

	/**
	 * findViewById简化
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T $(int id) {
		return (T) super.findViewById(id);
	}

	/**
	 * findViewById简化
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T $(View v, int id) {
		return (T) v.findViewById(id);
	}

	
	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public int dp2px(float dpValue) {  
        final float scale = mContext.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
	/**
	 * Get intent extra
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected <V extends Serializable> V getSerializableExtra(final String name) {
		return (V) getIntent().getSerializableExtra(name);
	}

	/**
	 * Get intent extra
	 * 
	 */
	protected int getIntExtra(final String name) {
		return getIntent().getIntExtra(name, -1);
	}

	/**
	 * Get intent extra
	 * 
	 * @info JSON也可以通过这个传
	 * 
	 */
	protected String getStringExtra(final String name) {
		return getIntent().getStringExtra(name);
	}

	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/** 含有Bundle通过Class跳转界面 StartActivityForResult **/
	protected void startActivityForResult(Class<?> cls, Bundle bundle,
			int requestCode) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, requestCode);
	}

	/** 通过Action跳转界面 **/
	protected void startActivity(String action) {
		startActivity(action, null);
	}

	/** 含有Bundle通过Action跳转界面 **/
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

}
