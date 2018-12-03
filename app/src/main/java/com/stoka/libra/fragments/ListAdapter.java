package com.stoka.libra.fragments;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stoka.libra.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private ListAdapter.onItemClickListener onItemClickListener;

    public interface onItemClickListener{
        void onItemClick(int pos);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    private List<Category> list;
    public ListAdapter(List<Category> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_list_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            myViewHolder.name.setText(list.get(i).getName());
            myViewHolder.icon.setImageResource(list.get(i).getIcon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView icon;
        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.imgIcon);
            name = itemView.findViewById(R.id.txtName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getLayoutPosition());
        }
    }
}
