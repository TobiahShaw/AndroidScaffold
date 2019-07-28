package io.github.tobiahshaw.scaffold.base.impl;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import java.io.IOException;

import io.github.tobiahshaw.scaffold.base.BaseActivity;
import io.github.tobiahshaw.scaffold.base.rx.BasePresenter;
import io.github.tobiahshaw.scaffold.base.rx.BaseView;
import io.github.tobiahshaw.scaffold.widget.LoadingDialog;

public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity implements BaseView {

    protected P presenter;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        presenter = initPresenter();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detach();//在presenter中解绑释放view
            presenter = null;
        }
        super.onDestroy();
    }

    /**
     * 在子类中初始化对应的presenter
     *
     * @return 相应的presenter
     */
    public abstract P initPresenter();

    @Override
    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog.create(context);
        }
        mLoadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) mLoadingDialog.dismiss();
    }

    @Override
    public void handleException(Throwable e) {
        if (e == null) return;
        e.printStackTrace();
        if (e.getClass().isAssignableFrom(IOException.class)) {
            showMsg("网络异常");
        } else {
            showMsg(e.getMessage());
        }
    }

    @Override
    public void showMsg(String msg) {
        if (getApplicationContext() == null || TextUtils.isEmpty(msg)) return;
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show());
    }

    protected void closeKeyBoard() {
        InputMethodManager inputMethodManager = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
        if (inputMethodManager == null) return;
        if (getCurrentFocus() == null) return;
        inputMethodManager.hideSoftInputFromWindow(this
                        .getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
