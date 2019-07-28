package io.github.tobiahshaw.scaffold.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.github.tobiahshaw.scaffold.widget.LoadingDialog;

public abstract class BaseActivity  extends AppCompatActivity {

    protected LoadingDialog mLoadingDialog;
    private boolean statusLightMode = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getApplication()).stack.push(this);
    }

    @Override
    protected void onDestroy() {
        ((MyApplication)getApplication()).stack.remove(this);
        super.onDestroy();
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    protected void setLightStatusBarMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int originFlag = getWindow().getDecorView().getSystemUiVisibility();
            getWindow().getDecorView().setSystemUiVisibility(originFlag | View
                    .SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            statusLightMode = true;
        }
    }

    protected void clearLightStatusBarMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && statusLightMode) {
            int originFlag = getWindow().getDecorView().getSystemUiVisibility();
            getWindow().getDecorView().setSystemUiVisibility(originFlag ^ View
                    .SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            statusLightMode = false;
        }
    }

    protected int getStatusBarHeight() {
        int statusBarHeight = 0;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
