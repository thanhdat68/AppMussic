package com.example.appmussic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmussic.Activity.PlaynhacActivity;
import com.example.appmussic.Adapter.PlaynhacAdapter;
import com.example.appmussic.R;

public class Fragment_playdanhsachbaihat extends Fragment {
    RecyclerView recyclerViewplaynhac;
    View view;
    PlaynhacAdapter playnhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.frangment_play_danhsachcabaihat,container,false);
       recyclerViewplaynhac=view.findViewById(R.id.Recyclerviewplaybaihat);
       if (PlaynhacActivity.mangbaihat.size()>0){
           playnhacAdapter=new PlaynhacAdapter(getActivity(),PlaynhacActivity.mangbaihat);
           recyclerViewplaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
           recyclerViewplaynhac.setAdapter(playnhacAdapter);

       }
        return view;
    }
}
