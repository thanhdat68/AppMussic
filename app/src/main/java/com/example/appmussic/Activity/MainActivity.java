package com.example.appmussic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.appmussic.Adapter.MainViewpageAdrapter;
import com.example.appmussic.Fragment.Fragment_Tim_Kiem;
import com.example.appmussic.Fragment.Fragment_Trang_Chu;
import com.example.appmussic.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
TabLayout tabLayout;
ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }

    private void init() {
        MainViewpageAdrapter mainViewpageAdrapter=new MainViewpageAdrapter(getSupportFragmentManager());
        mainViewpageAdrapter.addFragment(new Fragment_Trang_Chu(),"Trang Chủ ");
        mainViewpageAdrapter.addFragment(new Fragment_Tim_Kiem(),"Tìm Kiếm ");
        viewPager.setAdapter(mainViewpageAdrapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_trang_chu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_tim_kiem);
    }

    private  void anhxa(){
        tabLayout = findViewById(R.id.myTablayout);
        viewPager=findViewById(R.id.myViewPage);
        
    }
}
