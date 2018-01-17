package com.christiankula.albumviewer.photolist;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.christiankula.albumviewer.AlbumViewerApplication;
import com.christiankula.albumviewer.R;
import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.photolist.mvp.PhotoListMvp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoListActivity extends AppCompatActivity implements PhotoListMvp.View, SwipeRefreshLayout.OnRefreshListener {

    private final static int SPAN_COUNT_PORTRAIT = 2;
    private final static int SPAN_COUNT_LANDSCAPE = 4;

    @BindView(R.id.rv_photo_list)
    RecyclerView rvPhotoList;

    @BindView(R.id.srl_root)
    SwipeRefreshLayout srlRoot;

    private PhotoListMvp.Presenter presenter;

    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);

        ((AlbumViewerApplication) getApplication()).getComponent().inject(this);
        ButterKnife.bind(this);

        presenter.attachView(this);

        initPhotoListRecyclerView();
        initSwipeRefreshLayout();

        presenter.onCreate();
    }


    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    @Inject
    @Override
    public void setPresenter(PhotoListMvp.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayPhotos(List<Photo> photos) {
        photoAdapter.setData(photos);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        srlRoot.setRefreshing(refreshing);
    }

    @Override
    public void showUnableToRetrievePhotosMessage() {
        Snackbar.make(srlRoot, "Unable to retrieve photos at the moment, try again later.", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showNoPhotosToDisplayMessage() {
        //TODO Change toast to static message
        Toast.makeText(this, "There is no photos to display", Toast.LENGTH_SHORT).show();
    }

    private void initPhotoListRecyclerView() {
        photoAdapter = new PhotoAdapter(new ArrayList<Photo>());

        int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? SPAN_COUNT_PORTRAIT : SPAN_COUNT_LANDSCAPE;

        rvPhotoList.setLayoutManager(new GridLayoutManager(this, spanCount));
        rvPhotoList.setAdapter(photoAdapter);
    }

    private void initSwipeRefreshLayout() {
        srlRoot.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary));

        srlRoot.setOnRefreshListener(this);
    }
}
