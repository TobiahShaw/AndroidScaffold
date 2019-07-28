package io.github.tobiahshaw.scaffold.base.rx;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public abstract class BaseSingleObserver<T> implements SingleObserver<T> {

    private BasePresenter presenter;
    private BaseView view;
    private boolean showDialog;

    public BaseSingleObserver(BasePresenter presenter, BaseView view) {
        this(presenter, view, true);
    }

    public BaseSingleObserver(BasePresenter presenter, BaseView view, boolean showDialog) {
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
    public void onSuccess(T t) {
        view.dismissLoadingDialog();
        whenSuccessful(t);
    }

    public abstract void whenSuccessful(T t);

    @Override
    public void onError(Throwable e) {
        view.handleException(e);
        view.dismissLoadingDialog();
    }
}
