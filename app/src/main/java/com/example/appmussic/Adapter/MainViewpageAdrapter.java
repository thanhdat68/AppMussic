package com.example.appmussic.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewpageAdrapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrayFragment=new ArrayList<>();
    private  ArrayList<String> arraytitle=new ArrayList<>();
    public MainViewpageAdrapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayFragment.size();
    }

    public void addFragment(Fragment fragment,String title){
        arrayFragment.add(fragment);
        arraytitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return arraytitle.get(position);
    }
}
