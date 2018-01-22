package com.christiankula.albumviewer.photoviewer;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.christiankula.albumviewer.AlbumViewerApplication;
import com.christiankula.albumviewer.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoViewerActivity extends AppCompatActivity implements PhotoViewerMvp.View {

    private static final String TAG = PhotoViewerActivity.class.getSimpleName();

    @BindView(R.id.vp_photos)
    ViewPager vpPhotos;

    private PhotoViewerMvp.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        ((AlbumViewerApplication) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);
    }

    @Inject
    @Override
    public void setPresenter(PhotoViewerMvp.Presenter presenter) {
        this.presenter = presenter;
    }
}
