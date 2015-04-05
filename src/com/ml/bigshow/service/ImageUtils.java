package com.ml.bigshow.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Environment;

public class ImageUtils {

	public static String ROOT_PATH = Environment.getExternalStorageDirectory()
			+ "/Big/";

	public static String createImageName() {
		return java.util.UUID.randomUUID().toString();
	}
	
	/**
	 * 保存drawable图片到本地文件
	 * @return 图片文件路径
	 */
	public static String saveDrawable2Data(Drawable drawable){
		Bitmap bm = drawable2Bitmap(drawable);
		byte[] bytes = Bitmap2Bytes(bm);
		return bytes2Data(bytes);
	}

	/**
	 * 将byte[]图片保存到本地文件中
	 */
	public static String bytes2Data(byte[] img) {
		try {
			String filePath = ROOT_PATH + createImageName() + ".jpg";
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
			return filePath;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 将Drawable图片保存到本地文件中
	 */
	public static String drawableID2Data(Context context, Integer drawableID) {

		try {
			String filePath = ROOT_PATH + createImageName() + ".jpg";
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}

			InputStream is = context.getResources().openRawResource(drawableID);
			FileOutputStream fos = new FileOutputStream(file);
			
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			fos.close();
			is.close();

			return filePath;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 将Bitmap转化byte[]
	 */
	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * 将drawable转为Bitmap
	 */
	public static Bitmap drawable2Bitmap(Drawable drawable) {
		// 取 drawable 的长宽
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();

		// 取 drawable 的颜色格式
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}
	
	

}
