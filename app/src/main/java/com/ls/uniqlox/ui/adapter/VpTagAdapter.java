package com.ls.uniqlox.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ls.uniqlox.model.ConstTagurl;
import com.ls.uniqlox.ui.frag.TagFragment;

public class VpTagAdapter extends FragmentPagerAdapter {

    public VpTagAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        TagFragment fragment = new TagFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("uniqlox_urls", ConstTagurl.uniqlox_urls[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ConstTagurl.uniqlox_urls[position].getName();
    }

    @Override
    public int getCount() {
        return ConstTagurl.uniqlox_urls.length;
    }
}
