package com.example.appmussic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmussic.Adapter.BaiHatHotAdapter;
import com.example.appmussic.Model.BaiHat;
import com.example.appmussic.R;
import com.example.appmussic.Service.APIService;
import com.example.appmussic.Service.DataSevicer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_BaiHatHot extends Fragment {
    View view;
    RecyclerView recyclerViewBatHathot;
    BaiHatHotAdapter baiHatHotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihathot,container,false);
        recyclerViewBatHathot=view.findViewById(R.id.Recyclervieưbaihathot);

        GetData();
        return view;
    }

    private void GetData() {
        DataSevicer dataSevicer= APIService.getService();
        Call<List<BaiHat>> callback = dataSevicer.GetBaiHatHot();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList= (ArrayList<BaiHat>) response.body();
                baiHatHotAdapter=new BaiHatHotAdapter(getActivity(),baiHatArrayList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBatHathot.setLayoutManager(linearLayoutManager);
                recyclerViewBatHathot.setAdapter(baiHatHotAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
              
            }
        });
    }
}
