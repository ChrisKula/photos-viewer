package com.christiankula.albumviewer.photoviewer;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.christiankula.albumviewer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoViewerActivity extends AppCompatActivity {

    private static final String TAG = PhotoViewerActivity.class.getSimpleName();

    @BindView(R.id.vp_photos)
    ViewPager vpPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);


        ButterKnife.bind(this);
    }
}
