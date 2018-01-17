package com.christiankula.albumviewer.mvp;

/**
 * This interface represents a base view contract that should be extended by an interface implemented by a concrete View
 * for the corresponding {@link BasePresenter}
 */
public interface BaseView<P extends BasePresenter> {

    /**
     * Sets this {@code BaseView}'s {@code BasePresenter}
     */
    void setPresenter(P presenter);
}
