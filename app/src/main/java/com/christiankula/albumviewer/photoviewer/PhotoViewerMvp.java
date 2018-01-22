package com.christiankula.albumviewer.photoviewer;


import com.christiankula.albumviewer.mvp.BasePresenter;
import com.christiankula.albumviewer.mvp.BaseView;

public interface PhotoViewerMvp {

    interface Model {

    }

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
