package com.example.techmasala;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.techmasala.APIcalls.TechApi;

import org.jetbrains.annotations.NotNull;

public class News_main extends Fragment {
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_main,container,false);
    }

    @Override
    public void onResume() {
        StringBuilder url=new StringBuilder("url is = ");
        url.append(getArguments().getString("main"));
        Toast.makeText(getContext(),url,Toast.LENGTH_SHORT).show();
//        new TechApi(getContext(),getActivity()).getData("");
        super.onResume();
    }
}
