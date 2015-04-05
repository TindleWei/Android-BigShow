package com.ml.bigshow.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public abstract class LeanAsyncTask extends AsyncTask<Void, Void, Void> {
	  ProgressDialog dialog;
	  protected Context ctx;
	  boolean openDialog = false;
	  Exception exception;

	  protected LeanAsyncTask(Context ctx) {
	    this.ctx = ctx;
	  }

	  protected LeanAsyncTask(Context ctx, boolean openDialog) {
	    this.ctx = ctx;
	    this.openDialog = openDialog;
	  }

	  public LeanAsyncTask setOpenDialog(boolean openDialog) {
	    this.openDialog = openDialog;
	    return this;
	  }

	  public ProgressDialog getDialog() {
	    return dialog;
	  }

	  @Override
	  protected void onPreExecute() {
	    super.onPreExecute();
	    
	    if (openDialog) {
	      dialog = showSpinnerDialog((Activity) ctx);
	    }
	  }
	  
	 //wait 要把这个Dialog改成我的
	  public static ProgressDialog showSpinnerDialog(Activity activity) {
		    //activity = modifyDialogContext(activity);

		    ProgressDialog dialog = new ProgressDialog(activity);
		    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		    dialog.setCancelable(true);
		    dialog.setMessage("Loading...");
		    if (activity.isFinishing()==false) {
		      dialog.show();
		    }
		    return dialog;
		  }

	  @Override
	  protected Void doInBackground(Void... params) {
	    try {
	      doInBack();
	    } catch (Exception e) {
	      e.printStackTrace();
	      exception = e;
	    }
	    return null;
	  }

	  @Override
	  protected void onPostExecute(Void aVoid) {
	    super.onPostExecute(aVoid);
	    if (openDialog) {
	      if (dialog.isShowing()) {
	        dialog.dismiss();
	      }
	    }
	    onPost(exception);
	  }

	  protected abstract void doInBack() throws Exception;

	  protected abstract void onPost(Exception e);
	}
