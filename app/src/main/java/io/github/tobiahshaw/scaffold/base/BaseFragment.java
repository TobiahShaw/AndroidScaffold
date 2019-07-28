package io.github.tobiahshaw.scaffold.base;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;

import io.github.tobiahshaw.scaffold.widget.LoadingDialog;

public abstract class BaseFragment extends Fragment {

    protected LoadingDialog mLoadingDialog;
    private boolean statusLightMode = false;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    protected void setLightStatusBarMode() {
        Activity activity = getActivity();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity != null) {
            int originFlag = activity.getWindow().getDecorView().getSystemUiVisibility();
            activity.getWindow().getDecorView().setSystemUiVisibility(originFlag | View
                    .SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            statusLightMode = true;
        }
    }

    protected void clearLightStatusBarMode() {
        Activity activity = getActivity();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity != null && statusLightMode) {
            int originFlag = activity.getWindow().getDecorView().getSystemUiVisibility();
            activity.getWindow().getDecorView().setSystemUiVisibility(originFlag ^ View
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
