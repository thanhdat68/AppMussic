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
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmussic.Activity.PlaynhacActivity;
import com.example.appmussic.Model.BaiHat;
import com.example.appmussic.R;
import com.example.appmussic.Service.APIService;
import com.example.appmussic.Service.DataSevicer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends  RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat>mangbaihat;


    public DanhsachbaihatAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_danh_sach_bai_hat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat=mangbaihat.get(position);
        holder.txtcasi.setText(baiHat.getCasi());
        holder.txttenbaihat.setText(baiHat.getTenbaihat());
        holder.textindex.setText(position+1+"");

    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textindex,txttenbaihat,txtcasi;
        ImageView imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcasi=itemView.findViewById(R.id.textviewtencasi);
            txttenbaihat=itemView.findViewById(R.id.textviewtenbaihatdanhsach);
            textindex=itemView.findViewById(R.id.textviewdanhsachindex);
            imgluotthich=itemView.findViewById(R.id.imaviewluotthichdanhsachbaihat);
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    DataSevicer dataSevicer= APIService.getService();
                    Call<String> callback=dataSevicer.Updaetluotthich("1",mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("success")) {
                                Toast.makeText(context, "đã thich", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, PlaynhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
