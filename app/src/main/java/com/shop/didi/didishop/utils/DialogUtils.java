package com.shop.didi.didishop.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import com.shop.didi.didishop.R;


public class DialogUtils {
	private Context context;
	private AlertDialog dialog;
	public TextView title;

	public DialogUtils(Context paramContext) {
		this.context = paramContext;
		this.dialog = new AlertDialog.Builder(paramContext).create();
	}

	public void dismiss() {
		try {
			if (this.dialog.isShowing())
				this.dialog.dismiss();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTitleText(CharSequence paramCharSequence) {
		this.title.setText(paramCharSequence);
	}

	public void show() {
		try {
			this.dialog.show();
			dialog.setCanceledOnTouchOutside(false);
			this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
			View localView = ((Activity) this.context).getLayoutInflater().inflate(R.layout.progress_dialog_view, null);
			this.title = ((TextView) localView.findViewById(R.id.title));
			this.dialog.getWindow().setContentView(localView);
			return;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
}