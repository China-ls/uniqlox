package com.ls.uniqlox.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ls.uniqlox.domain.Const;
import com.ls.uniqlox.ui.frag.TagFragment;

public class VpTagAdapter extends FragmentStatePagerAdapter {

    public VpTagAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        TagFragment fragment = new TagFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("uniqlox_urls", Const.uniqlox_urls[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Const.uniqlox_urls[position].getName();
    }

    @Override
    public int getCount() {
        return Const.uniqlox_urls.length;
    }
}
