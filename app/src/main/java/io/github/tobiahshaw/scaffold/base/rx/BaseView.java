package io.github.tobiahshaw.scaffold.base.rx;

public interface BaseView {

    void showLoadingDialog();
    void dismissLoadingDialog();
    void handleException(Throwable e);
    void showMsg(String msg);

}
