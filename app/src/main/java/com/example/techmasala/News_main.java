package com.example.techmasala;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.techmasala.APIcalls.TechApi;
import com.example.techmasala.Adapters.Constants.ContentList;
import com.example.techmasala.Adapters.RecyclerView_Adapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class News_main extends Fragment {
    private RecyclerView recycler;
    private Context ctx;
    private Activity act;
    private  RecyclerView.LayoutManager layoutManager;
    private RecyclerView_Adapter adapter;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.newslayoutfrag,container,false);
    }

    @Override
    public void onResume() {
        this.act=getActivity();
        this.ctx=getContext();
        recycler=getActivity().findViewById(R.id.recyclerView);
        List<ContentList> li=new ArrayList<>();
        adapter = new RecyclerView_Adapter(li,ctx);
        String secondary=getArguments().getString("urlSecond");
        new TechApi(getContext(),getActivity()).getData("https://newsapi.org/v2/",secondary,recycler);

        recycler.setAdapter(adapter);

        super.onResume();
    }
}
