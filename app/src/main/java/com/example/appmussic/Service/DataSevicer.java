package com.example.appmussic.Service;

import com.example.appmussic.Model.ChuDeTheLoai;
import com.example.appmussic.Model.Playlist;
import com.example.appmussic.Model.QuangCao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataSevicer {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlitstforcrrunday.php")
    Call<List<Playlist>> GetPlaylistCurrentday();

    @GET("chudevatheloai.php")
    Call<List<ChuDeTheLoai>>GetChuDeTheLoai();
}
