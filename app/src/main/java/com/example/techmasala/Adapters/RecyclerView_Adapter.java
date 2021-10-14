package com.example.techmasala.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmasala.Adapters.Constants.ContentList;
import com.example.techmasala.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder_Main> {
    private Context context;
    private List<ContentList> list;
    public RecyclerView_Adapter(List<ContentList> list, Context context){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder_Main onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layoutInflater=LayoutInflater.from(context).inflate(R.layout.news_main,parent,false);
        return new ViewHolder_Main(layoutInflater);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder_Main holder, int position) {
        ContentList content = list.get(position);
        holder.description.setText(content.getDescription());
        holder.tittle.setText(content.getTitle());
        if(position==0) holder.url_img_holder.setImageDrawable(context.getDrawable(R.drawable.krishna_ji));
        else Picasso.get().load(content.getImgUrl()).into(holder.url_img_holder);
        holder.tittle.setText(content.getTitle());
        holder.author.setText(content.getAuthor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder_Main extends RecyclerView.ViewHolder{
        TextView description;
        ImageView url_img_holder;
        TextView tittle;
        TextView author;
        public ViewHolder_Main(@NonNull @NotNull View itemView) {
            super(itemView);
            description=itemView.findViewById(R.id.description_news);
            url_img_holder=itemView.findViewById(R.id.img_news);
            tittle=itemView.findViewById(R.id.tiitle_news);
            author=itemView.findViewById(R.id.author);
        }
    }
}
