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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihathot;

    public BaiHatHotAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.mangbaihathot = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_baihathot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat=mangbaihathot.get(position);
        holder.txtten.setText(baiHat.getTenbaihat());
        holder.txtcasi.setText(baiHat.getCasi());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(holder.imaghinh);

    }

    @Override
    public int getItemCount() {
        return mangbaihathot.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtten, txtcasi;
        ImageView imaghinh, imagluotthich;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.textviewtenbaihathot);
            txtcasi = itemView.findViewById(R.id.textviewcasibaihathot);
            imaghinh = itemView.findViewById(R.id.imageBaihathot);
            imagluotthich = itemView.findViewById(R.id.imageluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, PlaynhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihathot.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imagluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imagluotthich.setImageResource(R.drawable.iconloved);
                    DataSevicer dataSevicer= APIService.getService();
                    Call<String> callback=dataSevicer.Updaetluotthich("1",mangbaihathot.get(getPosition()).getIdbaihat());
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
                    imagluotthich.setEnabled(false);
                 }
            });

        }
    }
}
