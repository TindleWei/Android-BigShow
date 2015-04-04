package com.ml.bigshow;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 自定义进度Dialog
 *
 */
public class MyProgressDialog extends Dialog {
	private Context context = null;
	private static MyProgressDialog progressDialog = null;
	public MyProgressDialog(Context context){
		super(context);
		this.context = context;
	}
	public MyProgressDialog(Context context, int theme) {
		super(context, theme);
	}
	public static MyProgressDialog createDialog(Context context){
		progressDialog = new MyProgressDialog(context,R.style.MyProgressDialog);
		progressDialog.setContentView(R.layout.view_progressdialog);  
		progressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		return progressDialog;
	}
	public void onWindowFocusChanged(boolean hasFocus){
		if (progressDialog == null){
			return;
		}
		// 日后可以加旋转动画
//		ImageView imageView = (ImageView) progressDialog.findViewById(R.id.iv_load);
		// imageView的背景图片是一个anim动画，因此动画需要启动
//		AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
//		animationDrawable.start();
	}
	/**
	* setTitile 标题
	* @param strTitle
	*
	*/
	public MyProgressDialog setTitile(String strTitle){
		return progressDialog;
	}
	/**
	* setMessage 提示内容
	* @param strMessage
	*/
	public MyProgressDialog setMessage(String strMessage){
		TextView tvMsg = (TextView)progressDialog.findViewById(R.id.tv_msg);
		if (tvMsg != null){
			tvMsg.setText(strMessage);
		}
			return progressDialog;
	}
}
