package com.christiankula.albumviewer.photoviewer.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.christiankula.albumviewer.models.Photo;

import java.util.List;


public class PhotoPagerAdapter extends FragmentStatePagerAdapter {

    private List<Photo> data;

    public PhotoPagerAdapter(FragmentManager fm, List<Photo> photoList) {
        super(fm);
        data = photoList;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoPageFragment.newInstance(data.get(position));
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public Photo getItemAt(int index) {
        return data.get(index);
    }
}
