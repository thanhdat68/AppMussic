package com.example.appmussic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmussic.Activity.DanhsachbaihatActivity;
import com.example.appmussic.Model.Album;
import com.example.appmussic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> mangAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> mangAlbum) {
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album=mangAlbum.get(position);
        holder.txtcasialbum.setText(album.getTencasialbum());
        holder.txttenalbum.setText(album.getTenalbum());
        Picasso.with(context).load(album.getHinhalbum()).into(holder.imghinhAlbum);

    }

    @Override
    public int getItemCount() {
        return mangAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imghinhAlbum;
        TextView txttenalbum, txtcasialbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhAlbum = itemView.findViewById(R.id.imgAlbum);
            txttenalbum = itemView.findViewById(R.id.tvTenalbum);
            txtcasialbum = itemView.findViewById(R.id.tvTencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album",mangAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
