package io.github.tobiahshaw.scaffold.base.impl;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;

import io.github.tobiahshaw.scaffold.base.BaseFragment;
import io.github.tobiahshaw.scaffold.base.rx.BasePresenter;
import io.github.tobiahshaw.scaffold.base.rx.BaseView;
import io.github.tobiahshaw.scaffold.widget.LoadingDialog;

public abstract class BaseMVPFragment<P extends BasePresenter> extends BaseFragment implements BaseView {

    protected P presenter;
    private boolean isViewCreate = false;//view是否创建
    private boolean isViewVisible = false;//view是否可见
    public Context context;
    private boolean isFirst = true;//是否第一次加载


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        presenter = initPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
        if (getActivity() == null || getActivity().getApplicationContext() == null || TextUtils.isEmpty(msg))
            return;
        getActivity().runOnUiThread(() -> Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show());
    }

}
