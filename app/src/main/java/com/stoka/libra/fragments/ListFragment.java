package com.stoka.libra.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stoka.libra.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListFragment extends Fragment {

    private Unbinder unbinder;


    RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,null);
        List<Category> list = initList();
        unbinder = ButterKnife.bind(this,view);
rv = view.findViewById(R.id.rv);
        ListAdapter listAdapter = new ListAdapter(list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(listAdapter);

        return view;
    }

    public List<Category> initList(){
        List<Category> list = new ArrayList<>();
        list.add(new Category("fruits",R.drawable.ic_done_white_18dp));
        list.add(new Category("super",R.drawable.ic_done_white_18dp));
        list.add(new Category("apple",R.drawable.ic_done_white_18dp));
        list.add(new Category("banana",R.drawable.ic_done_white_18dp));
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
