package io.github.tobiahshaw.scaffold.base.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {

    private BasePresenter presenter;
    private BaseView view;
    private boolean showDialog;

    public BaseObserver(BasePresenter presenter, BaseView view) {
        this(presenter, view, true);
    }

    public BaseObserver(BasePresenter presenter, BaseView view, boolean showDialog) {
        this.presenter = presenter;
        this.view = view;
        this.showDialog = showDialog;
    }

    @Override
    public void onSubscribe(Disposable d) {
        presenter.addDisposable(d);
        if (showDialog) view.showLoadingDialog();
    }

    @Override
    public void onError(Throwable e) {
        view.handleException(e);
        if (showDialog) view.dismissLoadingDialog();
    }

    @Override
    public void onComplete() {
        if (showDialog) view.dismissLoadingDialog();
    }
}
