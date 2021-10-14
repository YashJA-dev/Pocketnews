package com.example.techmasala.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmasala.Adapters.Constants.ContentList;
import com.example.techmasala.R;

import org.jetbrains.annotations.NotNull;

public class RecyclerView_Adapter extends ListAdapter<ContentList, RecyclerView_Adapter.ViewHolder_Main> {
    private Context context;
    protected RecyclerView_Adapter(@NonNull @NotNull AsyncDifferConfig<ContentList> config, Context context) {
        super(config);
        this.context=context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder_Main onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layoutInflater=LayoutInflater.from(context).inflate(R.layout.news_main,parent,false);
        return new ViewHolder_Main(layoutInflater);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder_Main holder, int position) {

    }

    class ViewHolder_Main extends RecyclerView.ViewHolder{
        public ViewHolder_Main(@NonNull @NotNull View itemView) {
            super(itemView);

        }
    }
}
