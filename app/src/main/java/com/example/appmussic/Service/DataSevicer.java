package com.example.appmussic.Service;

import com.example.appmussic.Model.Album;
import com.example.appmussic.Model.BaiHat;
import com.example.appmussic.Model.ChuDe;
import com.example.appmussic.Model.ChuDeTheLoai;
import com.example.appmussic.Model.Playlist;
import com.example.appmussic.Model.QuangCao;
import com.example.appmussic.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataSevicer {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlitstforcrrunday.php")
    Call<List<Playlist>> GetPlaylistCurrentday();

    @GET("chudevatheloai.php")
    Call<ChuDeTheLoai> GetChuDeTheLoai();

    @GET("Albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbathatquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachbaihattheoplaylist(@Field("idplaylist") String idolaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> Getdanhsachcacplaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> Getdanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<ChuDe>>GetAllchude();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>>Gettheloaitheochude(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>>GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>>Getdanhsachbaihattheoalbum(@Field("idalbum") String idalbum);


    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String>Updaetluotthich(@Field("luotthich")String luotthich,@Field("idbaihat") String idbaihat);
}
