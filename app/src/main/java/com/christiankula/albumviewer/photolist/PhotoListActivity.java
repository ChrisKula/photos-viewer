package com.christiankula.albumviewer.photolist;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.christiankula.albumviewer.AlbumViewerApplication;
import com.christiankula.albumviewer.R;
import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.photolist.mvp.PhotoListMvp;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoListActivity extends AppCompatActivity implements PhotoListMvp.View, SwipeRefreshLayout.OnRefreshListener {

    /**
     * Span count for GridLayoutManager when in portrait mode
     */
    private final static int SPAN_COUNT_PORTRAIT = 2;


    /**
     * Span count for GridLayoutManager when in landscape mode
     */
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

        setupPhotoListRecyclerView();
        initSwipeRefreshLayout();
    }


    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_photo_list, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (presenter.getPreferredListStyle()) {
            case PhotoAdapter.STYLE_LIST:
                menu.findItem(R.id.menu_item_list_style_list).setChecked(true);
                return true;

            case PhotoAdapter.STYLE_GRID:
            default:
                menu.findItem(R.id.menu_item_list_style_grid).setChecked(true);
                return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_refresh_list:
                presenter.onRefresh();
                return true;

            case R.id.menu_item_list_style_grid:
            case R.id.menu_item_list_style_list:
                presenter.onMenuItemListStyleClick(item.getItemId());
                invalidateOptionsMenu();
                setupPhotoListRecyclerView();
                return true;
        }

        return super.onOptionsItemSelected(item);
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

    private void setupPhotoListRecyclerView() {
        int preferredStyle = presenter.getPreferredListStyle();

        RecyclerView.LayoutManager layoutManager;

        photoAdapter = new PhotoAdapter(preferredStyle);

        switch (preferredStyle) {
            case PhotoAdapter.STYLE_LIST:
                layoutManager = new LinearLayoutManager(this);
                break;

            case PhotoAdapter.STYLE_GRID:
            default:
                int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? SPAN_COUNT_PORTRAIT : SPAN_COUNT_LANDSCAPE;

                layoutManager = new GridLayoutManager(this, spanCount);
                break;
        }

        rvPhotoList.setLayoutManager(layoutManager);
        rvPhotoList.setAdapter(photoAdapter);

        presenter.invalidatePhotosList();
    }

    private void initSwipeRefreshLayout() {
        srlRoot.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary));

        srlRoot.setOnRefreshListener(this);
    }
}
