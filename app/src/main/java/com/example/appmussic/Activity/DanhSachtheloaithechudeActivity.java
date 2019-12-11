package com.example.appmussic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appmussic.Adapter.DanhSachtheloaitheochudeAdapter;
import com.example.appmussic.Model.ChuDe;
import com.example.appmussic.Model.TheLoai;
import com.example.appmussic.R;
import com.example.appmussic.Service.APIService;
import com.example.appmussic.Service.DataSevicer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachtheloaithechudeActivity extends AppCompatActivity {
    ChuDe chuDe;
    Toolbar toolbartheloaitheochude;
    RecyclerView recyclerViewtheloaitheochude;
    DanhSachtheloaitheochudeAdapter danhSachtheloaitheochudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sachtheloaithechude);
        getInten();
        init();
        GetData();

    }

    private void GetData() {
        DataSevicer dataSevicer = APIService.getService();
        Call<List<TheLoai>> callback = dataSevicer.Gettheloaitheochude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai = (ArrayList<TheLoai>) response.body();
                danhSachtheloaitheochudeAdapter = new DanhSachtheloaitheochudeAdapter(DanhSachtheloaithechudeActivity.this,mangtheloai);
               recyclerViewtheloaitheochude.setLayoutManager(new GridLayoutManager(DanhSachtheloaithechudeActivity.this,2));
               recyclerViewtheloaitheochude.setAdapter(danhSachtheloaitheochudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewtheloaitheochude = findViewById(R.id.Recyclerviewtheloaitheochude);
        toolbartheloaitheochude = findViewById(R.id.toobartheloaitheochude);
        setSupportActionBar(toolbartheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenchude());
        toolbartheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getInten() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")) {
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
            Toast.makeText(this, chuDe.getTenchude(), Toast.LENGTH_SHORT).show();
        }
    }
}
