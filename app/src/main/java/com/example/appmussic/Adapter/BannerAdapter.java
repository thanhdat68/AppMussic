package com.example.appmussic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appmussic.Activity.DanhsachbaihatActivity;
import com.example.appmussic.Model.QuangCao;
import com.example.appmussic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);
        ImageView imgbacgroudbanner = view.findViewById(R.id.imageviewBackgroudbanmer);
        ImageView imgsongbanner = view.findViewById(R.id.imageviewBanner);
        TextView txtitleSongbanner = view.findViewById(R.id.textViewtitleBanner);
        TextView tvNoidung = view.findViewById(R.id.tvnoidung);

        Picasso.with(context).load(arrayListBanner.get(position).getHinhanh()).into(imgbacgroudbanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhbaihat()).into(imgsongbanner);
        txtitleSongbanner.setText(arrayListBanner.get(position).getTenbaihat());
        tvNoidung.setText(arrayListBanner.get(position).getNoidung());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner",arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
