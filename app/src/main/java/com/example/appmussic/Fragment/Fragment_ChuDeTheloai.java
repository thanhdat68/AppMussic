package com.example.appmussic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.appmussic.Activity.DanhSachtheloaithechudeActivity;
import com.example.appmussic.Activity.DanhsachbaihatActivity;
import com.example.appmussic.Activity.DanhsachtatcachudeActivity;
import com.example.appmussic.Adapter.DanhSachtheloaitheochudeAdapter;
import com.example.appmussic.Model.ChuDe;
import com.example.appmussic.Model.ChuDeTheLoai;
import com.example.appmussic.Model.TheLoai;
import com.example.appmussic.R;
import com.example.appmussic.Service.APIService;
import com.example.appmussic.Service.DataSevicer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDeTheloai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView tvxemthemchudetheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chudetheloai, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        tvxemthemchudetheloai = view.findViewById(R.id.textviewxemthemchude);
        tvxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        getData();
        return view;
    }

    private void getData() {
        DataSevicer dataSevicer = APIService.getService();
        Call<ChuDeTheLoai> callback = dataSevicer.GetChuDeTheLoai();
        callback.enqueue(new Callback<ChuDeTheLoai>() {
            @Override
            public void onResponse(Call<ChuDeTheLoai> call, Response<ChuDeTheLoai> response) {
                ChuDeTheLoai chuDeTheLoai = (ChuDeTheLoai) response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(chuDeTheLoai.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(chuDeTheLoai.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580, 250);
                layout.setMargins(10, 20, 10, 30);

                for (int i = 0; i < (chuDeArrayList.size()); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    if (chuDeArrayList.get(i).getHinhchude() != null) {
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhchude()).into(imageView);


                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent =new Intent(getActivity(), DanhSachtheloaithechudeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);

                        }
                    });
                }

                for (int j = 0; j < (theLoaiArrayList.size()); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null) {
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);


                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChuDeTheLoai> call, Throwable t) {

            }
        });

    }
}
