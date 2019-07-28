package io.github.tobiahshaw.scaffold.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import io.github.tobiahshaw.scaffold.R;

public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    public static LoadingDialog create(Context context) {
        LoadingDialog loading = new LoadingDialog(context);
        loading.setContentView(R.layout.dialog_loading);
        loading.setCancelable(true);
        loading.setCanceledOnTouchOutside(false);

        Window window = loading.getWindow();
        if (window != null) {
            WindowManager.LayoutParams p = window.getAttributes();
            p.gravity = Gravity.CENTER;
            p.dimAmount = 0.2f;
        }
        return loading;
    }

}
