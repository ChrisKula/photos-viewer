package com.christiankula.albumviewer.photoviewer;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.christiankula.albumviewer.AlbumViewerApplication;
import com.christiankula.albumviewer.R;
import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.photoviewer.page.PhotoPagerAdapter;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoViewerActivity extends AppCompatActivity implements PhotoViewerMvp.View, ViewPager.OnPageChangeListener {

    private static final String TAG = PhotoViewerActivity.class.getSimpleName();

    public static final String PHOTOS_EXTRA_KEY = "PHOTOS_EXTRA";

    public static final String SELECTED_PHOTO_ID_EXTRA_KEY = "SELECTED_PHOTO_ID_EXTRA";

    @BindView(R.id.vp_photos)
    ViewPager vpPhotos;

    private PhotoViewerMvp.Presenter presenter;
    private PhotoPagerAdapter photoPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        ((AlbumViewerApplication) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        presenter.attachView(this);

        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();

        super.onDestroy();
    }

    @Inject
    @Override
    public void setPresenter(PhotoViewerMvp.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public List<Photo> getPhotosFromIntent() {
        return Parcels.unwrap(getIntent().getParcelableExtra(PHOTOS_EXTRA_KEY));
    }

    @Override
    public int getSelectedPhotoIdFromIntent() {
        return getIntent().getIntExtra(SELECTED_PHOTO_ID_EXTRA_KEY, -1);
    }

    @Override
    public void setupPhotosViewPager(List<Photo> photos) {
        setupPhotosViewPager(photos, 0);
    }

    @Override
    public void setupPhotosViewPager(List<Photo> photos, int selectedPhotoIndex) {
        photoPagerAdapter = new PhotoPagerAdapter(getSupportFragmentManager(), photos);

        vpPhotos.addOnPageChangeListener(this);
        vpPhotos.setAdapter(photoPagerAdapter);

        vpPhotos.setCurrentItem(selectedPhotoIndex);

        this.onPageSelected(selectedPhotoIndex);
    }

    @Override
    public void closeView() {
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setTitle(getString(R.string.photo_number_s, photoPagerAdapter.getItemAt(position).getId()));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
