package com.ml.bigshow;

import com.activeandroid.ActiveAndroid;
import com.avos.avoscloud.AVOSCloud;

import android.app.Application;
import android.util.DisplayMetrics;

public class BaseApplication extends Application {

	public static BaseApplication instance;
	
	public static BaseApplication getInstance() {
		return instance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		//初始化本地数据库
		ActiveAndroid.initialize(this);
		
		AVOSCloud.useAVCloudCN();
		AVOSCloud.initialize(this,
				"bp8bg4jqsnvc719ozomgp0ohvt6qa2n03o7e5sfe95v69j05",
				"u5rlevm51o2xjgvmkadomjk7lzfezhsir8fdjprmbz44x4dz");
		
	}
	
	

}
