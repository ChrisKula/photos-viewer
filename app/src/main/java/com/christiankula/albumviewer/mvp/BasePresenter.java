package com.christiankula.albumviewer.mvp;

/**
 * This interface represents a base presenter contract that should be implemented by a concrete Presenter for the
 * corresponding {@link BaseView}
 *
 * @param <V> the corresponding {@link BaseView} to the implementing Presenter
 */
public interface BasePresenter<V extends BaseView> {
    /**
     * Attach the given {@link BaseView} to this Presenter
     * <br/>
     * <br/>
     * Should be called as soon as possible, usually in {@code BaseView#onCreate()} or similar
     */
    void attachView(V view);

    /**
     * Detach a previously attached {@link BaseView} from this Presenter
     * <br/>
     * <br/>
     * Should be called when the Presenter doesn't plan on having interactions with the {@link BaseView} anymore,
     * usually in {@code BaseView#onDestroy()} or similar
     */
    void detachView();
}
