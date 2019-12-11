package com.example.appmussic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmussic.Model.BaiHat;
import com.example.appmussic.R;

import java.util.ArrayList;

public class PlaynhacAdapter  extends RecyclerView.Adapter<PlaynhacAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> mangBaihat;

    public PlaynhacAdapter(Context context, ArrayList<BaiHat> mangBaihat) {
        this.context = context;
        this.mangBaihat = mangBaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_play_baihta,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat=mangBaihat.get(position);
        holder.txtindex.setText(position + 1+"");
        holder.txtcasi.setText(baiHat.getCasi());
        holder.txttenbaihat.setText(baiHat.getTenbaihat());

    }

    @Override
    public int getItemCount() {
        return mangBaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenbaihat,txtindex,txtcasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcasi=itemView.findViewById(R.id.textviewplaynhactencasi);
            txtindex=itemView.findViewById(R.id.textviewplaynhacindex);
            txttenbaihat=itemView.findViewById(R.id.textviewplaytenbaihat);
        }
    }
}
