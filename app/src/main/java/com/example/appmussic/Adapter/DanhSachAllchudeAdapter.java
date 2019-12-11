package com.example.appmussic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmussic.Activity.DanhSachtheloaithechudeActivity;
import com.example.appmussic.Model.ChuDe;
import com.example.appmussic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachAllchudeAdapter extends RecyclerView.Adapter<DanhSachAllchudeAdapter.ViewHolder> {
    Context context;
    ArrayList<ChuDe> mangchude;

    public DanhSachAllchudeAdapter(Context context, ArrayList<ChuDe> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_cac_chu_de,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe=mangchude.get(position);
        Picasso.with(context).load(chuDe.getHinhchude()).into(holder.imgchude);

    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgchude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude=itemView.findViewById(R.id.imagedongcacchude);
            imgchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachtheloaithechudeActivity.class);
                    intent.putExtra("chude",mangchude.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
